package net.exkazuu.gameaiarena.sample.java;

import net.exkazuu.gameaiarena.gui.builder.GameGuiBuildResult;
import net.exkazuu.gameaiarena.gui.builder.GameGuiBuilder;

public class SampleGamePanelMain {
  public static void main(String[] args) {
    GameGuiBuildResult<SampleEnvironment> ret =
        new GameGuiBuilder().setTitle("Test").setWindowSize(1000, 600).setPanelSize(800, 400)
            .setWindowCreator(new SampleWindowCreator()).buildForGui(SampleEnvironment.class);
    ret.getEnvironment().start(new SampleScene());
    ret.getWindow().dispose();
  }
}
