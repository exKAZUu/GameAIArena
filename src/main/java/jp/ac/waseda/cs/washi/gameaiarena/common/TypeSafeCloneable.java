package jp.ac.waseda.cs.washi.gameaiarena.common;

public class TypeSafeCloneable<T> implements Cloneable {
	public final T cloneTypeSafely() {
		return clone();
	}

	@SuppressWarnings("unchecked")
	public T clone() {
		try {
			return (T) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return null;
	}
}
