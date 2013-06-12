package net.exkazuu.gameaiarena.common;

import java.util.Collections;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import net.exkazuu.gameaiarena.api.Point2;


@Deprecated
public abstract class AbstractMap<T extends TypeSafeCloneable<T>>
    extends TypeSafeCloneable<AbstractMap<T>> {
  /** マップのタイル集合 */
  private SortedMap<Point2, T> tiles;

  protected AbstractMap() {
    tiles = new TreeMap<Point2, T>();
  }

  @Override
  public AbstractMap<T> clone() {
    AbstractMap<T> map = (AbstractMap<T>) super.clone();
    map.tiles = new TreeMap<Point2, T>();
    for (Entry<Point2, T> e : tiles.entrySet()) {
      map.tiles.put(e.getKey(), e.getValue().clone());
    }
    return map;
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

  public void rotateRight(SortedMap<Point2, T> tiles) {
    // TODO(exKAZUu):
  }
}
