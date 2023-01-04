package cn.elytra.mod.mining.util;

/**
 * Positive X => East
 * Positive Z => South
 */
public enum HitSide {

	TOP, BOTTOM, EAST, WEST, NORTH, SOUTH;

	public static HitSide fromSideHit(int sideHit) {
		switch(sideHit) {
			case 0:
				return BOTTOM;
			case 1:
				return TOP;
			case 2:
				return SOUTH;
			case 3:
				return NORTH;
			case 4:
				return EAST;
			case 5:
				return WEST;
			default:
				throw new IllegalArgumentException("Invalid sideHit, should be in range from 0 to 5");
		}
	}

}
