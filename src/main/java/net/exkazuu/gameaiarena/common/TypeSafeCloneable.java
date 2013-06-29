package net.exkazuu.gameaiarena.common;

public class TypeSafeCloneable<T> implements Cloneable {
  @SuppressWarnings("unchecked")
  public T clone() {
    try {
      return (T) super.clone();
    } catch (CloneNotSupportedException e) {}
    return null;
  }
}
