package net.exkazuu.gameaiarena.sample.java;

import net.exkazuu.gameaiarena.gui.Scene;
import net.exkazuu.gameaiarena.gui.builder.BuildResult;
import net.exkazuu.gameaiarena.gui.builder.Builder;

import org.junit.Test;

public class SampleGamePanelMainTest {
  @Test
  public void testBuilderForGui() {
    BuildResult<SampleEnvironment> ret =
        new Builder().setTitle("Test").setWindowSize(1000, 600).setPanelSize(800, 400)
            .setWindowCreator(new SampleWindowCreator()).buildForGui(SampleEnvironment.class);
    ret.getEnvironment().start(new TestScene());
    ret.getWindow().dispose();
  }

  @Test
  public void testBuilderForCui() {
    BuildResult<SampleEnvironment> ret = new Builder().buildForCui(SampleEnvironment.class);
    ret.getEnvironment().start(new TestScene());
  }
}


class TestScene extends SampleScene {
  @Override
  public Scene<SampleEnvironment> run() {
    return null;
  }
}
