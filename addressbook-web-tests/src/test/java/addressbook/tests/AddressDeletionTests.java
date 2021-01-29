package addressbook.tests;

import addressbook.model.AddressData;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class AddressDeletionTests extends TestBase {

    @BeforeTest
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.goTo().addressPage();
            app.contact().createContact();
            app.goTo().homePage();
        }
    }

    @Test
    public void testDeleteAddress() {
        List<AddressData> before = app.contact().list();

        int index = before.size() - 1;
        app.contact().selectContact(index);
        app.contact().submitContactDeletion();
        app.contact().agreeContactDeletion();
        app.goTo().homePage();

        List<AddressData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1); //compare size of lists

        before.remove(index); //delete from List<GroupData> before
        Assert.assertEquals(before, after);
    }

    @Test
    public void testDeleteAllAddresses() {
        app.contact().selectAllContacts();
        app.contact().submitContactDeletion();
        app.contact().agreeContactDeletion();
        app.goTo().homePage();

        List<AddressData> result = app.contact().list();
        Assert.assertEquals(result.size(), 0); //compare size of lists
    }

}
