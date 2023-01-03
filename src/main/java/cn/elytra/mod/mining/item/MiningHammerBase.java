package cn.elytra.mod.mining.item;

import cn.elytra.mod.mining.util.BlockPos;
import cn.elytra.mod.mining.util.HitSide;
import cofh.core.item.tool.ItemHammerAdv;
import cofh.lib.util.helpers.BlockHelper;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public abstract class MiningHammerBase extends ItemHammerAdv implements IMiningHammer {

    public MiningHammerBase(ToolMaterial toolMaterial) {
        super(toolMaterial);
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {

        World world = player.worldObj;
        Block block = world.getBlock(x, y, z);

        if(!canHarvestBlock(block, stack)) {
            if(!player.capabilities.isCreativeMode) {
                stack.damageItem(1, player);
            }
            return false;
        }
        boolean used = false;

        float refStrength = ForgeHooks.blockStrength(block, player, world, x, y, z);
        if(refStrength != 0.0D && canHarvestBlock(block, stack)) {
            MovingObjectPosition pos = BlockHelper.getCurrentMovingObjectPosition(player, true);
	        Block adjBlock;
            float strength;

            int x2;
            int y2;
            int z2;

            // Mining changes
            // Removed the CoFHCore logic for looping blocks.
            // Making it more flexible to modify the range of mining blocks.

            ImmutableList<BlockPos> breakingBlocks =
                getMiningBlocks(stack, BlockPos.fromMovingObjectPosition(pos), HitSide.fromSideHit(pos.sideHit), player);

            for(BlockPos bp : breakingBlocks) {
                x2 = bp.getX();
                y2 = bp.getY();
                z2 = bp.getZ();

                adjBlock = world.getBlock(x2, y2, z2);
                strength = ForgeHooks.blockStrength(adjBlock, player, world, x2, y2, z2);
                if(strength > 0f && refStrength / strength <= 10f) {
                    used |= harvestBlock(world, x2, y2, z2, player);
                }
            }

            // Mining changes end

            if(used && !player.capabilities.isCreativeMode) {
                stack.damageItem(1, player);
            }
        }
        return true;
    }

}
