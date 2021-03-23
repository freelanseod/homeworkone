package tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import model.Issue;
import model.Project;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @BeforeClass
    public void checkJavaVersion() {
        System.out.println("Use java 8");
    }

    @Test
    public void testGetProjects() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println("length " + projects.size());

        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();

        Issue issue = new Issue().withSummary("new test issue").withDescription("test description").withProject(projects.iterator().next());
        Issue issueCreated = app.soap().addIssue(issue);
        assertEquals(issue.getDescription(), issueCreated.getDescription());
    }

    @Test
    public void testCheckIssueStatus() throws RemoteException, ServiceException, MalformedURLException {
        IssueData issue = app.soap().getIssues(0000006);
        skipIfNotFixed(issue.getId().intValue());
    }

}
