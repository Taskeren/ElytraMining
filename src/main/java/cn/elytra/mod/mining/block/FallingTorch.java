package cn.elytra.mod.mining.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class FallingTorch extends BlockTorch {

	public void dropTorchAsSand(World world, int x, int y, int z) {
		if(!world.isRemote) {
			// set metadata to 2 (placed on the top)
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);

			// spawn falling block
			EntityFallingBlock efb =
				new EntityFallingBlock(world, (float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F, this, 5);
			world.spawnEntityInWorld(efb);
		}
	}

	@SuppressWarnings("RedundantIfStatement")
	@Override
	protected boolean func_150108_b(World world, int x, int y, int z, Block p_150108_5_) {
		if(this.func_150109_e(world, x, y, z)) {
			int l = world.getBlockMetadata(x, y, z);
			boolean flag = false;

			if(!world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true) && l == 1) {
				flag = true;
			}

			if(!world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true) && l == 2) {
				flag = true;
			}

			if(!world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true) && l == 3) {
				flag = true;
			}

			if(!world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true) && l == 4) {
				flag = true;
			}

			if(!this.func_150107_m(world, x, y - 1, z) && l == 5) {
				flag = true;
			}

			if(flag) {

				// Mining changes
				// Replace the item drop with falling block creation

				dropTorchAsSand(world, x, y, z);

				// Mining changes end

				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	protected boolean func_150109_e(World world, int x, int y, int z) {
		if(!this.canPlaceBlockAt(world, x, y, z)) {
			if(world.getBlock(x, y, z) == this) {

				// Mining changes
				// Replace the item drop with falling block creation

				dropTorchAsSand(world, x, y, z);

				// Mining changes end

			}
			return false;
		} else {
			return true;
		}
	}

	public static FallingTorch fallingTorch;

	public static void preInit() {
		fallingTorch =
			(FallingTorch) GameRegistry.registerBlock(new FallingTorch(), "falling_torch").setHardness(0.0F).setLightLevel(0.9375F).setStepSound(soundTypeWood).setBlockName("elytra_mining.falling_torch").setBlockTextureName("torch_on");
	}

	public static void init() {
		GameRegistry.addShapedRecipe(new ItemStack(fallingTorch, 8), "TTT", "TST", "TTT", 'T', Blocks.torch, 'S', Blocks.sand);
	}

}
