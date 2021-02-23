package addressbook.tests;

import addressbook.model.AddressData;
import addressbook.model.Addresses;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewAddressTests extends TestBase {

    //iterator of arrays of objects
    @DataProvider
    public Iterator<Object[]> validAddressesFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/addresses.xml"))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(AddressData.class);
            List<AddressData> groups = (List<AddressData>) xStream.fromXML(xml); //type cast

            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validAddressesFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/addresses.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            //List<AddressData>.class
            List<AddressData> groups = gson.fromJson(json, new TypeToken<List<AddressData>>(){}.getType());

            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test (dataProvider = "validAddressesFromJson")
    public void testAddNewAddress(AddressData contact) {
        app.goTo().homePage();
        Addresses before = app.db().addresses();
        app.goTo().addressPage();

//        File photo = new File("src/test/resources/od.jpg");
//        AddressData contact = new AddressData().withFirstname("first name test").withLastname("last name test").withHomePhone("+79780230000").withMobilePhone("+79780991122").withWorkPhone("+79785227700").withEmail("f@mail.ru").withEmail2("s@mail.ru").withEmail3("t@mail.ru").withPhoto(photo);

        app.contact().fillAddressFormWithAllFields(contact);
        app.contact().submitContactAdding();
        app.goTo().homePage();

        Addresses after = app.db().addresses();
        Assert.assertEquals(after.size(), before.size() + 1); //compare size of lists

        AddressData index = contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt());
        assertThat(after, equalTo(before.withAdded(index)));
    }

}
