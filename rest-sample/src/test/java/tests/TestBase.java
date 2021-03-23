package tests;

import appmanager.ApplicationManager;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager();

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

    boolean isIssueOpen(int issueId) throws IOException {
        int status = app.helper().getIssueById(issueId);
        if (status == 3) {
            return false;
        }
        else {
            return true;
        }
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
