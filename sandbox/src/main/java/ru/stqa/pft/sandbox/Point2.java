package ru.stqa.pft.sandbox;

//import static ksy.Point.distance;

/**
 * Created by fedotk on 2/24/2016.
 */public class Point2 {
  public static void main(String args[]){

    Point p = new Point();

    Point p1 = new Point(7, 6);
    Point p2 = new Point(3, 8);

    System.out.println("функция работает >>> " +  + p.distance(p1, p2));
    System.out.println("метод работает >>> "  +  + distance(p1, p2));
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt((p1.a - p2.a)*(p1.a - p2.a)+ (p1.b -p2.b)*(p1.b -p2.b));
  }
}
