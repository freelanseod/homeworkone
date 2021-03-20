package appmanager;

import org.openqa.selenium.By;

public class UiSession extends BaseHelper {
    public UiSession(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        type(By.name("username"), username);
        click(By.cssSelector("input[value='Login']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

}
