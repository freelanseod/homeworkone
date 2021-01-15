package addressbook.appmanager;

import addressbook.model.AddressData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper extends BaseHelper {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactAdding() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillAddressForm(AddressData addressData) {
        type(By.name("firstname"), addressData.getFirstname());
        type(By.name("middlename"), addressData.getMiddlename());
        type(By.name("lastname"), addressData.getLastname());
        type(By.name("nickname"), addressData.getNickname());
        type(By.name("company"), addressData.getCompany());
    }

    public void selectContact() {
        click(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[1]"));
    }

    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void agreeContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void selectAllContacts() {
        click(By.id("MassCB"));
    }

    public void submitContactEditing() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactUpdate() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact() {
        fillAddressForm(new AddressData("first name test", "middle name test", "last name test", "nickname test", "test company"));
        submitContactAdding();
        click(By.linkText("home"));
    }

}
