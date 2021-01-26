package addressbook.tests;

import addressbook.model.AddressData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddressDeletionTests extends TestBase {

    @Test
    public void testDeleteAddress() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddNewAddressPage();
            app.getContactHelper().createContact();
        }
        List<AddressData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().agreeContactDeletion();
        app.getNavigationHelper().goToHomePage();

        List<AddressData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1); //compare size of lists

        before.remove(before.size() - 1); //delete from List<GroupData> before
        Assert.assertEquals(before, after);
    }

    @Test
    public void testDeleteAllAddresses() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddNewAddressPage();
            app.getContactHelper().createContact();
        }

        app.getContactHelper().selectAllContacts();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().agreeContactDeletion();
        app.getNavigationHelper().goToHomePage();

        List<AddressData> result = app.getContactHelper().getContactList();
        Assert.assertEquals(result.size(), 0); //compare size of lists
    }

}
