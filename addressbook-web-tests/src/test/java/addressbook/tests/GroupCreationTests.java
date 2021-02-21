package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    //iterator of arrays of objects
    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.xml"))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml); //type cast
            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/groups.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            //List<GroupData>.class
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());

            return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testCreationGroup(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();

        app.group().initGroupCreation();
        app.group().fillGroupForm(group);
        app.group().submitGroupCreation();
        app.group().returnToGroupPage();

        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        GroupData index = group.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()); //anon function get id from stream of groups set and find max id
        assertThat(after, equalTo(before.withAdded(index))); //hamcrest works with fluent interfaces
    }

}
