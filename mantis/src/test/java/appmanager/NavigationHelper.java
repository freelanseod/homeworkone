package appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends BaseHelper {
    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUsers() {
        if (isElementPresent(By.tagName("div.main-content"))
                && wd.findElement(By.tagName("span")).getText().equals("Manage")) {
            return;
        }
        click(By.cssSelector("a[href='/mantis/manage_overview_page.php']"));
        click(By.cssSelector("a[href='/mantis/manage_user_page.php'"));
    }

}
