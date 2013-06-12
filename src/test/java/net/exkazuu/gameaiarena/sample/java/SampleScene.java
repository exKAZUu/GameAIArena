package net.exkazuu.gameaiarena.sample.java;

import java.awt.Color;

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
    renderer.clear(Color.BLACK);
  }
}
