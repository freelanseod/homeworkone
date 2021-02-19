package addressbook.tests;

import addressbook.model.AddressData;
import addressbook.model.Addresses;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewAddressTests extends TestBase {

    @Test
    public void testAddNewAddress() {
        app.goTo().homePage();
        Addresses before = app.contact().all();
        app.goTo().addressPage();

        File photo = new File("src/test/resources/od.jpg");
        AddressData contact = new AddressData().withFirstname("first name test").withLastname("last name test").withHomePhone("+79780230000").withMobilePhone("+79780991122").withWorkPhone("+79785227700")
                .withEmail("f@mail.ru").withEmail2("s@mail.ru").withEmail3("t@mail.ru").withPhoto(photo);

        app.contact().fillAddressFormWithAllFields(contact);
        app.contact().submitContactAdding();
        app.goTo().homePage();

        Addresses after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1); //compare size of lists

        AddressData index = contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(before.withAdded(index)));
    }

}
