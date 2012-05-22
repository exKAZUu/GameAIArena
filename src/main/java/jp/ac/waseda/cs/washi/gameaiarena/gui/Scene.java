package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.util.List;

import jp.ac.waseda.cs.washi.gameaiarena.common.Environment;
import jp.ac.waseda.cs.washi.gameaiarena.key.MappedInputer;

public abstract class Scene<Env extends Environment> {

  private Env environment;

  public void initialize() {}

  public void release() {}

  public Scene<Env> run() {
    return this;
  }

  public void draw() {}

  public final Env getEnvironment() {
    return environment;
  }

  public final MappedInputer getInputer() {
    return environment.getInputer();
  }

  public final Renderer getRenderer() {
    return environment.getRenderer();
  }

  public final List<Renderer> getSubRenderers() {
    return environment.getSubRenderers();
  }

  final void setEnvironment(Env environment) {
    this.environment = environment;
  }
}
