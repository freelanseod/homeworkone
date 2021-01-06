package addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testDeleteGroup() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
    }

}
