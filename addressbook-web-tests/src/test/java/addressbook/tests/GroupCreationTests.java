package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testCreationGroup() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();

//        GroupData group = new GroupData("test group", "logo", "test comment");
        GroupData group = new GroupData().withName("test group").withHeader("test header").withFooter("test footer");
        app.group().initGroupCreation();
        app.group().fillGroupForm(group);
        app.group().submitGroupCreation();
        app.group().returnToGroupPage();

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1); //compare size of lists

        before.add(group); //add group which was modified

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //anon function
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
