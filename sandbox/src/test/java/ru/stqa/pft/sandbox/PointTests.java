package ru.stqa.pft.sandbox;

import ksy.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by fedotk on 2/26/2016.
 */
public class PointTests {

  @Test
  public void testDistance(){
    Point p = new Point();
    Point p1 = new Point(1,6);
    Point p2 = new Point(5, 2);
    Assert.assertEquals(p.distance(p1, p2), distanceToCheckResult(p1, p2));

    System.out.println("Expected result: " + distanceToCheckResult(p1, p2));
    System.out.println("Actual result: " + p.distance(p1, p2));
  }


  public static double distanceToCheckResult(Point p1, Point p2){
    return Math.sqrt((p1.a - p2.a)*(p1.a - p2.a)+ (p1.b -p2.b)*(p1.b -p2.b));
  }
}
