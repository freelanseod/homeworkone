package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test delete", "logo delete", "comment delete"));
        }
    }

    @Test
    public void testDeleteGroup() {
        List<GroupData> before = app.group().list();

        int index = before.size() - 1;
        app.group().delete(index);

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1); //compare size of lists

        before.remove(index); //delete from List<GroupData> before
        Assert.assertEquals(before, after);
    }

}
