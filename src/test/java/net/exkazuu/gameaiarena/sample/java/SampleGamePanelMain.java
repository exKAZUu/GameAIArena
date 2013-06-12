package net.exkazuu.gameaiarena.sample.java;

import net.exkazuu.gameaiarena.gui.builder.BuildResult;
import net.exkazuu.gameaiarena.gui.builder.Builder;

public class SampleGamePanelMain {
  public static void main(String[] args) {
    BuildResult<SampleEnvironment> ret =
        new Builder().setTitle("Test").setWindowSize(1000, 600).setPanelSize(800, 400)
            .setWindowCreator(new SampleWindowCreator()).buildForGui(SampleEnvironment.class);
    ret.getEnvironment().start(new SampleScene());
    ret.getWindow().dispose();
  }
}
