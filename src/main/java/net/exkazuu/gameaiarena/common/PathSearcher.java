package net.exkazuu.gameaiarena.common;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import net.exkazuu.gameaiarena.api.Direction4;
import net.exkazuu.gameaiarena.api.Point2;


public final class PathSearcher {
  public static <T> Direction4[] getPath(Point2 from, Point2 to,
      final List<Direction4> directionList, PassPointChooser chooser) {
    // check arguments
    if (from == null) {
      throw new NullPointerException("from must not be null.");
    }
    if (to == null) {
      throw new NullPointerException("to must not be null.");
    }
    if (chooser == null) {
      throw new NullPointerException("chooser must not be null.");
    }

    // already reached?
    if (from.equals(to)) {
      return new Direction4[0];
    }

    HashMap<Point2, Direction4> walked = new HashMap<Point2, Direction4>();
    PriorityQueue<PassPoint> queue = new PriorityQueue<PassPoint>(32, new Comparator<PassPoint>() {
      @Override
      public int compare(PassPoint p1, PassPoint p2) {
        if (p1.getMoveCost() == p2.getMoveCost()) {
          return -(directionList.indexOf(p1.getLastDirection()) - directionList.indexOf(p2
              .getLastDirection()));
        }
        return p1.getMoveCost() - p2.getMoveCost();
      }
    });
    queue.add(new PassPoint(from));

    while (!queue.isEmpty()) {
      final PassPoint state = queue.poll();
      // 訪問済みである場合は何もしない
      if (walked.containsKey(state.getLocation())) {
        continue;
      }

      // 訪問済みであることを設定
      walked.put(state.getLocation(), state.getLastDirection());
      // 目的地に到達したかどうか判定
      if (state.getLocation().equals(to)) {
        final Direction4[] result = new Direction4[state.getMoveCost()];
        int index = state.getMoveCost();
        while (--index >= 0) {
          result[index] = walked.get(to);
          assert (result[index] != null);
          to = to.sub(result[index].toPoint());
        }
        return result;
      }
      // 次の訪問先を登録
      for (PassPoint next : chooser.getNextPassPoints(state)) {
        queue.add(next);
      }
    }

    // 到達経路が存在しない
    return null;
  }

  private PathSearcher() {}
}
