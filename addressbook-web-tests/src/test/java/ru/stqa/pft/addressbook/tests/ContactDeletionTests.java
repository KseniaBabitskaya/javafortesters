package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by fedotk on 3/2/2016.
 */
public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (!(app.contact().list().size() == 0)) {
      app.contact().create(new ContactData("Name1", "Name1", "test1"));
    }
  }

  @Test
  public void testContactDeletion() {
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().create(new ContactData("Name1", "Name2", "test1"));
    }
    List<ContactData> before = app.contact().list(); //создаем список контактов до
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().homePage();

    List<ContactData> after = app.contact().list(); //создаем список контакто после
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1); //удаляем последний контакт
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());//сравниваем id для дальнейшей сортировки
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
