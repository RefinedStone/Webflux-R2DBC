package com.example.reactiveapp.person;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PersonResponseDto {
    private String name;
    private Long age;
}
