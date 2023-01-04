package cn.elytra.mod.mining.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.MovingObjectPosition;

import java.util.ArrayList;
import java.util.List;

public class BlockPos {

	private int x, y, z;
	private final boolean mutable;

	public BlockPos(int x, int y, int z) {
		this(x, y, z, true);
	}

	public BlockPos(int x, int y, int z, boolean mutable) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.mutable = mutable;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	@SuppressWarnings("unused")
	public void setX(int x) {
		if(mutable) {
			this.x = x;
		}
	}

	@SuppressWarnings("unused")
	public void setY(int y) {
		if(mutable) {
			this.y = y;
		}
	}

	@SuppressWarnings("unused")
	public void setZ(int z) {
		if(mutable) {
			this.z = z;
		}
	}

	public static BlockPos relativeTo(BlockPos reference, int offsetX, int offsetY, int offsetZ) {
		return new BlockPos(reference.x + offsetX, reference.y + offsetY, reference.z + offsetZ);
	}

	public static BlockPos fromMovingObjectPosition(MovingObjectPosition mop) {
		return new BlockPos(mop.blockX, mop.blockY, mop.blockZ);
	}

	public static ImmutableList<BlockPos> range(BlockPos p1, BlockPos p2) {
		List<BlockPos> result = new ArrayList<>();

		int minX = Math.min(p1.x, p2.x);
		int maxX = Math.max(p1.x, p2.x);
		int minY = Math.min(p1.y, p2.y);
		int maxY = Math.max(p1.y, p2.y);
		int minZ = Math.min(p1.z, p2.z);
		int maxZ = Math.max(p1.z, p2.z);

		for(int a = minX; a <= maxX; a++) {
			for(int b = minY; b <= maxY; b++) {
				for(int c = minZ; c <= maxZ; c++) {
					result.add(new BlockPos(a, b, c));
				}
			}
		}

		return ImmutableList.copyOf(result);
	}

}
