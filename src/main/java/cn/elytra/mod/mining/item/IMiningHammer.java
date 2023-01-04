package cn.elytra.mod.mining.item;

import cn.elytra.mod.mining.util.BlockPos;
import cn.elytra.mod.mining.util.HitSide;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IMiningHammer {

	ImmutableList<BlockPos> getMiningBlocks(ItemStack hammer, BlockPos pos, HitSide side, EntityPlayer player);

}
