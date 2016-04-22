package ru.stqa.mantis.tests.appmanager;

import org.openqa.selenium.By;

/**
 * Created by fedotk on 4/14/2016.
 */
public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String user, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
