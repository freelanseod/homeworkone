package addressbook.bdd;

import addressbook.appmanager.ApplicationManager;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.BrowserType;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupStepsDefinitions {
    private ApplicationManager applicationManager;
    private Groups groups;
    private GroupData newGroup;

    @Before
    public void init() throws IOException {
        applicationManager = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
        applicationManager.init();
    }

    @After
    public void stop() {
        applicationManager.stop();
        applicationManager = null;
    }

    @Given("^a set of groups$") //exact match is checked
    public void loadGroups() {
        groups = applicationManager.db().groups();
    }

    @When("^I create a new group with name (.+), header (.+) and footer (.+)$")
    public void createGroup(String name, String header, String footer) {
        applicationManager.goTo().groupPage();
        newGroup = new GroupData().withName(name).withHeader(header).withFooter(footer);
        applicationManager.group().create(newGroup);
    }

    @Then("^the new set of groups is equal to the old set with the added group$")
    public void verifyGroupCreated() {
        Groups newGroups = applicationManager.db().groups();
        assertThat(newGroups, equalTo(groups.withAdded(newGroup.withId(newGroups.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
