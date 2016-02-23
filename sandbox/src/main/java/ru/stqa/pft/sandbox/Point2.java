package ru.stqa.pft.sandbox;

/**
 * Created by Ксюшенька on 23.02.2016.
 */
public class Point2 {

  public static void main (String args[]) {

    Point p = new Point();

    Point p1 = new Point(1, 9);
    Point p2 = new Point(5, 7);

    System.out.println("Расстояние между точками " + p.distance(p1, p2));
    System.out.println("Расстояние между точками " + distance2(p1, p2));
  }

    public static double distance2(Point p1, Point p2){
      return Math.sqrt((p1.a - p2.a)*(p1.a - p2.a) + (p1.b -p2.b)*(p1.b -p2.b));
    }
}
