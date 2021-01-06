package addressbook.tests;

import addressbook.model.GroupData;
import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testCreationGroup() throws Exception {
        app.goToGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test group", "logo", "test comment"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
