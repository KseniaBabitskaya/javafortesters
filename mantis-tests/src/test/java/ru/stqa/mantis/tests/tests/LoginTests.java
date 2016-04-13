package ru.stqa.mantis.tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.mantis.tests.appmanager.HttpSession;

import java.io.IOException;

/**
 * Created by fedotk on 4/13/2016.
 */
public class LoginTests extends TestBase {
  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    Assert.assertTrue(session.login("administrator", "root"));
    Assert.assertTrue(session.isLoggedInAs("administrator"));
  }
}
