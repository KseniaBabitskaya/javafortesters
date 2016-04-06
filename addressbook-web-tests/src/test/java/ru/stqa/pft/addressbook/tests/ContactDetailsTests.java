package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by Ксюшенька on 26.03.2016.
 */
public class ContactDetailsTests extends TestBase {

  @Test(enabled = true)
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromDetails = app.contact().infoFromDetailsForm(contact);
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    MatcherAssert.assertThat(mergedContactData(contactInfoFromDetails), CoreMatchers.equalTo(mergedContactData(contactInfoFromEditForm)));
  }

  private String mergedContactData(ContactData contactData) {
    return Arrays.asList(contactData.getFirstname(), contactData.getLastname(), contactData.getAllAddresses(),
            contactData.getHomePhone(), contactData.getMobilePhone(), contactData.getWorkPhone(),
            contactData.getEmail(), contactData.getEmail2(), contactData.getEmail3())
            .stream().filter((s -> !s.equals("")))
            .map(ContactDetailsTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s+", " ").replaceAll("[-()]", "").replaceAll("^\\s+", "");
  }
}

