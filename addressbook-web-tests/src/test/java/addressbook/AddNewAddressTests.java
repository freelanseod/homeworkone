package addressbook;

import org.testng.annotations.Test;

public class AddNewAddressTests extends TestBase{

    @Test
    public void testAddNewAddress() throws Exception {
        goToAddNewAddressPage();
        fillAddressForm(new AddressData("first name test", "middle name test", "last name test", "nickname test", "test company"));
        submitContactAdding();
        goToHomePage();
    }

}
