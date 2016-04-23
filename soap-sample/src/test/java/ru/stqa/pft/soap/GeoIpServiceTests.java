package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Ксюшенька on 23.04.2016.
 */
public class GeoIpServiceTests {
    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("192.168.1.4");
        Assert.assertEquals(geoIP.getCountryCode(),"ZZZ");
    }
    @Test
    public void testIvalidIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("192.168.1.XXX");
        Assert.assertEquals(geoIP.getCountryCode(),"ZZZ");
    }
}
