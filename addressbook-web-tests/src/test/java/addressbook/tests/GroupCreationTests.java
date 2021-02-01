package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testCreationGroup() {
        app.goTo().groupPage();
        Groups before = app.group().all();

        GroupData group = new GroupData().withName("test group").withHeader("test header").withFooter("test footer");
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
