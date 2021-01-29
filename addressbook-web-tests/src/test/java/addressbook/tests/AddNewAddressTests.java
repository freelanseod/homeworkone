package addressbook.tests;

import addressbook.model.AddressData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class AddNewAddressTests extends TestBase {

    @Test
    public void testAddNewAddress() {
        app.goTo().homePage();
        List<AddressData> before = app.contact().list();

        app.goTo().addressPage();
        AddressData contact = new AddressData("first name test", "last name test");
        app.contact().fillAddressForm(contact);
        app.contact().submitContactAdding();
        app.goTo().homePage();

        List<AddressData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1); //compare size of lists

        before.add(contact); //add new contact

        Comparator<? super AddressData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //anon function
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
