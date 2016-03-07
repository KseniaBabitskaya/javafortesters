package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

    @Test
    public void ContactCreationTest() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().createContact(new ContactData("Name1", "Name2", "Name3", "Name4", "test1"));
        app.getNavigationHelper().gotoHomePage();
    }

}
