package net.exkazuu.gameaiarena.gui;


public abstract class DefaultScene<Env extends Environment> extends Scene<Env> {

  public void initialize() {}

  public void release() {}

  public DefaultScene<Env> run() {
    return this;
  }

  public void draw() {}
}
