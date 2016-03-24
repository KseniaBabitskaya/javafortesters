package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by fedotk on 3/2/2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (!(app.contact().all().size() == 0)) {
      app.contact().create(new ContactData().withFirstname("Name1").withLastname("Name2").withGroup("test1"));
    }
  }

  @Test
  public void testsContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
//    ContactData contact = new ContactData().withId(before.get(before.size() - 1).getId()).withFirstname("Name2").withLastname("Name2").withGroup("test1");
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Name2").withLastname("Name2").withGroup("test1");

    app.goTo().homePage();
    app.contact().modify(contact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();

    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.remove(before.size() - 1);
    before.add(contact);

//    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
//    before.sort(byId);
//    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}