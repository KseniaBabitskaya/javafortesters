package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ксюшенька on 01.03.2016.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
//        type(By.name("group"), contactData.getGroup());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

//        click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }
  public void modify(ContactData contact) {
//    initContatModification(contact.getId());
//    initContatModification();
    initContactModificationByRowIndex(contact.getId());
//    selectContactById(contact.getId());
    fillContactForm(contact, false);
    submitModification();
    returnToHomePage();
  }

  private void returnToHomePage() {
    click(By.xpath("//div[@class='msgbox']//a[.='home page']"));
  }

  public void submitContactDeletion() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    wd.switchTo().alert().accept();

  }

//  public void initContatModification(int number) {
//    String locator = "//table[@id='maintable']/tbody/tr[" + number + "]/td[8]/a/img";
//    click(By.xpath(locator));
//  }
  public void initContatModification() {
//    String locator = "//table[@id='maintable']/tbody/tr[2]/td[8]/a/img";
    String locator = "/html/body/div/div[4]/form[2]/table/tbody/tr[2]";
    click(By.xpath(locator));
  }

  public void initContactModificationByRowIndex(int rowIndex) {
    click(By.xpath(".//*[@id='maintable']/tbody/tr["+ rowIndex +"]/td[7]/a/img"));
  }


  public void submitModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public boolean isThereAContact() {
//        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    return isElementPresent(By.name("selected[]"));
  }

//  public void modify(ContactData contact, int id) {
//
//    initContatModification(id);
//    fillContactForm(contact, false);
//    submitModification();
//  }
  public void delete(int index) {
    selectContact(index);
    submitContactDeletion();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    submitContactDeletion();
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> rows = wd.findElements(By.tagName("tr"));
    for (int i = 1; i < rows.size(); i++) {
      String lastname = rows.get(i).findElements(By.tagName("td")).get(1).getText();
      String firstname = rows.get(i).findElements(By.tagName("td")).get(2).getText();
      int id = Integer.parseInt(rows.get(i).findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
      contacts.add(contact);
    }
    return contacts;
  }


}
