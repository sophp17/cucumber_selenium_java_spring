package com.example.utils;

import com.example.pages.BasePage;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
    public class CookieService extends BasePage {
    @Value("${application.url}")
    private String baseURL;
    private String DOMAIN = baseURL;


        public void addCookies(WebDriver driver) {
            driver.manage().deleteAllCookies();
            List<Cookie> cookies = Arrays.asList(
                    createCookie("__gpi", "UID=0000100e4de5294d:T=1741446156:RT=1741446156:S=ALNI_MaHMAwMHHJAh-yOFYU8U16DMqAJ8w"),
                    createCookie("__gads", "ID=ec8d2b1ed8045c87:T=1741446156:RT=1741446156:S=ALNI_MZTSKEDWTn4esq_k9zlnYo2l-RNpA"),
                    createCookie("_fbp", "fb.1.1741446296059.169654996331977544"),
                    createCookie("_ga_C1R58GKDN5", "GS1.1.1741446088.1.1.1741446157.0.0.0"),
                    createCookie("cid", "2011034441.1741446089"),
                    createCookie("FCCDCF", "%5Bnull%2Cnull%2Cnull%2C%5B%22CQN81UAQN81UAEsACBFRBgFoAP_gAEPgAA5QINJD7C7FbSFCwH5zaLsAMAhHRsAAQoQAAASBAmABQAKQIAQCgkAYFASgBAACAAAAICRBIQIECAAAAUAAQAAAAAAEAAAAAAAIIAAAgAEAAAAIAAACAIAAEAAIAAAAEAAAmAgAAIIACAAAgAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAQNVSD2F2K2kKFkPCmwXYAYBCujYAAhQgAAAkCBMACgAUgQAgFJIAgCIFAAAAAAAAAQEiCQAAQABAAAIACgAAAAAAIAAAAAAAQQAABAAIAAAAAAAAEAQAAIAAQAAAAIAABEhAAAQQAEAAAAAAAQAAA%22%2C%222~70.89.93.108.122.149.184.196.236.259.311.313.323.358.415.442.486.494.495.540.574.609.864.981.1029.1048.1051.1095.1097.1126.1205.1276.1301.1365.1415.1449.1514.1570.1577.1598.1651.1716.1735.1753.1765.1870.1878.1889.1958.1960.2072.2253.2299.2373.2415.2506.2526.2531.2568.2571.2575.2624.2677.2778~dv.%22%2C%226BED8587-69CD-48A5-B51B-08AE366D1914%22%5D%5D"),
                    createCookie("FCNEC", "%5B%5B%22AKsRol9RG3kZwHZ16-_pGNd8PYdIZ1vwwXKVAM0okZyCTC4roKb9xP0AI_Y0OzIBE2mN8rQ-xA4uB-FGzptC2qU1lEOpdqLPO0pVBSyc51xI4vQjulAarvKA91L7eQcpTW8YQpsKcx8Ed4yLVPOyEGItNQO28b0qFw%3D%3D%22%5D%5D"),
                    createCookie("_ga", "GA1.1.1832217853.1741445865"),
                    createCookie("cid", "1832217853.1741445865"),
                    createCookie("_eoi ", "53bcd4832596eb4a:T=1741001142:RT=1741003196:S=AA-AfjZA5m07xYDTJ4m2cvvO2XOl"),
                    createCookie("__gads ", "3ee55f42e21ca078:T=1741001485:RT=1741003196:S=ALNI_MbfFYc3UEoochBq87FTtVfxPGG_aw"),
                    createCookie("__gpi ", "0000100335628ac1:T=1741001485:RT=1741003196:S=ALNI_MYZ3VISchSjAMo6YHvAW1x-lk01oA"),
                    createCookie("_fbp ", "fb.1.1741001638271.187605782187644395"),
                    createCookie("_ga_C1R58GKDN5 ", "GA1.1.1364587154.1741001141"),
                    createCookie("_ga ", "GS1.1.1741001140.1.1.1741003196.0.0.0"),
                    createCookie("cid ", "1364587154.1741001141"),
                    createCookie("FCCDCF ", "%5Bnull%2Cnull%2Cnull%2C%5B%22CQNsWoAQNsWoAEsACBFRBfFoAP_gAEPgAA5QINJD7C7FbSFCwH5zaLsAMAhHRsAAQoQAAASBAmABQAKQIAQCgkAYFASgBAACAAAAICRBIQIECAAAAUAAQAAAAAAEAAAAAAAIIAAAgAEAAAAIAAACAIAAEAAIAAAAEAAAmAgAAIIACAAAgAAAAAAAAAAAAAAAAACAAAAAAAAAAAAAAAAAAQNVSD2F2K2kKFkPCmwXYAYBCujYAAhQgAAAkCBMACgAUgQAgFJIAgCIFAAAAAAAAAQEiCQAAQABAAAIACgAAAAAAIAAAAAAAQQAABAAIAAAAAAAAEAQAAIAAQAAAAIAABEhAAAQQAEAAAAAAAQAAA%22%2C%222~70.89.93.108.122.149.184.196.236.259.311.313.323.358.415.442.486.494.495.540.574.609.864.981.1029.1048.1051.1095.1097.1126.1205.1276.1301.1365.1415.1449.1514.1570.1577.1598.1651.1716.1735.1753.1765.1870.1878.1889.1958.1960.2072.2253.2299.2373.2415.2506.2526.2531.2568.2571.2575.2624.2677.2778~dv.%22%2C%229EA6995D-57A8-4E06-9918-61723813B200%22%5D%5D"),
                    createCookie("FCNEC ", "%5B%5B%22AKsRol-KP6gUHeCxe9rqCJVlheqo3BSsWyn-_z0NbY1UDiRgkfSrz2RMmbmKqanm5TNOkLKAPh-x_9vi91z8i8v9UFtTv5Zr3Wsq-7ZCYGSbocK7bG24h88b1OeTD21a4T1NuSET0tT1x3UpWdKlqCwx0_a1_-hntQ%3D%3D%22%5D%5D"),
                    createCookie("promoCover_DESKTOP_HOME_COVER_cooldown ", "cooldown"),
                    createCookie("promoCover_DESKTOP_HOME_COVER ", "2"),
                    createCookie("promoFlash_DESKTOP_HOME_FLASH ", "2"),
                    createCookie("promoInterstitial_DESKTOP_HOME_INTERSTITIAL_home ", "2"),
                    createCookie("promoInterstitial_DESKTOP_HOME_INTERSTITIAL_home_cooldown ", "cooldown")
                    );

            cookies.forEach(driver.manage()::addCookie);
        }

        private Cookie createCookie(String name, String value) {
            return new Cookie.Builder(name, value)
                    .domain(DOMAIN)
                    .path("/")
                    .isSecure(true)
                    .build();
        }


    @Override
    public boolean isAt() {
        return false;
    }
}
