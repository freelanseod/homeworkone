package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends BaseHelper {
    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
    }

    public void goToAddNewAddressPage() {
        click(By.linkText("add new"));
    }

    public void goToHomePage() {
        click(By.linkText("home page"));
    }

}
