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
        UserDto userDto = userService.getById(id);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 если пользователь не найден
        }
        return ResponseEntity.ok(userDto); // 200 с найденным пользователем
    }

    @PostMapping  // POST /users
    public ResponseEntity<Void> save(@RequestBody @Valid UserDto userDto) throws MyException {
        userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Возвращает код 201
    }

    @DeleteMapping("/{id}")  // DELETE /users/{id}
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Возвращает код 204
    }

    @PutMapping("/{id}")  // PUT /users/{id}
    public ResponseEntity<Void> updateById(@RequestBody @Valid UserDto userDto, @PathVariable("id") Long id) {
        userService.updateById(id, userDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Возвращает код 204
    }

    @GetMapping("/orderByAge/{age}")  // GET /users/orderByAge/{age}
    public List<UserDto> findAllOrderByAge(@PathVariable Integer age) {
        return userService.findAllOrderByAge(age);
    }

}
