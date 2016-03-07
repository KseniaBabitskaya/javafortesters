package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by fedotk on 3/2/2016.
 */
public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Name1", "Name2", "Name3", "Name4", "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().submitContactDeletion();
    app.getNavigationHelper().gotoHomePage();
  }


}
