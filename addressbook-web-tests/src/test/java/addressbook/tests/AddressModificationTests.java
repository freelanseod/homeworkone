package addressbook.tests;

import addressbook.model.AddressData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class AddressModificationTests extends TestBase {

    @Test
    public void testAddressModification() {
        app.getNavigationHelper().goToAddNewAddressPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact();
        }
        List<AddressData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactEditing();
        AddressData contact = new AddressData("first name test", "last name test");
        app.getContactHelper().fillAddressForm(contact);
        app.getContactHelper().submitContactUpdate();
        app.getNavigationHelper().goToHomePage();

        List<AddressData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()); //compare size of lists

        before.remove(before.size() - 1);
        before.add(contact); //add group which was modified

        Comparator<? super AddressData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //anon function
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
