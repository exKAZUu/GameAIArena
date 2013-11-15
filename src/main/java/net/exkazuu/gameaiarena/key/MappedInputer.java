package net.exkazuu.gameaiarena.key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Preconditions;

public class MappedInputer {
  private static final class KeyData {
    private final List<KeyPressChecker> keyPressCheckers;
    private boolean now, old;

    private KeyData(KeyPressChecker keyPressChecker) {
      keyPressCheckers = new ArrayList<>();
      keyPressCheckers.add(keyPressChecker);
      now = false;
      old = false;
    }

    private final void add(KeyPressChecker keyPressChecker) {
      keyPressCheckers.add(keyPressChecker);
    }

    private final boolean isPress() {
      for (KeyPressChecker checker : keyPressCheckers) {
        if (checker.isPress()) {
          return true;
        }
      }
      return false;
    }

    private final void update() {
      old = now;
      now = isPress();
    }
  }

  private final HashMap<Object, KeyData> key2checker = new HashMap<>();

  public void add(Object key, KeyPressChecker keyPressChecker) {
    Preconditions.checkNotNull(keyPressChecker);

    KeyData keyData = key2checker.get(key);
    if (keyData == null) {
      key2checker.put(key, new KeyData(keyPressChecker));
    } else {
      keyData.add(keyPressChecker);
    }
  }

  public void clear() {
    key2checker.clear();
  }

  public boolean isPress(Object key) {
    Preconditions.checkArgument(key2checker.containsKey(key));

    return key2checker.get(key).now;
  }

  public boolean isPressNow(Object key) {
    Preconditions.checkArgument(key2checker.containsKey(key));

    return key2checker.get(key).isPress();
  }

  public boolean isPush(Object key) {
    Preconditions.checkArgument(key2checker.containsKey(key));

    final KeyData keyData = key2checker.get(key);
    return (keyData.now & !keyData.old);
  }

  public boolean isRelease(Object key) {
    Preconditions.checkArgument(key2checker.containsKey(key));
    final KeyData keyData = key2checker.get(key);
    return (!keyData.now & keyData.old);
  }

  public void update() {
    for (final KeyData keyData : key2checker.values()) {
      keyData.update();
    }
  }
}
