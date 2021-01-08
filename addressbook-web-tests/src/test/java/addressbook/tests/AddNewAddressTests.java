package addressbook.tests;

import addressbook.model.AddressData;
import org.testng.annotations.Test;

public class AddNewAddressTests extends TestBase {

    @Test
    public void testAddNewAddress() throws Exception {
        app.getNavigationHelper().goToAddNewAddressPage();
        app.getContactHelper().fillAddressForm(new AddressData("first name test", "middle name test", "last name test", "nickname test", "test company"));
        app.getContactHelper().submitContactAdding();
        app.getNavigationHelper().goToHomePage();
    }

}
