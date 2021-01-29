package addressbook.tests;

import addressbook.model.AddressData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class AddressModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().addressPage();
        if (app.contact().list().size() == 0) {
            app.contact().createContact();
        }
    }

    @Test
    public void testAddressModification() {
        List<AddressData> before = app.contact().list();

        app.contact().submitContactEditing(before.size() - 1);
        AddressData contact = new AddressData().withFirstname("first name test").withLastname("last name test");
        app.contact().fillAddressForm(contact);
        app.contact().submitContactUpdate();
        app.goTo().homePage();

        List<AddressData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size()); //compare size of lists

        before.remove(before.size() - 1);
        before.add(contact); //add group which was modified

        Comparator<? super AddressData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()); //anon function
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}
