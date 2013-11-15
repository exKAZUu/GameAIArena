package net.exkazuu.gameaiarena.sample;

import net.exkazuu.gameaiarena.api.Point2;
import net.exkazuu.gameaiarena.gui.Environment;
import net.exkazuu.gameaiarena.gui.GamePanel;
import net.exkazuu.gameaiarena.sample.entity.Chara;

public class SampleEnvironment extends Environment {
  private Chara _chara;

  public SampleEnvironment(GamePanel panel) {
    super(panel);
    _chara = new Chara(new Point2(0, 0));
  }

  public Chara getChara() {
    return _chara;
  }
}
