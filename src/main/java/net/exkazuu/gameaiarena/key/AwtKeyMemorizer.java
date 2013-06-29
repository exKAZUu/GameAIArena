package net.exkazuu.gameaiarena.key;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class AwtKeyMemorizer extends KeyAdapter {
  private final HashMap<Integer, Boolean> key2pressed = new HashMap<Integer, Boolean>();

  public boolean isPress(int key) {
    final Boolean b = key2pressed.get(key);
    return b != null && b == true;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    key2pressed.put(e.getKeyCode(), true);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    key2pressed.put(e.getKeyCode(), false);
  }

  public KeyPressChecker getKeyPressChecker(final int key) {
    return new KeyPressChecker() {
      @Override
      public boolean isPress() {
        return AwtKeyMemorizer.this.isPress(key);
      }
    };
  }
}
