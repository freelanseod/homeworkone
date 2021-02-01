package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test modification").withHeader("test header").withFooter("test footer"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next(); //take random group for modify

        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test group edit").withHeader("logo edit").withFooter("test comment edit");

        app.group().modify(group);

        Groups after = app.group().all();
        Assert.assertEquals(after.size(), before.size()); //compare size of lists
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
