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

    public void fillAddressFormWithAllFields(AddressData addressData) {
        type(By.name("firstname"), addressData.getFirstname());
        type(By.name("lastname"), addressData.getLastname());
        type(By.name("home"), addressData.getHomePhone());
        type(By.name("mobile"), addressData.getMobilePhone());
        type(By.name("work"), addressData.getWorkPhone());
        type(By.name("email"), addressData.getEmail());
        type(By.name("email2"), addressData.getEmail2());
        type(By.name("email3"), addressData.getEmail3());
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
        click(By.linkText("home"));
    }

    public void createContactWithPhone() {
        fillAddressFormWithAllFields(new AddressData().withFirstname("first name test").withLastname("last name test").withHomePhone("+79780000000").withMobilePhone("+79880000000").withWorkPhone("+79340000000"));
        submitContactAdding();
        click(By.linkText("home"));
    }

    public void createContactAllFields() {
        fillAddressFormWithAllFields(new AddressData().withFirstname("first name test").withLastname("last name test").withPostAddress("99345 post test street").withHomePhone("+79780000000")
                .withMobilePhone("+79880000000").withWorkPhone("+79340000000").withEmail("one@mail.ru").withEmail2("two@mail.ru").withEmail3("three@mail.ru"));
        submitContactAdding();
        click(By.linkText("home"));
    }

    public void modify(AddressData contact) {
        submitContactEditingById(contact.getId());
        fillAddressForm(contact);
        submitContactUpdate();
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
        returnToHomePage();
    }

    public void deleteAllAddresses() {
        selectAllContacts();
        submitContactDeletion();
        agreeContactDeletion();
        returnToHomePage();
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

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

    public Set<AddressData> allWithPhones() {
        Set<AddressData> contacts = new HashSet<>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String allPhones = cells.get(5).getText();
            contacts.add(new AddressData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones));
        }
        return contacts;
    }

    public Set<AddressData> allContacts() {
        Set<AddressData> contacts = new HashSet<>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            contacts.add(new AddressData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withPostAddress(address).withAllEmails(allEmails));
        }
        return contacts;
    }

    public Addresses all() {
        Addresses contacts = new Addresses();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            contacts.add(new AddressData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

    public AddressData infoFromEditForm(AddressData contact) {
        initAddressModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String postAddress = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();

        return new AddressData().withId(contact.getId()).withFirstname(firstName).withLastname(lastName).withHomePhone(home).withMobilePhone(mobile)
                .withWorkPhone(work).withPostAddress(postAddress).withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    private void initAddressModificationById(int id) { //choose contact by id
        //метод последовательных приближений = метод итераций
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id))); //find checkbox
        WebElement row = checkbox.findElement(By.xpath("./../..")); //rise up to class entry
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click(); //find and click on edit button

        //more versions
        //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
        //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
        //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

}
