package addressbook.tests;

import addressbook.model.AddressData;
import addressbook.model.Addresses;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewAddressTests extends TestBase {

    @Test
    public void testAddNewAddress() {
        app.goTo().homePage();
        Addresses before = app.contact().all();

        AddressData contact = new AddressData().withFirstname("first name test").withLastname("last name test");
        app.goTo().addressPage();
        app.contact().fillAddressForm(contact);
        app.contact().submitContactAdding();
        app.goTo().homePage();

        Addresses after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1); //compare size of lists

        AddressData index = contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(before.withAdded(index)));
    }

}
