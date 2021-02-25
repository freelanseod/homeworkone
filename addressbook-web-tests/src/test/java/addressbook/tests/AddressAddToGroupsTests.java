package addressbook.tests;

import addressbook.model.AddressData;
import addressbook.model.Addresses;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressAddToGroupsTests extends TestBase {

    @BeforeClass
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test gp one").withHeader("test header one").withFooter("test footer one"));
        }
    }

    @Test
    public void testAddNewContactWithGroup() {
        Groups groups = app.db().groups();
        app.goTo().homePage();
        Addresses before = app.db().addresses();
        app.goTo().addressPage();

        File photo = new File("src/test/resources/od.jpg");
        AddressData contact = new AddressData()
                .withFirstname("first name test").withLastname("last name test").withHomePhone("+79780230000").withMobilePhone("+79780991122").withWorkPhone("+79785227700")
                .withEmail("f@mail.ru").withEmail2("s@mail.ru").withEmail3("t@mail.ru").withPhoto(photo).inGroup(groups.iterator().next());

        app.contact().fillAddressFormWithGroup(contact, true);
        app.contact().submitContactAdding();
        app.goTo().homePage();

        Addresses after = app.db().addresses();
        Assert.assertEquals(after.size(), before.size() + 1); //compare size of lists

        AddressData index = contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(before.withAdded(index)));

        verifyAddressesListInUI();
    }

}
