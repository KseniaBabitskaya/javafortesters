package ru.stqa.mantis.tests.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.mantis.tests.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by fedotk on 4/14/2016.
 */
public class RegistrationTests extends TestBase {
//    @BeforeMethod
//    public void startMailServer() {
//        app.mail().start();
//    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
//        String user = "qwe";
        String email = String.format("user%s@localhost.localdomain",now);
        String password = "asd";
        app.james().createUser(user, password);
        app.registration().start(user, email);
//        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 80000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

//    @AfterMethod(alwaysRun = true)
//    public void stopMailServer() {
//        app.mail().stop();
//    }
}
