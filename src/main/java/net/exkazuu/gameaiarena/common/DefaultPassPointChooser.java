package net.exkazuu.gameaiarena.common;

import java.util.ArrayList;

import net.exkazuu.gameaiarena.api.Direction4;


public class DefaultPassPointChooser implements PassPointChooser {
  /*
   * (non-Javadoc)
   * 
   * @see com.google.code.gameaiarena.api.PassPointChooser#getNextPassPoints(com
   * .google.code.gameaiarena.api.PassPoint)
   */
  @Override
  public Iterable<PassPoint> getNextPassPoints(PassPoint lastPoint) {
    ArrayList<PassPoint> results = new ArrayList<PassPoint>();
    for (Direction4 direction : Direction4.values()) {
      results.add(new PassPoint(lastPoint, direction, 1));
    }
    return results;
  }
}
