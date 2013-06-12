package net.exkazuu.gameaiarena.common;

public interface PassPointChooser {

	public abstract Iterable<PassPoint> getNextPassPoints(PassPoint lastPoint);

}