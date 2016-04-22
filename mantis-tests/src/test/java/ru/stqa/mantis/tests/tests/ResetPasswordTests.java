package ru.stqa.mantis.tests.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.mantis.tests.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Ксюшенька on 22.04.2016.
 */
public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPasswod() throws IOException, MessagingException {
        app.registration().login("administrator", "root");
        app.registration().openManageUsersTab();
        String a = app.registration().selectUser();
        String username = a.replaceAll(" .+", "");
        String email = a.replaceAll(".+ ", "");
        app.registration().resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String newPassword = "qwe";
        String resetLink = findResetLink(mailMessages, email);
        app.registration().finish(resetLink, newPassword);
        assertTrue(app.newSession().login(username, newPassword));
    }

    private String findResetLink( List<MailMessage> mailMessages, String email ) {
        MailMessage mailMessage = mailMessages.stream().filter(( m ) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}

