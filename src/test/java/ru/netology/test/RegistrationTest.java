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

        $("[data-test-id='city'] input").sendKeys(userData.getCity());
        $("[data-test-id='date'] input").sendKeys(generateDate(5));
        $("[data-test-id='date'] input").doubleClick().sendKeys(generateDate(5));
        $("[data-test-id='name'] input").sendKeys(userData.getName());
        $("[data-test-id='phone'] input").sendKeys(userData.getPhone());
        $("[data-test-id='agreement']").click();
        $x("//span[text()='Запланировать']").click();
        $x("//div[@data-test-id='success-notification']")
                .shouldBe(visible, Duration.ofSeconds(4))
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + DataGenerator.generateDate(5)));

        $("[data-test-id='date'] input").doubleClick().sendKeys(generateDate(7));
        $x("//span[text()='Запланировать']").click();
        $x("//span[text()='Перепланировать']").click();
        $x("//div[@data-test-id='success-notification']")
                .shouldBe(visible, Duration.ofSeconds(4))
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDate(7)));
    }

}
