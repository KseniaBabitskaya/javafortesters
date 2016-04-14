package ru.stqa.mantis.tests.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by fedotk on 4/14/2016.
 */
public class RegistrationHelper {
  private final ApplicationManager app;
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
  }

  public void  start(String username, String emain){
    wd.get(app.getProperty("web.baseUrl") + ("/signup_page.php"));
  }
}
