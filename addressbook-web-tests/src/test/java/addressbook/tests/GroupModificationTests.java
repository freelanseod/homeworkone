package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test modification").withHeader("test header").withFooter("test footer"));
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = app.group().list();

        int index = before.size() - 1;
        GroupData group = new GroupData()
                .withId(before.get(index).getId()).withName("test group edit").withHeader("logo edit").withFooter("test comment edit");

        app.group().modify(index, group);

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size()); //compare size of lists

        before.remove(index);
        before.add(group); //add group which was modified

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //anon function
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
