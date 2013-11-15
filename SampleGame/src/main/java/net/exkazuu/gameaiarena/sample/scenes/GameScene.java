package net.exkazuu.gameaiarena.sample.scenes;

import java.awt.Color;
import java.awt.Image;

import net.exkazuu.gameaiarena.api.Point2;
import net.exkazuu.gameaiarena.gui.Renderer;
import net.exkazuu.gameaiarena.gui.Scene;
import net.exkazuu.gameaiarena.key.MappedInputer;
import net.exkazuu.gameaiarena.sample.SampleEnvironment;
import net.exkazuu.gameaiarena.sample.entity.Chara;
import net.exkazuu.gameaiarena.sample.entity.GameKey;
import net.exkazuu.gameaiarena.sample.entity.Images;

public class GameScene extends Scene<SampleEnvironment> {
  private Scene<SampleEnvironment> _nextScene;

  public GameScene(Scene<SampleEnvironment> nextScene) {
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

    Chara chara = env.getChara();
    // Detect pressing keys
    if (inputer.isPress(GameKey.UP)) {
      chara.move(new Point2(0, -5));
    }
    if (inputer.isPress(GameKey.DOWN)) {
      chara.move(new Point2(0, 5));
    }

    // Detect pressing keys
    if (inputer.isPush(GameKey.LEFT)) {
      chara.move(new Point2(-5, 0));
    }
    if (inputer.isPush(GameKey.RIGHT)) {
      chara.move(new Point2(5, 0));
    }

    if (inputer.isPress(GameKey.ESCAPE)) {
      // If _nextScene is null, the game will terminate
      return _nextScene;
    }

    return this;
  }

  @Override
  public void draw() {
    SampleEnvironment env = getEnvironment();
    Renderer renderer = env.getRenderer();
    renderer.clear(Color.WHITE);

    Image img = Images.chara(renderer);
    Point2 p = env.getChara().getLocation();
    renderer.drawImage(img, p.x, p.y);
  }
}
