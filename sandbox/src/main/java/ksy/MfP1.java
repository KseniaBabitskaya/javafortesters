package ksy;

/**
 * Created by fedotk on 2/24/2016.
 */
public class MfP1 {
  public static void main(String args[]) {
//    hello("Ksy");
//
//    Square s = new Square();
//    s.a = 5;
//    System.out.println(s.a + " = " + area(s));
//
//
//    Rec r = new Rec();
//    r.a = 4;
//    r.b = 5;
//    System.out.println(r.a + " * " + r.b + " = " + area(r));
//  }
//
//  public static void hello(String a){
//    System.out.println("Hello " + a);
//  }
//
//  public static double area(Square s){
//    return s.a * s.a;
//  }
//
//  public static double area(Rec r){
//    return r.a * r.b;
//  }

    Square s = new Square(2);
    System.out.println(s.a + " " + s.area());

    Rec r = new Rec(5,6);
    System.out.println(r.a + "---" + r.b + " = " + r.area());
  }


}
