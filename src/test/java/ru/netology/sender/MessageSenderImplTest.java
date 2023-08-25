package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    @Test
    void send() {
        String testIp1 = "172.17.17.17";
        String testIp2 = "173.17.17.17";

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(testIp1))
                .thenReturn(new Location("Barnaul", Country.RUSSIA, "Yurina street", 111));
        Mockito.when(geoService.byIp(testIp2))
                .thenReturn(new Location("Boston", Country.USA, "George street", 27));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("x-real-ip", testIp1);

        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("x-real-ip", testIp2);

        Assertions.assertEquals(messageSender.send(map1), "Добро пожаловать");
        Assertions.assertEquals(messageSender.send(map2), "Welcome");
    }
}