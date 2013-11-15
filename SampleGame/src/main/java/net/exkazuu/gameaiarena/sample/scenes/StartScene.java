package net.exkazuu.gameaiarena.sample.scenes;

import java.awt.Color;
import java.awt.Image;

import net.exkazuu.gameaiarena.gui.Renderer;
import net.exkazuu.gameaiarena.gui.Scene;
import net.exkazuu.gameaiarena.key.MappedInputer;
import net.exkazuu.gameaiarena.sample.SampleEnvironment;
import net.exkazuu.gameaiarena.sample.entity.GameKey;

public class StartScene extends Scene<SampleEnvironment> {

  private Scene<SampleEnvironment> _nextScene;

  public StartScene(Scene<SampleEnvironment> nextScene) {
    _nextScene = nextScene;
  }

  @Override
  public void initialize() {}

  @Override
  public void release() {}

  @Override
  public Scene<SampleEnvironment> run() {
    SampleEnvironment env = getEnvironment();
    MappedInputer inputer = env.getInputer();
    if (inputer.isPress(GameKey.ENTER)) {
      return _nextScene;
    }
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
