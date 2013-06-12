package net.exkazuu.gameaiarena.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Point2Test {
  @Test
  public void parse() {
    Point2[] ps =
        {new Point2(0, 0), new Point2(1, 2), new Point2(-1, 2), new Point2(1, -2),
            new Point2(-1, -2)};

    for (Point2 p : ps) {
      assertEquals(p, Point2.parse(p.toString()));
    }
  }

  @Test
  public void parseVariousFormats() {
    assertEquals(new Point2(1, 2), Point2.parse("  1 ,  2  "));
    assertEquals(new Point2(1, 2), Point2.parse(" +1 , +2 "));
    assertEquals(new Point2(1, 2), Point2.parse(" (+1 , +2) "));
  }
}
