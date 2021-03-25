package tests;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String location = new GeoIPService().getGeoIPServiceSoap12().getLocation().substring(16, 18);
        assertEquals(location, "RU");
    }

}
