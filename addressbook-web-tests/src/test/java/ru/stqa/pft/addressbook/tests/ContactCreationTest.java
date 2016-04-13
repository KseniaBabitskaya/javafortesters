package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsFromJson", enabled = true)
  public void testContactCreationTest(ContactData contact) {
    Contacts contactsBefore = app.db().contacts();
    Groups groups = app.db().groups();
    app.goTo().homePage();

//    Contacts before = app.contact().all();
//    File photo = new File("src/test/resources/pic.png");
//    ContactData contact = new ContactData().withFirstname("Name1").withLastname("Name2").withPhoto(photo);

    app.contact().create(contact.inGroup(groups.iterator().next()));
    app.goTo().homePage();

    Contacts contactsAfter = app.db().contacts();
    assertThat(contactsAfter.size(), equalTo(contactsBefore.size() + 1));
    assertThat(contactsAfter, equalTo(contactsBefore
            .withAdded(contact.withId(contactsAfter.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test(enabled = false)
  public void testBadContactCreationTest() {
    app.goTo().homePage();
    Contacts before = app.contact().all();

    ContactData contact = new ContactData().withFirstname("Name1'").withLastname("Name2");
    app.contact().create(contact);
//    app.goTo().homePage();

    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
    verifyGroupListInUI();
  }
}
