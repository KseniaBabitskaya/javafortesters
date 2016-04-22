package ru.stqa.mantis.tests.appmanager;

import org.openqa.selenium.By;

/**
 * Created by fedotk on 4/14/2016.
 */
public class RegistrationHelper extends HelperBase {

    private String username;

    public RegistrationHelper( ApplicationManager app ) {
        super(app);
    }

    public void start( java.lang.String user, java.lang.String email ) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), user);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish( java.lang.String confirmationLink, java.lang.String password ) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }


    public void login( java.lang.String username, java.lang.String password ) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void openManageUsersTab() {
//        wd.findElement(By.cssSelector("a[href='/mantisbt-1.2.19/manage_overview_page.php']").linkText("Manage")).click();
        click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_overview_page.php']"));
        click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']"));
    }

    public String selectUser() {
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=3']"));
        String username = wd.findElement(By.cssSelector("input[name='username']")).getAttribute("value");
        String email = wd.findElement(By.cssSelector("input[name='email']")).getAttribute("value");
        return username + " " + email;
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
