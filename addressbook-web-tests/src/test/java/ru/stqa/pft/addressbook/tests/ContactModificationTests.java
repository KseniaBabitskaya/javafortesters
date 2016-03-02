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
    app.getContactHelper().selectContact();
    app.getContactHelper().clickEditContact();
    app.getContactHelper().fillContactCreationForm(new ContactData("Name21", "Name22", "Name23", "Name24"));
    app.getContactHelper().submitModification();
    app.getNavigationHelper().gotoHomePage();
  }
}
