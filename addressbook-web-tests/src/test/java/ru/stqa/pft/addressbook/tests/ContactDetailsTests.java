package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ксюшенька on 26.03.2016.
 */
public class ContactDetailsTests extends TestBase{

    @Test(enabled = false)
    public void testContactDetails(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromDetails = app.contact().infoFromDetailsForm(contact);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contactInfoFromDetails, equalTo(contactInfoFromEditForm));
    }

}

