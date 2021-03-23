package appmanager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
    private final Properties properties;
    private RestHelper restHelper;

    public ApplicationManager() {
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local"); //if target type is not specified use local
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RestHelper helper() {
        if (restHelper == null) {
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }

}
