package addressbook.generators;

import addressbook.model.AddressData;
import addressbook.model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Address count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage(); //info about available options
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<AddressData> addresses = generateContacts(count);
        if (format.equals("xml")) {
            saveAsXml(addresses, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(addresses, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<AddressData> addresses, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(addresses);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<AddressData> addresses, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(addresses);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<AddressData> generateContacts(int count) {
        List<AddressData> addresses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            addresses.add(new AddressData().withFirstname(String.format("generator name %s", i)).withLastname(String.format("generator last name %s", i)).withCompany(String.format("generator company %s", i)).withHomePhone(String.format("+7978099123%s", i)).withMobilePhone(String.format("+7978099124%s", i))
                    .withWorkPhone(String.format("+7978099125%s", i)).withPostAddress(String.format("generator post address %s", i)).withEmail(String.format("email%s@mail.ru", i)).withEmail2(String.format("email2%s@mail.ru", i)).withEmail3(String.format("email3%s@mail.ru", i)));
        }
        return addresses;
    }

}
