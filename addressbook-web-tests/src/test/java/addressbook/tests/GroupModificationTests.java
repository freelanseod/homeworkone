package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeClass
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test modification").withHeader("test header").withFooter("test footer"));
        }
//        app.goTo().groupPage();
//        if (app.group().all().size() == 0) {
//            app.group().create(new GroupData().withName("test modification").withHeader("test header").withFooter("test footer"));
//        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups(); //app.group().all();
        GroupData modifiedGroup = before.iterator().next(); //take random group for modify

        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test group edit").withHeader("logo edit").withFooter("test comment edit");
        app.group().modify(group);

        Groups after = app.db().groups(); //app.group().all();
        Assert.assertEquals(after.size(), before.size()); //compare size of lists
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
