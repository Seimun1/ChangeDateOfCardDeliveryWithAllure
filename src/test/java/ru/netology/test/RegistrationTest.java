package ru.netology.test;

import org.junit.jupiter.api.Test;

import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationData;

import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataGenerator.generateDate;

public class RegistrationTest {

    @Test
    void shouldPassChangeDate () {
        open("http://localhost:9999/");

        RegistrationData userData = DataGenerator.generateUser("ru");
        int addDays1 = 5;
        int addDays2 = 7;
        $("[data-test-id='city'] input").sendKeys(userData.getCity());
        $("[data-test-id='date'] input").sendKeys(generateDate(addDays1));
        $("[data-test-id='date'] input").doubleClick().sendKeys(generateDate(addDays1));
        $("[data-test-id='name'] input").sendKeys(userData.getName());
        $("[data-test-id='phone'] input").sendKeys(userData.getPhone());
        $("[data-test-id='agreement']").click();
        $x("//span[text()='Запланировать']").click();
        $x("//div[@data-test-id='success-notification']")
                .shouldBe(visible)
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + DataGenerator.generateDate(addDays1)));

        $("[data-test-id='date'] input").doubleClick().sendKeys(generateDate(addDays2));
        $x("//span[text()='Запланировать']").click();
        $x("//span[text()='Перепланировать']").click();
        $x("//div[@data-test-id='success-notification']")
                .shouldBe(visible)
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDate(addDays2)));
    }

}
