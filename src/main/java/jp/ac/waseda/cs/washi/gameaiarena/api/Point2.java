package jp.ac.waseda.cs.washi.gameaiarena.api;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * int型で指定される(x, y)座標空間における位置を表すイミュータブルなPoint型です。
 */
public class Point2 implements Comparable<Point2>, Serializable {
  /**
	 * 
	 */
  private static final long serialVersionUID = 4512703319714201609L;

  /**
   * (0, 0)から指定されたx,y座標の範囲内に存在するすべてのPointのリストを返します。
   * 
   * @param endX x座標の終端位置（含まれない）
   * @param endY y座標の終端位置（含まれない）
   * @return (0,0)から指定されたx,y座標の範囲内に存在するすべてのPointのリスト
   */
  public static ArrayList<Point2> getPoints(int endX, int endY) {
    return getPoints(0, 0, endX, endY);
  }

  /**
   * 指定された範囲内に存在するすべてのPointのリストを返します。
   * 
   * @param startX x座標の開始位置
   * @param startY y座標の開始位置
   * @param endX x座標の終端位置（含まれない）
   * @param endY y座標の終端位置（含まれない）
   * @return 指定された範囲内に存在するすべてのPointのリスト
   */
  public static ArrayList<Point2> getPoints(int startX, int startY, int endX, int endY) {
    final ArrayList<Point2> result = new ArrayList<Point2>();
    for (int y = startY; y < endY; y++) {
      for (int x = startX; x < endX; x++) {
        result.add(new Point2(x, y));
      }
    }
    return result;
  }

  public final int x, y;

  /**
   * 原点で初期化を行うコンストラクタです。
   */
  public Point2() {
    this(0, 0);
  }

  /**
   * 指定された(x, y)座標で初期化を行うコンストラクタです。
   * 
   * @param x x座標
   * @param y y座標
   */
  public Point2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * 指定されたjava.awt.Point型と同じ位置で初期化を行うコンストラクタです。
   * 
   * @param that java.awt.Point型のオブジェクト
   */
  public Point2(java.awt.Point that) {
    this(that.x, that.y);
  }

  /**
   * 指定されたPoint型と同じ位置で初期化を行うコンストラクタです。
   * 
   * @param that Point型のオブジェクト
   */
  public Point2(Point2 that) {
    this(that.x, that.y);
  }

  /**
   * Point(this.x + that.x, this.y + that.y)となるPoint型を返します。
   * 
   * @param that このPoint型に加算するPoint型
   * @return このPoint型に引数のPoint型を加算した結果
   */
  public Point2 add(Point2 that) {
    return new Point2(x + that.x, y + that.y);
  }

  @Override
  public int compareTo(Point2 that) {
    if (x == that.x) {
      if (y == that.y) {
        return 0;
      }
      return y < that.y ? -1 : 1;
    }
    return x < that.x ? -1 : 1;
  }

  /**
   * ２つのPoint型が等しいかどうかを判定します。
   * 
   * @param that このPoint型と比較を行うオブジェクト
   * @return 比較したオブジェクトがPoint型で等しい値を持つ場合はtrue、そうでない場合はfalse
   */
  @Override
  public boolean equals(Object that) {
    if (!(that instanceof Point2)) {
      return false;
    }
    return this.equals((Point2) that);
  }

  /**
   * ２つのPoint型が等しいかどうかを判定します。
   * 
   * @param that このPoint型と比較を行うオブジェクト
   * @return 比較したオブジェクトがPoint型で等しい値を持つ場合はtrue、そうでない場合はfalse
   */
  public boolean equals(Point2 that) {
    return that != null && that.x == x && that.y == y;
  }

  /**
   * Returns the manhattan distance between this and specified points.
   * 
   * @param that the point to calculate the manhattan distance
   * @return the manhattan distance between this and specified points
   */
  public int getManhattanDistance(Point2 that) {
    return Math.abs(x - that.x) + Math.abs(y - that.y);
  }

  /**
   * このPointが持つハッシュコードを返します。
   * 
   * @return このPointが持つハッシュコード
   */
  @Override
  public int hashCode() {
    return x ^ y;
  }

  /**
   * This method is deprecated so please use move method in Direction4. Returns the moved location
   * from this position with the specified direction.
   * 
   * @param direction the specified direction to move
   * @return the moved location
   */
  @Deprecated
  public Point2 move(Direction4 direction) {
    return new Point2(x + direction.dx, y + direction.dy);
  }

  /**
   * Point(this.x - that.x, this.y - that.y)となるPoint型を返します。
   * 
   * @param that このPoint型から減算するPoint型
   * @return このPoint型から引数のPoint型を減産した結果
   */
  public Point2 sub(Point2 that) {
    return new Point2(x - that.x, y - that.y);
  }

  /**
   * このPointの(x, y)座標空間における位置の文字列表現を返します。 このメソッドはデバッグにおける使用のみを意図しており、
   * 返される文字列の内容やフォーマットは実装により異なる可能性があります。 返される文字列はnullではありませんが空である可能性があります。
   * 
   * @return このPointの文字列表現
   */
  @Override
  public String toString() {
    return "{ X = " + x + ", Y = " + y + " }";
  }
}
