package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by fedotk on 3/2/2016.
 */
public class ContactModificationTests extends TestBase {
  @Test
  public void testsContactModification() {
    app.getNavigationHelper().gotoHomePage();

//    ContactData contact = new ContactData("Name1", "Name2", "test1");
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name1", "Name2", null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().clickEditContact();

    ContactData contact = new ContactData((before.get(before.size() - 1).getId()), "Name11", "Name2", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitModification();
    app.getNavigationHelper().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}