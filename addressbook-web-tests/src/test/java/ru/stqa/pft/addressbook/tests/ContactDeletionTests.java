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
public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList(); //создаем список контактов до

    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Name1", "Name2", "test1"));
    }
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().submitContactDeletion();

    List<ContactData> after = app.getContactHelper().getContactList(); //создаем список контакто после

    before.remove(before.size()-1); //удаляем последний контакт

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());//сравниваем id для дальнейшей сортировки
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);

    app.getNavigationHelper().gotoHomePage();
  }


}
