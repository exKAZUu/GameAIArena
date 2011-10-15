package jp.ac.waseda.cs.washi.gameaiarena.common;

public interface PassPointChooser {

	public abstract Iterable<PassPoint> getNextPassPoints(PassPoint lastPoint);

}