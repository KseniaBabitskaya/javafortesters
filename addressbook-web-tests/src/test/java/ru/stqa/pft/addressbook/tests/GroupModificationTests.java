package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by fedotk on 3/2/2016.
 */
public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (!(app.group().list().size() == 0)) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData().withID(before.get(index).getId()).withName("test11").withHeader("test2").withFooter("test3");

        app.group().modify(index, group);

        app.goTo().groupPage();

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(group);

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
//    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); //сравнение множеств (неупорядоченный список)
        Assert.assertEquals(before, after);
    }

}
