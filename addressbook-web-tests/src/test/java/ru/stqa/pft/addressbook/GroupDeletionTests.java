package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletion() {

        gotoGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }

    private void deleteSelectedGroups() {
        wd.findElement(By.name("delete")).click();
    }

    private void selectGroup() {
        wd.findElement(By.name("selected[]")).click();
    }


}
