package net.exkazuu.gameaiarena.common;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.exkazuu.gameaiarena.api.Point2;


/**
 * Map which consists of square tiles on the basis of Array.
 * 
 * @author Kazunori Sakamoto (exkazuu@gmail.com)
 * 
 * @param <T> A tile type
 */
public abstract class ArrayMap<T extends TypeSafeCloneable<T>>
    extends TypeSafeCloneable<ArrayMap<T>> {
  private T[] tiles;
  private List<T> unmodifiableTileList;
  private final int width;
  private final int height;

  @SuppressWarnings("unchecked")
  protected ArrayMap(Class<T> klass, int width, int height) {
    this.width = width;
    this.height = height;
    initializeTiles((T[]) Array.newInstance(klass, width * height));
  }

  private void initializeTiles(T[] newTiles) {
    tiles = newTiles;
    unmodifiableTileList = Collections.unmodifiableList(Arrays.asList(newTiles));
  }

  @Override
  public ArrayMap<T> clone() {
    ArrayMap<T> map = (ArrayMap<T>) super.clone();
    map.initializeTiles(map.tiles.clone());
    T[] newTiles = map.tiles;
    for (int i = 0; i < newTiles.length; i++) {
      newTiles[i] = newTiles[i].clone();
    }
    return map;
  }

  public List<T> getTiles() {
    return unmodifiableTileList;
  }

  public void set(int x, int y, T tile) {
    tiles[y * getWidth() + x] = tile;
  }

  public void set(Point2 location, T tile) {
    set(location.x, location.y, tile);
  }

  public T get(int x, int y) {
    return tiles[y * getWidth() + x];
  }

  public T get(Point2 location) {
    return get(location.x, location.y);
  }

  public boolean isAvailable(int x, int y) {
    return 0 <= x && x < getWidth() && 0 <= y && y < getHeight();
  }

  public boolean isAvailable(Point2 location) {
    return isAvailable(location.x, location.y);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
