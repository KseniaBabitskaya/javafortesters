package ksy;

/**
 * Created by fedotk on 2/24/2016.
 */
public class Point {
  double a;
  double b;

  public Point(double a, double b){
    this.a = a;
    this.b = b;
  }

  public Point() {
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt((p1.a - p2.a)*(p1.a - p2.a)+ (p1.b -p2.b)*(p1.b -p2.b));
  }

//  Почему не работает такой вариант, хотя в занятии был похожий пример?
//  public double distance(){
//    return Math.sqrt((this.a - this.a)*(this.a - this.a)+ (this.b -this.b)*(this.b -this.b));
//  }

}

