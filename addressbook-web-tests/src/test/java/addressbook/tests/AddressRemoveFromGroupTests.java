package addressbook.tests;

import addressbook.model.AddressData;
import addressbook.model.Addresses;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddressRemoveFromGroupTests extends TestBase {
    @BeforeClass
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("gp for test"));
        }

        Groups groups = new Groups();
        if (app.db().addresses().size() == 0) {
            app.goTo().addressPage();
            AddressData contact = new AddressData()
                    .withFirstname("first name test").withLastname("last name test").withHomePhone("+79780230000").withMobilePhone("+79780991122").withWorkPhone("+79785227700")
                    .withEmail("f@mail.ru").withEmail2("s@mail.ru").withEmail3("t@mail.ru").inGroup(groups.iterator().next());

            app.contact().fillAddressFormWithGroup(contact, true);
            app.contact().submitContactAdding();
        }
    }

    @Test
    public void testDeleteAddressFromGroup() {
        AddressData addressData = selectContactAddToGroup();
        GroupData groupData = app.db().groups().iterator().next();
        app.contact().selectGroupFromMainList(groupData.getId());

        Addresses before = app.contact().all();

        app.contact().selectContactById(addressData.getId());
        app.contact().deleteContact();

        app.goTo().homePage();
        app.contact().selectGroupFromMainList(groupData.getId());
        Addresses after = app.contact().all();

        Assert.assertEquals(after.size(), before.size() - 1);
        // need fix
        //assertThat(after, equalTo(before.without(addressData)));
    }

    private AddressData selectContactAddToGroup() {
        AddressData addContact = app.db().addresses().iterator().next();
        app.contact().addToGroup(addContact, app.db().groups().iterator().next());
        return addContact;
    }

}
