package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by fedotk on 2/29/2016.
 */
public class NavigationHelper extends HelperBase{
  public FirefoxDriver wd;


  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
      click(By.linkText("groups"));
  }

  public void gotoHomePage(){
    click(By.linkText("home"));
  }
}
