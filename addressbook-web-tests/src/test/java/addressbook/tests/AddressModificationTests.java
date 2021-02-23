package addressbook.tests;

import addressbook.model.AddressData;
import addressbook.model.Addresses;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressModificationTests extends TestBase {

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
    public void testAddressModification() {
        Addresses before = app.db().addresses();
        AddressData modifiedContact = before.iterator().next();

        AddressData contact = new AddressData()
                .withId(modifiedContact.getId()).withFirstname("first name test").withLastname("last name test").withHomePhone("89001112233").withWorkPhone("8009224400")
                .withMobilePhone("89718882266").withEmail("mod@mail.ru").withEmail2("mod2@mail.ru").withEmail3("mod3@mail.ru");

        app.contact().modify(contact);

        Addresses after = app.db().addresses();
        Assert.assertEquals(after.size(), before.size()); //compare size of lists

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
