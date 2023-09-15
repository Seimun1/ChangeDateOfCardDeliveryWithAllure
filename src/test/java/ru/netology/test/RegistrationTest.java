package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataGenerator.generateDate;

public class RegistrationTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    void shouldPassChangeDate () {
        open("http://localhost:9999/");

        var userData = DataGenerator.generateUser("ru");
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
                .shouldBe(visible, Duration.ofSeconds(10))
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + DataGenerator.generateDate(addDays1)));

        $("[data-test-id='date'] input").doubleClick().sendKeys(generateDate(addDays2));
        $x("//span[text()='Запланировать']").click();
        $x("//span[text()='Перепланировать']").click();
        $x("//div[@data-test-id='success-notification']")
                .shouldBe(visible, Duration.ofSeconds(10))
                .shouldHave(exactText("Успешно! Встреча успешно запланирована на " + generateDate(addDays2)));
    }

}
