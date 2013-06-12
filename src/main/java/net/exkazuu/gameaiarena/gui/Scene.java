package net.exkazuu.gameaiarena.gui;

import java.util.List;

import net.exkazuu.gameaiarena.key.MappedInputer;


public abstract class Scene<Env extends Environment> {

  private Env environment;

  public abstract void initialize();

  public abstract void release();

  public abstract Scene<Env> run();

  public abstract void draw();

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
