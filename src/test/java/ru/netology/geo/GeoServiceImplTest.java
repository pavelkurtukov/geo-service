package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
class GeoServiceImplTest {
    GeoServiceImpl geoService = new GeoServiceImpl();

    @ParameterizedTest
    @MethodSource("ipParams")
    @DisplayName("Тест определения локации по IP")
    void byIpWithParams(String ip, Country expectedCountry) {
        Country resultCountry = geoService.byIp(ip).getCountry();
        Assertions.assertEquals(resultCountry, expectedCountry);
    }

    public static Stream<Arguments> ipParams() {
        return Stream.of(
                Arguments.of("172.0.32.11", Country.RUSSIA),
                Arguments.of("172.55.253.27", Country.RUSSIA),
                Arguments.of("96.44.183.149", Country.USA),
                Arguments.of("96.51.3.3", Country.USA),
                Arguments.of("127.0.0.1", null)
        );
    }

    @Test
    void byCoordinates() {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(111d, 222d));
    }
}