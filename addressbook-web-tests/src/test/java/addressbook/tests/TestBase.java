package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import addressbook.model.AddressData;
import addressbook.model.Addresses;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@Listeners(MyTestListener.class)
public class TestBase {
    Logger logger = LoggerFactory.getLogger("TestBase");

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME)); //if browser type is not specified use chrome

    @BeforeSuite
    public void setUp(ITestContext context) throws Exception {
        app.init();
        context.setAttribute("app", app);
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] p) {
        //Method name of test
        logger.info("start test " + method.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod (alwaysRun = true)
    public void logTestStop(Method method) {
        logger.info("stop test " + method.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyAddressesListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Addresses dbAddresses = app.db().addresses();
            Addresses uiAddresses = app.contact().all();
            assertThat(uiAddresses, equalTo(dbAddresses.stream()
                    .map((a) -> new AddressData().withId(a.getId()).withFirstname(a.getFirstname()).withLastname(a.getLastname()))
                    .collect(Collectors.toSet())));
        }
    }

}
