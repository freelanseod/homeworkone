package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeClass
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test delete").withHeader("test header").withFooter("test footer"));
        }
    }

    @Test
    public void testDeleteGroup() {
        Groups before = app.db().groups();
        GroupData deleteGroup = before.iterator().next(); //return the first found element of the set

        app.group().delete(deleteGroup); //any of group even from the middle of set

        Groups after = app.db().groups();
        Assert.assertEquals(after.size(), before.size() - 1); //compare size of lists
        assertThat(after, equalTo(before.without(deleteGroup)));

        verifyGroupListInUI();
    }

}
