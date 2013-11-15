package net.exkazuu.gameaiarena.sample.entity;

import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.exkazuu.gameaiarena.gui.Renderer;

public class Images {
  private Images() {}

  public static void prefetch(Renderer renderer) {
    for (Method method : new Images().getClass().getMethods()) {
      if (!method.getName().startsWith("load")) {
        continue;
      }

      try {
        method.invoke(null, renderer);
      } catch (IllegalArgumentException e) {} catch (IllegalAccessException e) {} catch (InvocationTargetException e) {}
    }
  }

  private static Image _chara;

  public static Image chara(Renderer renderer) {
    if (_chara == null) {
      _chara = renderer.loadImage("images/chara.png");
    }

    return _chara;
  }
}
