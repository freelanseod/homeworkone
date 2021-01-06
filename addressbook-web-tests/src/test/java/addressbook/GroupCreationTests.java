package addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testCreationGroup() throws Exception {
        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test group", "logo", "test comment"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
