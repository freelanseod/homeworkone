package addressbook.tests;

import addressbook.model.AddressData;
import addressbook.model.Addresses;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().addresses().size() == 0) {
            app.goTo().addressPage();
            app.contact().createContactAllFields();
            app.goTo().homePage();
        }
    }

    @Test
    public void testDeleteAddress() {
        Addresses before = app.db().addresses();
        AddressData deleteContact = before.iterator().next();

        app.contact().delete(deleteContact);

        Addresses after = app.db().addresses();
        Assert.assertEquals(after.size(), before.size() - 1); //compare size of lists

        before.without(deleteContact);
        assertThat(after, equalTo(before.without(deleteContact)));

        verifyAddressesListInUI();
    }

    @Test
    public void testDeleteAllAddresses() {
        app.contact().deleteAllAddresses();

        Addresses result = app.db().addresses();
        Assert.assertEquals(result.size(), 0); //compare size of lists
    }

}
