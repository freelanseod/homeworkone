package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test delete", "logo delete", "comment delete"));
        }

        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
    }

}
