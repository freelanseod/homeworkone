package addressbook;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testDeleteGroup() throws Exception {
        goToGroupPage();
        selectGroup();
        deleteSelectedGroup();
        returnToGroupPage();
    }

}
