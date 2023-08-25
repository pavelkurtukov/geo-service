package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {
    @Test
    void locale() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String expectedRussian = "Добро пожаловать";
        String expectedOthers = "Welcome";

        Assertions.assertEquals(localizationService.locale(Country.RUSSIA), expectedRussian);
        Assertions.assertEquals(localizationService.locale(Country.USA), expectedOthers);
        Assertions.assertEquals(localizationService.locale(Country.BRAZIL), expectedOthers);
        Assertions.assertEquals(localizationService.locale(Country.GERMANY), expectedOthers);
    }
}