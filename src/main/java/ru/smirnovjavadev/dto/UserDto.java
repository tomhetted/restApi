package ru.smirnovjavadev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    @NotBlank(message = "Name can not be null")
    private String name;

    @NotNull
    private Integer age;

}
