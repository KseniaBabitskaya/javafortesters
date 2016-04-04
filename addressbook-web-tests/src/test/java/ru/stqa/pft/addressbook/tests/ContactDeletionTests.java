package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by fedotk on 3/2/2016.
 */
public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Name1").withLastname("Name2").withGroup("test1"));
    }
  }

  @Test(enabled = true)
  public void testContactDeletion() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
//    Contacts before = app.contact().all(); //создаем список контактов до
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
//    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
//    Contacts after = app.contact().all(); //создаем список контакто после
//    Assert.assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
