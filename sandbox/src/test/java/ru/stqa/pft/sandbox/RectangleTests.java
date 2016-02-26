package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by fedotk on 2/26/2016.
 */
public class RectangleTests {

  @Test
  public void testArea(){
    Rectangle r = new Rectangle(6, 7);
    Assert.assertEquals(r.area(), 42.0);
    System.out.println("Actual: " + r.area() + "; " + "Expected: " + 42.0);
  }
}
