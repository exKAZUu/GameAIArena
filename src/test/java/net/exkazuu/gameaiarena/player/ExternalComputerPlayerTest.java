package net.exkazuu.gameaiarena.player;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class ExternalComputerPlayerTest {

  @Test
  public void runValidCommand() throws IOException {
    new ExternalComputerPlayer(new String[] {"java"});
  }

  @Test(expected = IOException.class)
  public void runInvalidCommand() throws IOException {
    new ExternalComputerPlayer(new String[] {"javaaaaa"});
  }

  @Test
  public void writeAndRead() throws IOException {
    ExternalComputerPlayer player =
        new ExternalComputerPlayer(new String[] {"java", "-cp", "SampleAI", "SampleJavaAI"});
    player.writeLine("test");
    assertEquals("test", player.readLine());
    player.writeLine("test2");
    assertEquals("test2", player.readLine());
  }

}
