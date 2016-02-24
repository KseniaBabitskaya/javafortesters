package ksy;

//import static ksy.Point.distance;

/**
 * Created by fedotk on 2/24/2016.
 */
public class Point2 {
  public static void main(String args[]){

    Point p = new Point();
    Point p1 = new Point(1, 9);
    Point p2 = new Point(5, 7);

//    p1.a = 5;
//    p2.b = 7;

//    System.out.println(p1.a + " --- " + p2.b + " >>> " + distance(p1, p2));
    System.out.println(p1.a + " --- " + p2.b + " >>> " + p.distance(p1, p2));
  }


}
