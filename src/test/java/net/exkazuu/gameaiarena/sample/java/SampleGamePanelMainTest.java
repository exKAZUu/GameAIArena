package net.exkazuu.gameaiarena.sample.java;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import net.exkazuu.gameaiarena.gui.Scene;
import net.exkazuu.gameaiarena.gui.builder.GameGuiBuildResult;
import net.exkazuu.gameaiarena.gui.builder.GameGuiBuilder;

import org.junit.Test;

public class SampleGamePanelMainTest {
  @Test
  public void testBuilderForGui() {
    GameGuiBuildResult<SampleEnvironment> ret =
        new GameGuiBuilder().setTitle("Test").setWindowSize(1000, 600).setPanelSize(800, 400)
            .setWindowCreator(new SampleWindowCreator()).buildForGui(SampleEnvironment.class);
    TestScene testScene = new TestScene(1);
    ret.getEnvironment().start(testScene);
    ret.getWindow().dispose();
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(stream);
    ps.println("setLogData({");
    ps.println("logs: [");
    ps.println("['createImage',100,100,255,0,0,255,0],");
    ps.println("['clear',255,0,0,0],");
    ps.println("['drawImage',0,10,20],");
    ps.println("['forceRepaint'],");
    ps.println("],");
    ps.println("});");
    ps.close();
    assertEquals(stream.toString(), testScene.getRendererLog());
  }

  @Test
  public void testBuilderForCui() {
    GameGuiBuildResult<SampleEnvironment> ret =
        new GameGuiBuilder().buildForCui(SampleEnvironment.class);
    ret.getEnvironment().start(new TestScene(0));
  }
}


class TestScene extends SampleScene {
  private ByteArrayOutputStream byteArrayOutputStream;
  private int count;

  public TestScene(int count) {
    this.count = count;
    this.byteArrayOutputStream = new ByteArrayOutputStream();
  }

  public String getRendererLog() {
    return byteArrayOutputStream.toString();
  }

  @Override
  public void initialize() {
    if (getRenderer() != null) {
      getRenderer().startLogging(new PrintStream(byteArrayOutputStream));
    }
  }

  @Override
  public void release() {
    if (getRenderer() != null) {
      getRenderer().finishLogging();
    }
  }

  @Override
  public Scene<SampleEnvironment> run() {
    if (--count >= 0) {
      return this;
    } else {
      return null;
    }
  }
}
