package jp.ac.waseda.cs.washi.gameaiarena.common;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import jp.ac.waseda.cs.washi.gameaiarena.api.Point2;

public abstract class CollectionMap<T extends TypeSafeCloneable<T>>
    extends TypeSafeCloneable<CollectionMap<T>> {
  /** マップのタイル集合 */
  private SortedMap<Point2, T> tiles;
  private final int width;
  private final int height;

  protected CollectionMap(int width, int height) {
    this.width = width;
    this.height = height;
    tiles = new TreeMap<Point2, T>();
  }

  @Override
  public CollectionMap<T> clone() {
    CollectionMap<T> map = (CollectionMap<T>) super.clone();
    map.tiles = new TreeMap<Point2, T>();
    for (Entry<Point2, T> e : tiles.entrySet()) {
      map.tiles.put(e.getKey(), e.getValue().clone());
    }
    return map;
  }

  public Collection<T> getTiles() {
    return tiles.values();
  }

  public Set<Entry<Point2, T>> getLocationAndTiles() {
    return tiles.entrySet();
  }

  public void set(int x, int y, T tile) {
    set(new Point2(x, y), tile);
  }

  public void set(Point2 location, T tile) {
    tiles.put(location, tile);
  }

  public T get(int x, int y) {
    return get(new Point2(x, y));
  }

  public T get(Point2 location) {
    return tiles.get(location);
  }

  public boolean isAvailable(int x, int y) {
    return this.isAvailable(new Point2(x, y));
  }

  public boolean isAvailable(Point2 location) {
    return tiles.containsKey(location);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
