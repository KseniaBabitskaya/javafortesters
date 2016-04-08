package ru.stqa.pft.addressbook.tests;

import org.apache.xpath.operations.String;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by fedotk on 4/7/2016.
 */
public class ContactActionsWithGroupTests extends TestBase {

//  @BeforeTest
//  public void ensurePreconditions() {
//  if (){
//    app.goTo().groupPage();
//    app.group().create(new GroupData().withName("test1"));
//  }}

  @Test(enabled = true)

  public void testContactAddToGroup() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData contact = before.iterator().next();
    app.contact().addToGroup(contact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(contact).withAdded(contact)));
  }

//  @BeforeTest
//
//  public void ensurePreconditionsS() {
//    if(){
//      app.goTo().homePage();
//      app.contact().create(new ContactData().withFirstname("A").withLastname("B"));
//
//      Contacts before = app.db().contacts();
//      ContactData contact = before.iterator().next();
//      app.contact().addToGroup(contact);
//    }
//
//  }

  @Test(enabled = true)
  public void testContactRemoveFromGroup() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData contact = before.iterator().next();
    app.contact().removeFromGroup(contact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(contact).withAdded(contact)));
  }

}
