package tests;

import model.Issue;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = app.helper().getIssues();

        Issue newIssue = new Issue().withSubject("test subject").withDescription("test description");
        int issueId = app.helper().createIssue(newIssue);

        Set<Issue> newIssues = app.helper().getIssues();

        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);

        app.helper().getIssueById(1);
    }

    @Test
    public void testCheckIssueStatus() throws IOException {
        skipIfNotFixed(2);
        Set<Issue> oldIssues = app.helper().getIssues();
    }

}
