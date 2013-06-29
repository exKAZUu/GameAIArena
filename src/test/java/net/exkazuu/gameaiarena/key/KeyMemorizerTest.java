package net.exkazuu.gameaiarena.key;

import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.awt.event.KeyEvent;

import org.junit.Test;

public class KeyMemorizerTest {

  @Test
  public void キー押下の初期状態はfalse() {
    AwtKeyMemorizer mem = new AwtKeyMemorizer();

    assertEquals(false, mem.isPress(KeyEvent.VK_ENTER));
    assertEquals(false, mem.isPress(KeyEvent.VK_Z));
  }

  @Test
  public void キー押下によりtrueに変化() {
    AwtKeyMemorizer mem = new AwtKeyMemorizer();
    Component comp = new Component() {
      private static final long serialVersionUID = 1L;
    };
    KeyEvent event1 =
        new KeyEvent(comp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER,
            (char) KeyEvent.VK_ENTER);
    KeyEvent event2 =
        new KeyEvent(comp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_Z,
            (char) KeyEvent.VK_Z);

    mem.keyPressed(event1);
    assertEquals(true, mem.isPress(KeyEvent.VK_ENTER));
    assertEquals(false, mem.isPress(KeyEvent.VK_Z));
    mem.keyPressed(event2);
    assertEquals(true, mem.isPress(KeyEvent.VK_ENTER));
    assertEquals(true, mem.isPress(KeyEvent.VK_Z));
  }

  @Test
  public void キー解放によりfalseに変化() {
    AwtKeyMemorizer mem = new AwtKeyMemorizer();
    Component comp = new Component() {
      private static final long serialVersionUID = 1L;
    };
    KeyEvent event1 =
        new KeyEvent(comp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER,
            (char) KeyEvent.VK_ENTER);
    KeyEvent event2 =
        new KeyEvent(comp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_Z,
            (char) KeyEvent.VK_Z);

    mem.keyPressed(event1);
    mem.keyPressed(event2);
    mem.keyReleased(event1);
    assertEquals(false, mem.isPress(KeyEvent.VK_ENTER));
    assertEquals(true, mem.isPress(KeyEvent.VK_Z));
    mem.keyReleased(event2);
    assertEquals(false, mem.isPress(KeyEvent.VK_ENTER));
    assertEquals(false, mem.isPress(KeyEvent.VK_Z));
  }
}
