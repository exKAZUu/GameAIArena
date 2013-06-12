package net.exkazuu.gameaiarena.gui.builder;

import javax.swing.JFrame;

import net.exkazuu.gameaiarena.gui.JGamePanel;

public class WindowCreator {
  public JFrame createWindow(JGamePanel gamePanel) {
    JFrame window = new JFrame();
    window.getContentPane().add(gamePanel);
    return window;
  }
}
