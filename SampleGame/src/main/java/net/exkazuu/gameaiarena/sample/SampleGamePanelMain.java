package net.exkazuu.gameaiarena.sample;

import java.awt.event.KeyEvent;

import net.exkazuu.gameaiarena.gui.builder.GameGuiBuildResult;
import net.exkazuu.gameaiarena.gui.builder.GameGuiBuilder;
import net.exkazuu.gameaiarena.key.AwtKeyMemorizer;
import net.exkazuu.gameaiarena.key.MappedInputer;
import net.exkazuu.gameaiarena.sample.entity.GameKey;
import net.exkazuu.gameaiarena.sample.scenes.GameScene;
import net.exkazuu.gameaiarena.sample.scenes.StartScene;

public class SampleGamePanelMain {
  public static void main(String[] args) {
    // Initialize game environment including GUI components
    GameGuiBuildResult<SampleEnvironment> ret =
        new GameGuiBuilder()
            .setTitle("Sample Game")
            .setWindowSize(1000, 600)
            .setPanelSize(800, 400)
            .setWindowCreator(new SampleWindowCreator())
            .buildForGui(SampleEnvironment.class);

    // Initialize key board settings
    MappedInputer inputer = ret.getEnvironment().getInputer();
    AwtKeyMemorizer keyMemorizer = ret.getPanel().getKeyMemorizer();
    inputer.add(GameKey.UP, keyMemorizer.getKeyPressChecker(KeyEvent.VK_UP));
    inputer.add(GameKey.DOWN, keyMemorizer.getKeyPressChecker(KeyEvent.VK_DOWN));
    inputer.add(GameKey.LEFT, keyMemorizer.getKeyPressChecker(KeyEvent.VK_LEFT));
    inputer.add(GameKey.RIGHT, keyMemorizer.getKeyPressChecker(KeyEvent.VK_RIGHT));
    // Use the enter and space keys as our ENTER button
    inputer.add(GameKey.ENTER, keyMemorizer.getKeyPressChecker(KeyEvent.VK_ENTER));
    inputer.add(GameKey.ENTER, keyMemorizer.getKeyPressChecker(KeyEvent.VK_SPACE));
    inputer.add(GameKey.ESCAPE, keyMemorizer.getKeyPressChecker(KeyEvent.VK_ESCAPE));

    // Construct scenes combining each other
    GameScene gameScene = new GameScene(null);
    StartScene startScene = new StartScene(gameScene);
    
    // Start your game
    ret.getEnvironment().start(startScene);
    
    // Dispose GUI components after the game
    ret.getWindow().dispose();
  }
}
