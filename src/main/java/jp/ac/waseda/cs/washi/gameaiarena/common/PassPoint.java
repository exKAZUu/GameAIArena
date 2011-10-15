package jp.ac.waseda.cs.washi.gameaiarena.common;

import jp.ac.waseda.cs.washi.gameaiarena.api.Direction4;
import jp.ac.waseda.cs.washi.gameaiarena.api.Point2;

public class PassPoint {
	private final Point2 location;
	private final int moveCost;
	private final Direction4 lastDirection;

	public PassPoint(Point2 initialLocation) {
		location = initialLocation;
		moveCost = 0;
		lastDirection = null;
	}

	public PassPoint(PassPoint lastPassPoint, Direction4 nextDirection,
			int additionalCost) {
		location = lastPassPoint.getLocation().move(nextDirection);
		moveCost = lastPassPoint.getMoveCost() + additionalCost;
		lastDirection = nextDirection;
	}

	public Point2 getLocation() {
		return location;
	}

	public int getMoveCost() {
		return moveCost;
	}

	public Direction4 getLastDirection() {
		return lastDirection;
	}
	
	@Override
	public String toString() {
		return "{ " + location.toString() + ", " + lastDirection + ", " + moveCost + " }";
	}
}
