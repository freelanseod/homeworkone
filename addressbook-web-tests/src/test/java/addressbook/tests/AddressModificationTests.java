package addressbook.tests;

import addressbook.model.AddressData;
import org.testng.annotations.Test;

public class AddressModificationTests extends TestBase {

    @Test
    public void testAddressModification() {
        app.getNavigationHelper().goToAddNewAddressPage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact();
        }

        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactEditing();
        app.getContactHelper().fillAddressForm(new AddressData("first name edit", "middle name edit", "last name edit", "nickname edit", "company edit"));
        app.getContactHelper().submitContactUpdate();
        app.getNavigationHelper().goToHomePage();
    }

}
