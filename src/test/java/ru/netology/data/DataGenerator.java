package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private DataGenerator() {}

    public static String generateDate(int addDays) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] cities = new String[] {"Архангельск", "Барнаул", "Брянск", "Воронеж", "Казань", "Красноярск",
                "Липецк", "Москва", "Магадан", "Новосибирск", "Оренбург", "Пермь", "Псков", "Самара", "Саратов",
                "Смоленск", "Тверь", "Томск", "Уфа", "Хабаровск", "Ярославль"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static RegistrationData generateUser(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return new RegistrationData(
                generateCity(),
                faker.name().firstName() + " " + faker.name().lastName(),
                faker.phoneNumber().phoneNumber()
        );
    }
}
