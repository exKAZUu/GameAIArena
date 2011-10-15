package jp.ac.waseda.cs.washi.gameaiarena.common;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

import jp.ac.waseda.cs.washi.gameaiarena.api.Point2;

public abstract class AbstractMap<T> {
	/** マップのタイル集合 */
	private final SortedMap<Point2, T> tiles;

	protected AbstractMap() {
		tiles = new TreeMap<Point2, T>();
	}

	public SortedMap<Point2, T> getTiles() {
		return Collections.unmodifiableSortedMap(tiles);
	}

	public void setTile(int x, int y, T tile) {
		setTile(new Point2(x, y), tile);
	}

	public void setTile(Point2 location, T tile) {
		tiles.put(location, tile);
	}

	public T getTile(int x, int y) {
		return getTile(new Point2(x, y));
	}

	public T getTile(Point2 location) {
		return tiles.get(location);
	}

	public boolean isAvailable(int x, int y) {
		return this.isAvailable(new Point2(x, y));
	}

	public boolean isAvailable(Point2 location) {
		return tiles.containsKey(location);
	}
}
