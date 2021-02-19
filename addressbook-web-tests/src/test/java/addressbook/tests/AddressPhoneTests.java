package addressbook.tests;

import addressbook.model.AddressData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressPhoneTests extends TestBase {

    @BeforeClass
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addressPage();
            app.contact().createContactAllFields();
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        AddressData contact = app.contact().allWithPhones().iterator().next();
        AddressData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(AddressData address) {
        return Arrays.asList(address.getHomePhone(), address.getMobilePhone(), address.getWorkPhone())
                .stream().filter(s -> !s.equals("")) //delete extra symbols
                .map(this::cleaned)
                .collect(Collectors.joining("\n")); //\n between phone numbers
    }

    private String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
