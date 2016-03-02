package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by fedotk on 3/2/2016.
 */
public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().submitContactDeletion();
    app.getNavigationHelper().gotoHomePage();
  }


}
