package addressbook.appmanager;

import addressbook.model.AddressData;
import addressbook.model.Addresses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public void submitContactEditing(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactEditingById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void submitContactUpdate() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact() {
        fillAddressForm(new AddressData().withFirstname("first name test").withMiddlename("middle name test").withLastname("last name test").withNickname("nickname test").withCompany("test company"));
        submitContactAdding();
        addressCache = null;
        returnToHomePage();
    }

    public void modify(AddressData contact) {
        submitContactEditingById(contact.getId());
        fillAddressForm(contact);
        submitContactUpdate();
        addressCache = null;
        returnToHomePage();
    }

    public void delete(int index) {
        selectContact(index);
        submitContactDeletion();
        agreeContactDeletion();
        returnToHomePage();
    }

    public void delete(AddressData addressData) {
        selectContactById(addressData.getId());
        submitContactDeletion();
        agreeContactDeletion();
        addressCache = null;
        returnToHomePage();
    }

    public void deleteAllAddresses() {
        selectAllContacts();
        submitContactDeletion();
        agreeContactDeletion();
        addressCache = null;
        returnToHomePage();
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    private Addresses addressCache = null;

    public List<AddressData> list() {
        List<AddressData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            contacts.add(new AddressData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

    public Set<AddressData> allSet() {
        Set<AddressData> contacts = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            contacts.add(new AddressData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

    public Addresses all() {
        if (addressCache != null) {
            return new Addresses(addressCache);
        }
        addressCache = new Addresses();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            addressCache.add(new AddressData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return new Addresses(addressCache);
    }

}
