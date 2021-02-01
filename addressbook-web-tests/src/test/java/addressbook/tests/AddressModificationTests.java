package addressbook.tests;

import addressbook.model.AddressData;
import addressbook.model.Addresses;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().addressPage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact();
        }
    }

    @Test
    public void testAddressModification() {
        Addresses before = app.contact().all();
        AddressData modifiedContact = before.iterator().next();

        AddressData contact = new AddressData()
                .withId(modifiedContact.getId()).withFirstname("first name test").withLastname("last name test");

        app.contact().modify(contact);

        Addresses after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()); //compare size of lists

        before.without(modifiedContact);
        before.withAdded(contact);

        Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
