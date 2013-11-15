package net.exkazuu.gameaiarena.sample.entity;

import net.exkazuu.gameaiarena.api.Point2;

public class Chara {
  private Point2 _location;

  public Chara(Point2 location) {
    _location = location;
  }

  public Point2 getLocation() {
    return _location;
  }

  public void move(Point2 dp) {
    _location = _location.add(dp);
  }
}
