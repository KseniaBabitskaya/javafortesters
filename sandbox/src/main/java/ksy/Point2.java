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

    System.out.println("функция работает: " + "a = " + p1.a + "& b = " + p2.b + " >>> " + p.distance(p1, p2));
    System.out.println("метод работает: "  + "a = " + p1.a + "& b = " + p2.b + " >>> " + distance(p1, p2));

//    System.out.println(p.distance());

  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt((p1.a - p2.a)*(p1.a - p2.a)+ (p1.b -p2.b)*(p1.b -p2.b));
  }



}
