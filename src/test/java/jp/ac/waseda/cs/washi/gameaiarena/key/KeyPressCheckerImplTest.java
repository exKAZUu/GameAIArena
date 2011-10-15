package jp.ac.waseda.cs.washi.gameaiarena.key;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.event.KeyEvent;

import org.junit.Test;

public class KeyPressCheckerImplTest {
	@Test
	public void キー押下の初期状態はfalse() {
		AwtKeyMemorizer mem = new AwtKeyMemorizer();
		KeyPressChecker checker1 = mem.getKeyPressChecker(KeyEvent.VK_ENTER);
		KeyPressChecker checker2 = mem.getKeyPressChecker(KeyEvent.VK_Z);

		assertEquals(false, checker1.isPress());
		assertEquals(false, checker2.isPress());
	}

	@Test
	public void キー押下によりtrueに変化() {
		AwtKeyMemorizer mem = new AwtKeyMemorizer();
		Component comp = new Component() {
			private static final long serialVersionUID = 1L;
		};
		KeyEvent event1 = new KeyEvent(comp, KeyEvent.KEY_PRESSED,
				System.currentTimeMillis(), 0, KeyEvent.VK_ENTER,
				(char) KeyEvent.VK_ENTER);
		KeyEvent event2 = new KeyEvent(comp, KeyEvent.KEY_PRESSED,
				System.currentTimeMillis(), 0, KeyEvent.VK_Z,
				(char) KeyEvent.VK_Z);
		KeyPressChecker checker1 = mem.getKeyPressChecker(KeyEvent.VK_ENTER);
		KeyPressChecker checker2 = mem.getKeyPressChecker(KeyEvent.VK_Z);

		mem.keyPressed(event1);
		assertEquals(true, checker1.isPress());
		assertEquals(false, checker2.isPress());
		mem.keyPressed(event2);
		assertEquals(true, checker1.isPress());
		assertEquals(true, checker2.isPress());
	}

	@Test
	public void キー解放によりfalseに変化() {
		AwtKeyMemorizer mem = new AwtKeyMemorizer();
		Component comp = new Component() {
			private static final long serialVersionUID = 1L;
		};
		KeyEvent event1 = new KeyEvent(comp, KeyEvent.KEY_PRESSED,
				System.currentTimeMillis(), 0, KeyEvent.VK_ENTER,
				(char) KeyEvent.VK_ENTER);
		KeyEvent event2 = new KeyEvent(comp, KeyEvent.KEY_PRESSED,
				System.currentTimeMillis(), 0, KeyEvent.VK_Z,
				(char) KeyEvent.VK_Z);
		KeyPressChecker checker1 = mem.getKeyPressChecker(KeyEvent.VK_ENTER);
		KeyPressChecker checker2 = mem.getKeyPressChecker(KeyEvent.VK_Z);

		mem.keyPressed(event1);
		mem.keyPressed(event2);
		mem.keyReleased(event1);
		assertEquals(false, checker1.isPress());
		assertEquals(true, checker2.isPress());
		mem.keyReleased(event2);
		assertEquals(false, checker1.isPress());
		assertEquals(false, checker2.isPress());
	}
}
