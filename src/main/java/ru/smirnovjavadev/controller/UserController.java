package ru.smirnovjavadev.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.smirnovjavadev.dto.UserDto;
import ru.smirnovjavadev.exception.MyException;
import ru.smirnovjavadev.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping  // GET /users
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")  // GET /users/{id}
    public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
        if (!userService.existsById(id)) { // Проверяем, существует ли пользователь с таким id
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404, если не существует
        }
        UserDto userDto = userService.getById(id); // Получаем пользователя, если он существует
        return ResponseEntity.ok(userDto); // Возвращаем пользователя с кодом 200 OK
    }

    @PostMapping  // POST /users
    public ResponseEntity<Void> save(@RequestBody @Valid UserDto userDto) throws MyException {
        userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Возвращает код 201
    }

    @PutMapping("/{id}")  // PUT /users/{id}
    public ResponseEntity<Void> updateById(@RequestBody @Valid UserDto userDto, @PathVariable("id") Long id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userService.updateById(id, userDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}") // DELETE /users{id}
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/orderByAge/{age}")  // GET /users/orderByAge/{age}
    public List<UserDto> findAllOrderByAge(@PathVariable Integer age) {
        return userService.findAllOrderByAge(age);
    }

}
