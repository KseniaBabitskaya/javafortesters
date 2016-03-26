package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @Test (enabled = true)
  public void testContactCreationTest() {
    app.goTo().homePage();
    Contacts before = app.contact().all();

    ContactData contact = new ContactData().withFirstname("Name1").withLastname("Name2").withGroup("test1")
            .withHomePhone(null).withMobilePhone(null).withWorkPhone(null);
    app.contact().create(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
//    assertThat(after, equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test (enabled = false)
  public void testBadContactCreationTest() {
    app.goTo().homePage();
    Contacts before = app.contact().all();

    ContactData contact = new ContactData().withFirstname("Name1'").withLastname("Name2").withGroup("test1");
    app.contact().create(contact);
//    app.goTo().homePage();

    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));

  }
}
