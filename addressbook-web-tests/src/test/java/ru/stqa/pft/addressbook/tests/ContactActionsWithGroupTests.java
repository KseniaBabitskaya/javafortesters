package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by fedotk on 4/7/2016.
 */
public class ContactActionsWithGroupTests extends TestBase {

  @BeforeClass
  public void ensurePrecondition() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
//    ContactData contact = contacts.iterator().next();
    if (contacts.size() == 0) {
      app.contact().create(new ContactData().withFirstname("qwe").withLastname("asd"));
    }
    if (groups.size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("hhh"));
    }
  }

  @Test(enabled = true)

  public void testContactAddToGroup() {
    app.goTo().homePage();
    ContactData contact = app.db().contacts().iterator().next();
    Groups allGroups = app.db().groups();
    Groups contactGroups = contact.getGroups();
    GroupData group;
    if (contactGroups.equals(allGroups)) {
      GroupData newGroup = new GroupData().withName("new");
      app.goTo().groupPage();
      app.group().create(newGroup);
      app.goTo().homePage();
      group = app.db().getGroupByName(newGroup.getName());
    } else {
      group = app.db().groups().iterator().next();
    }
    app.contact().addToGroup(contact, group.getName());
    app.goTo().homePage();

    assertThat(app.db().getContactById(contact.getId()).getGroups().contains(group), equalTo(true));

    app.contact().chooseGroup(group.getName(), "group");
    verifyGroupListInUI();
  }


  @Test(enabled = true)
  public void testContactRemoveFromGroup() {
    app.goTo().homePage();
    ContactData contact = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();
    Groups contactGroups = contact.getGroups();
    if (contactGroups.isEmpty()) {
      app.goTo().homePage();
      app.contact().addToGroup(contact, group.getName());
      app.goTo().homePage();
    }
    app.contact().removeFromGroup(contact, group.getName());
    app.goTo().homePage();
    assertThat(app.db().getContactById(contact.getId()).getGroups().contains(group), equalTo(false));
  }

}
