package net.exkazuu.gameaiarena.sample.java;

import java.awt.Color;
import java.awt.Image;

import net.exkazuu.gameaiarena.gui.Renderer;
import net.exkazuu.gameaiarena.gui.Scene;

public class SampleScene extends Scene<SampleEnvironment> {
  @Override
  public void initialize() {}

  @Override
  public void release() {}

  @Override
  public Scene<SampleEnvironment> run() {
    return this;
  }

  @Override
  public void draw() {
    SampleEnvironment env = getEnvironment();
    Renderer renderer = env.getRenderer();
    Image img = renderer.createImage(100, 100, Color.BLUE);
    renderer.clear(Color.BLACK);
    renderer.drawImage(img, 10, 20);
  }
}
