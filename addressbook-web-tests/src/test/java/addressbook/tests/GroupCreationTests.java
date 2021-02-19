package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    //iterator of arrays of objects
    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[] {"test1", "header1", "footer1"});
        list.add(new Object[] {"test2", "header2", "footer2"});
        list.add(new Object[] {"test3", "header3", "footer3"});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testCreationGroup(String name, String header, String footer) {
        app.goTo().groupPage();
        Groups before = app.group().all();

        GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
        app.group().initGroupCreation();
        app.group().fillGroupForm(group);
        app.group().submitGroupCreation();
        app.group().returnToGroupPage();

        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        GroupData index = group.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()); //anon function get id from stream of groups set and find max id
        assertThat(after, equalTo(before.withAdded(index))); //hamcrest works with fluent interfaces
    }

}
