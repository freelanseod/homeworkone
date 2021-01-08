package addressbook.tests;

import org.testng.annotations.Test;

public class AddressDeletionTests extends TestBase {

    @Test
    public void testDeleteAddress() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().agreeContactDeletion();
        app.getNavigationHelper().goToHomePage();
    }

    @Test
    public void testDeleteAllAddresses() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectAllContacts();
        app.getContactHelper().submitContactDeletion();
        app.getContactHelper().agreeContactDeletion();
        app.getNavigationHelper().goToHomePage();
    }

}
