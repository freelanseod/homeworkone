package addressbook.tests;

import addressbook.model.AddressData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressPostTests extends TestBase {

    @BeforeClass
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.db().addresses().size() == 0) {
            app.goTo().addressPage();
            app.contact().createContactAllFields();
            app.goTo().homePage();
        }
    }

    @Test
    public void testPostAddress() {
        app.goTo().homePage();
        AddressData contact = app.contact().allContacts().iterator().next();
        AddressData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getPostAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));

        verifyAddressesListInUI();
    }

    private String mergeAddress(AddressData address) {
        return Arrays.asList(address.getPostAddress())
                .stream().filter(s -> !s.equals("")) //delete extra symbols
                .collect(Collectors.joining("\n")); //\n between phone words
    }

}
