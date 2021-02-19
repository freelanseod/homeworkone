package addressbook.tests;

import addressbook.model.AddressData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressEmailTests extends TestBase {

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
    public void testAddressEmail() {
        app.goTo().homePage();
        AddressData contact = app.contact().allContacts().iterator().next();
        AddressData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(AddressData address) {
        return Arrays.asList(address.getEmail(), address.getEmail2(), address.getEmail3())
                .stream().filter(s -> !s.equals("")) //delete extra symbols
                .map(this::cleaned)
                .collect(Collectors.joining("\n")); //\n between emails
    }

    private String cleaned(String email) {
        return email.replaceAll("mailto:", "");
    }

}
