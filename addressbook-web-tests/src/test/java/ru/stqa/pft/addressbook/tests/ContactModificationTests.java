package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by fedotk on 3/2/2016.
 */
public class ContactModificationTests extends TestBase {
  @Test
  public void testsContactModification(){
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Name1", "Name2", "Name3", "Name4", "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().clickEditContact();
    app.getContactHelper().fillContactForm(new ContactData("Name21", "Name22", "Name23", "Name24", "test1"), false);
    app.getContactHelper().submitModification();
    app.getNavigationHelper().gotoHomePage();
  }
}
