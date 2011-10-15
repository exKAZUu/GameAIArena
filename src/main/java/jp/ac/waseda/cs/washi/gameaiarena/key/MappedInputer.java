package jp.ac.waseda.cs.washi.gameaiarena.key;

import java.util.HashMap;

public class MappedInputer {
	private static class KeyData {
		private final KeyPressChecker keyPressChecker;
		private boolean now, old;

		public KeyData(KeyPressChecker keyPressChecker) {
			this.keyPressChecker = keyPressChecker;
			now = false;
			old = false;
		}
	}

	private final HashMap<Integer, KeyData> key2checker = new HashMap<Integer, KeyData>();

	public void add(int key, KeyPressChecker keyPressChecker) {
		key2checker.put(key, new KeyData(keyPressChecker));
	}

	public void clear() {
		key2checker.clear();
	}

	public boolean isPress(int key) {
		return key2checker.get(key).now;
	}

	public boolean isPressNow(int key) {
		return key2checker.get(key).keyPressChecker.isPress();
	}

	public boolean isPush(int key) {
		final KeyData keyData = key2checker.get(key);
		return (keyData.now & !keyData.old);
	}

	public boolean isRelease(int key) {
		final KeyData keyData = key2checker.get(key);
		return (!keyData.now & keyData.old);
	}

	public void update() {
		for (final KeyData keyData : key2checker.values()) {
			keyData.old = keyData.now;
			keyData.now = keyData.keyPressChecker.isPress();
		}
	}
}