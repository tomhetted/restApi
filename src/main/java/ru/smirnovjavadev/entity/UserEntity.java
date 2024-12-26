package ru.smirnovjavadev.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users", schema = "whiskey")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

}
