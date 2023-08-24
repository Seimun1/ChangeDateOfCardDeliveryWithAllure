package ru.netology.data;

import lombok.Value;

@Value
public class RegistrationData {
    private final String city;
    private final String name;
    private final String phone;
}
