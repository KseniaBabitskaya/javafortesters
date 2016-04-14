package ru.stqa.mantis.tests.tests;

import org.testng.annotations.Test;

/**
 * Created by fedotk on 4/14/2016.
 */
public class RegistrationTests extends TestBase{
  @Test
  public void testRegistration(){
    app.registration().start("user1", "user1@localhost.localdomain");
  }
}
