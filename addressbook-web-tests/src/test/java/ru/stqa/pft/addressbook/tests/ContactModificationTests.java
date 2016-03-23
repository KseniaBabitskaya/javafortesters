package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by fedotk on 3/2/2016.
 */
public class ContactModificationTests extends TestBase {
  @Test(enabled = false)
  public void testsContactModification() {
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withFirstname("Name1").withLastname("Name2").withGroup("test1"));
    }

    List<ContactData> before = app.contact().list();

    app.goTo().homePage();
//    app.getContactHelper().selectContact(before.size() - 1);
    app.contact().clickEditContact(before.size() + 1);
    ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId()).withFirstname("Name2").withLastname("Name2").withGroup("test1");
    app.contact().fillContactForm(contact, false);
    app.contact().submitModification();
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();

    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}