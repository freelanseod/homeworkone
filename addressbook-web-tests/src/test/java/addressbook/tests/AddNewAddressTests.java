package addressbook.tests;

import addressbook.model.AddressData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class AddNewAddressTests extends TestBase {

    @Test
    public void testAddNewAddress() {
        app.getNavigationHelper().goToHomePage();
        List<AddressData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().goToAddNewAddressPage();
        AddressData contact = new AddressData("first name test", "last name test");
        app.getContactHelper().fillAddressForm(contact);
        app.getContactHelper().submitContactAdding();
        app.getNavigationHelper().goToHomePage();

        List<AddressData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1); //compare size of lists

        before.add(contact); //add new contact

        Comparator<? super AddressData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //anon function
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
