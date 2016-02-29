package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {
        app.gotoGroupPage();
        selectGroup();
        deleteSelectedGroups();
        app.returnToGroupPage();
    }

    private void deleteSelectedGroups() {
        app.wd.findElement(By.name("delete")).click();
    }

    private void selectGroup() {
        app.wd.findElement(By.name("selected[]")).click();
    }


}
