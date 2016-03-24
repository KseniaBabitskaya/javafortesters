package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

    @Test
    public void ContactCreationTest() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();

        ContactData contact = new ContactData().withFirstname("Name1").withLastname("Name2").withGroup("test1");
        app.contact().create(contact);
        app.goTo().homePage();

        Set<ContactData> after = app.contact().all();
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

    }

}
