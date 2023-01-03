package cn.elytra.mod.mining.item;

import cn.elytra.mod.mining.Config;
import cn.elytra.mod.mining.Tags;
import cn.elytra.mod.mining.util.BlockPos;
import cn.elytra.mod.mining.util.HitSide;
import com.google.common.collect.ImmutableList;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CubeMiningHammer extends MiningHammerBase {

	private final int range;

	public CubeMiningHammer(ToolMaterial toolMaterial, int range) {
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
				p1 = BlockPos.relativeTo(hitPos, -range, -range * 2, -range);
				p2 = BlockPos.relativeTo(hitPos, range, 0, range);
				break;
			case BOTTOM:
				p1 = BlockPos.relativeTo(hitPos, -range, 0, -range);
				p2 = BlockPos.relativeTo(hitPos, range, range * 2, range);
				break;
			case EAST:
				p1 = BlockPos.relativeTo(hitPos, 0, -range, -range);
				p2 = BlockPos.relativeTo(hitPos, range * 2, range, range);
				break;
			case WEST:
				p1 = BlockPos.relativeTo(hitPos, -range * 2, -range, -range);
				p2 = BlockPos.relativeTo(hitPos, 0, range, range);
				break;
			case NORTH:
				p1 = BlockPos.relativeTo(hitPos, -range, -range, -range * 2);
				p2 = BlockPos.relativeTo(hitPos, range, range, 0);
				break;
			case SOUTH:
				p1 = BlockPos.relativeTo(hitPos, -range, -range, 0);
				p2 = BlockPos.relativeTo(hitPos, range, range, range * 2);
				break;
		}

		return BlockPos.range(p1, p2);
	}

	private static ImmutableList<BlockPos> getAdjustCube(BlockPos hitPos, HitSide hitSide, int range) {
		BlockPos p1 = hitPos, p2 = hitPos;

		int adjustY = (range * 2 + 1) - 2;

		switch(hitSide) {
			case TOP:
				p1 = BlockPos.relativeTo(hitPos, -range, -range * 2, -range);
				p2 = BlockPos.relativeTo(hitPos, range, 0, range);
				break;
			case BOTTOM:
				p1 = BlockPos.relativeTo(hitPos, -range, 0, -range);
				p2 = BlockPos.relativeTo(hitPos, range, range * 2, range);
				break;
			case EAST:
				p1 = BlockPos.relativeTo(hitPos, 0, -1, -range);
				p2 = BlockPos.relativeTo(hitPos, range * 2, adjustY, range);
				break;
			case WEST:
				p1 = BlockPos.relativeTo(hitPos, -range * 2, -1, -range);
				p2 = BlockPos.relativeTo(hitPos, 0, adjustY, range);
				break;
			case NORTH:
				p1 = BlockPos.relativeTo(hitPos, -range, -1, -range * 2);
				p2 = BlockPos.relativeTo(hitPos, range, adjustY, 0);
				break;
			case SOUTH:
				p1 = BlockPos.relativeTo(hitPos, -range, -1, 0);
				p2 = BlockPos.relativeTo(hitPos, range, adjustY, range * 2);
				break;
		}

		return BlockPos.range(p1, p2);
	}

	public static CubeMiningHammer iron;
	public static CubeMiningHammer diamond;

	public static void preInit() {
		iron = (CubeMiningHammer) GameRegistry.registerItem(new CubeMiningHammer(ToolMaterial.IRON, 1), "cube_iron_hammer", Tags.MODID).setUnlocalizedName("elytra_mining.iron_cube_hammer").setTextureName("elytra_mining:iron_cube_hammer");
		diamond = (CubeMiningHammer) GameRegistry.registerItem(new CubeMiningHammer(ToolMaterial.EMERALD, 2), "cube_diamond_hammer", Tags.MODID).setUnlocalizedName("elytra_mining.diamond_cube_hammer").setTextureName("elytra_mining:diamond_cube_hammer");
	}

	public static void init() {
		if(Config.enableFlatHammers) {
			GameRegistry.addShapedRecipe(new ItemStack(iron), "III", " S ", " S ", 'I', FlatMiningHammer.iron, 'S', Items.stick);
			GameRegistry.addShapedRecipe(new ItemStack(diamond), "DDD", " S ", " S ", 'D', FlatMiningHammer.diamond, 'S', Items.stick);
		} else {
			GameRegistry.addShapedRecipe(new ItemStack(iron), "III", "ISI", " S ", 'I', Blocks.iron_block, 'S', Items.stick);
			GameRegistry.addShapedRecipe(new ItemStack(diamond), "DDD", "DSD", " S ", 'D', Blocks.diamond_block, 'S', Items.stick);
		}
	}

}
