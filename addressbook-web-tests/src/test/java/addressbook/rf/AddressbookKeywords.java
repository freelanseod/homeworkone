package addressbook.rf;

import addressbook.appmanager.ApplicationManager;
import addressbook.model.GroupData;
import org.openqa.selenium.remote.BrowserType;

import java.io.IOException;

public class AddressbookKeywords {
    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    private ApplicationManager applicationManager;

    public void initApplicationManager() throws IOException { //init keyword from groups.robot
        applicationManager = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
        applicationManager.init();
    }

    public void stopApplicationManager() {
        applicationManager.stop();
        applicationManager = null;
    }

    public int getGroupCount() {
        applicationManager.goTo().groupPage();
        return applicationManager.group().count();
    }

    public void createGroup(String name, String header, String footer) {
        applicationManager.goTo().groupPage();
        applicationManager.group().create(new GroupData().withName(name).withHeader(header).withFooter(footer));
    }

}
