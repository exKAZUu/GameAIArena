package net.exkazuu.gameaiarena.gui.builder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

import net.exkazuu.gameaiarena.gui.Environment;
import net.exkazuu.gameaiarena.gui.GamePanel;
import net.exkazuu.gameaiarena.gui.JGamePanel;

public abstract class EnvironmentCreator<Env extends Environment> {
  public abstract Env createEnvironment(JFrame window, JGamePanel gamePanel);

  public static <Env extends Environment> EnvironmentCreator<Env> defaultCreator(
      final Class<Env> clazz) {
    return new EnvironmentCreator<Env>() {
      @Override
      public Env createEnvironment(JFrame window, JGamePanel gamePanel) {
        Constructor<Env> constructor;
        try {
          constructor = clazz.getConstructor(GamePanel.class);
        } catch (SecurityException e) {
          throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
          throw new RuntimeException(e);
        }

        Object[] args = {gamePanel};
        try {
          return constructor.newInstance(args);
        } catch (IllegalArgumentException e) {
          throw new RuntimeException(e);
        } catch (InstantiationException e) {
          throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
          throw new RuntimeException(e);
        }
      }
    };
  }
}
