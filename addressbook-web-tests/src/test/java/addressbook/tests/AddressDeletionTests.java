package addressbook.tests;

import addressbook.model.AddressData;
import addressbook.model.Addresses;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressDeletionTests extends TestBase {

    @BeforeTest
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addressPage();
            app.contact().createContact();
            app.goTo().homePage();
        }
    }

    @Test
    public void testDeleteAddress() {
        Addresses before = app.contact().all();
        AddressData deleteContact = before.iterator().next();

        app.contact().delete(deleteContact);

        Addresses after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1); //compare size of lists

        before.without(deleteContact);
        assertThat(after, equalTo(before.without(deleteContact)));
    }

    @Test
    public void testDeleteAllAddresses() {
        app.contact().deleteAllAddresses();

        Addresses result = app.contact().all();
        Assert.assertEquals(result.size(), 0); //compare size of lists
    }

}
