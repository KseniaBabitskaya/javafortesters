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
public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (!(app.contact().all().size() == 0)) {
      app.contact().create(new ContactData().withFirstname("Name1").withLastname("Name2").withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all(); //создаем список контактов до
    ContactData deletedContact= before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all(); //создаем список контакто после

    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedContact); //удаляем последний контакт
    Assert.assertEquals(before, after);
  }


}
