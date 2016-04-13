package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Ксюшенька on 26.03.2016.
 */
public class ContactAddressTest extends TestBase {

  @BeforeClass
  public void ensurePrecondition() {
    Contacts allContacts = app.db().contacts();
    app.goTo().homePage();
    if (allContacts.size() == 0) {
      ContactData newContact = new ContactData()
              .withFirstname("qwe").withLastname("asd").withAllAddresses("spb");
      app.contact().create(newContact);
      app.goTo().homePage();
    }
  }

  @Test(enabled = false)
  public void testContactAddress() {
    app.goTo().homePage();
    ContactData newContact;

    ContactData contact = app.db().contacts().iterator().next();
    String addr = contact.getAllAddresses();

    if (addr == null) {
      contact = new ContactData()
              .withFirstname("qwe").withLastname("asd").withAllAddresses("spb");
      app.contact().create(contact);
      contact = app.db().getContactByAddress(contact.getAllAddresses());
      app.goTo().homePage();
    }
    else{
      contact = app.db().getContactById(contact.getId());
    }

//    ContactData contact = app.db().contacts().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllAddresses(), equalTo(contactInfoFromEditForm.getAllAddresses()));
  }

}
