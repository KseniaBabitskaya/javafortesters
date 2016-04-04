package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    app.goTo().homePage();
    Contacts before = app.db().contacts();
//    Contacts before = app.contact().all();
//    File photo = new File("src/test/resources/pic.png");
//    ContactData contact = new ContactData().withFirstname("Name1").withLastname("Name2").withPhoto(photo);
    app.contact().create(contact);
    app.goTo().homePage();
    System.out.println(app.contact().count());
    assertThat(app.group().count(), equalTo(before.size() + 1));
//    Contacts after = app.contact().all();
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test(enabled = false)
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
