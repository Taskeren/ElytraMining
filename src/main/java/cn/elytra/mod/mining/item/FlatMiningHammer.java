package cn.elytra.mod.mining.item;

import cn.elytra.mod.mining.Tags;
import cn.elytra.mod.mining.util.BlockPos;
import cn.elytra.mod.mining.util.HitSide;
import com.google.common.collect.ImmutableList;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class FlatMiningHammer extends MiningHammerBase {

    private final int range;

    public FlatMiningHammer(ToolMaterial toolMaterial, int range) {
        super(toolMaterial);
        this.range = range;
    }

    @Override
    public ImmutableList<BlockPos> getMiningBlocks(ItemStack hammer, BlockPos hitPos, HitSide hitSide, EntityPlayer player) {
        return getAdjustCube(hitPos, hitSide, range);
    }

    @SuppressWarnings("unused")
    private static ImmutableList<BlockPos> getCube(BlockPos hitPos, HitSide hitSide, int range) {
        BlockPos p1 = hitPos, p2 = hitPos;

        switch(hitSide) {
            case TOP:
            case BOTTOM:
                p1 = BlockPos.relativeTo(hitPos, -range, 0, -range);
                p2 = BlockPos.relativeTo(hitPos, range, 0, range);
                break;
            case EAST:
            case WEST:
                p1 = BlockPos.relativeTo(hitPos, 0, -range, -range);
                p2 = BlockPos.relativeTo(hitPos, 0, range, range);
                break;
            case NORTH:
            case SOUTH:
                p1 = BlockPos.relativeTo(hitPos, -range, -range, 0);
                p2 = BlockPos.relativeTo(hitPos, range, range, 0);
                break;
        }

        return BlockPos.range(p1, p2);
    }

    private static ImmutableList<BlockPos> getAdjustCube(BlockPos hitPos, HitSide hitSide, int range) {
        BlockPos p1 = hitPos, p2 = hitPos;

        int adjustY = (range * 2 + 1) - 2;

        switch(hitSide) {
            case TOP:
            case BOTTOM:
                p1 = BlockPos.relativeTo(hitPos, -range, 0, -range);
                p2 = BlockPos.relativeTo(hitPos, range, 0, range);
                break;
            case EAST:
            case WEST:
                p1 = BlockPos.relativeTo(hitPos, 0, -1, -range);
                p2 = BlockPos.relativeTo(hitPos, 0, adjustY, range);
                break;
            case NORTH:
            case SOUTH:
                p1 = BlockPos.relativeTo(hitPos, -range, -1, 0);
                p2 = BlockPos.relativeTo(hitPos, range, adjustY, 0);
                break;
        }

        return BlockPos.range(p1, p2);
    }

    public static FlatMiningHammer iron;
    public static FlatMiningHammer diamond;

    public static void preInit() {
        iron = (FlatMiningHammer) GameRegistry.registerItem(new FlatMiningHammer(ToolMaterial.IRON, 1), "iron_flat_hammer", Tags.MODID).setUnlocalizedName("elytra_mining.iron_flat_hammer").setTextureName("elytra_mining:iron_flat_hammer");
        diamond = (FlatMiningHammer) GameRegistry.registerItem(new FlatMiningHammer(ToolMaterial.EMERALD, 2), "diamond_flat_hammer", Tags.MODID).setUnlocalizedName("elytra_mining.diamond_flat_hammer").setTextureName("elytra_mining:diamond_flat_hammer");
    }

    public static void init() {
        GameRegistry.addShapedRecipe(new ItemStack(iron), "III", "ISI", " S ", 'I', Items.iron_ingot, 'S', Items.stick);
        GameRegistry.addShapedRecipe(new ItemStack(diamond), "DDD", "DSD", " S ", 'D', Items.diamond, 'S', Items.stick);
    }

}
