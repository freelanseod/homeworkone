package appmanager;

import org.openqa.selenium.By;

public class UserHelper extends BaseHelper {
    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void selectUser(String username, int userId) {
        type(By.id("search"), username);
        click(By.cssSelector("input[value='Apply Filter']"));
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + userId + "']"));
    }

    public void initPasswordReset(long now) {
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
