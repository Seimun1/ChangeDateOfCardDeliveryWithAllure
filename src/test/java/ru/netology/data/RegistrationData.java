package ru.netology.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Data
@RequiredArgsConstructor
@Value
public class RegistrationData {
    private final String city;
    private final String name;
    private final String phone;
}
