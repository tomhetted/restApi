package ru.smirnovjavadev.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.smirnovjavadev.dto.UserDto;
import ru.smirnovjavadev.exception.MyException;
import ru.smirnovjavadev.service.UserService;

import java.util.List;

@RestController  // @ResponseBody + @Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("getAll") // http://localhost:4555/users/getAll  GET
    public List<UserDto> getAll(){
        return userService.getAll();
    }

    @PostMapping("save")
    public void save(@RequestBody @Valid UserDto userDto) throws MyException {
        userService.save(userDto);
    }

    @DeleteMapping(value = "{id}", produces = "application/json")  // http://localhost:4555/users/56
    public void deleteById(@PathVariable("id") Long id){
        userService.deleteById(id);
    }

    @PutMapping("{id}")
    public void updateById( @RequestBody @Valid UserDto userDto, @PathVariable("id") Long id){
        userService.updateById(id, userDto);
    }

    @GetMapping("/orderByAge/{age}")
    public List<UserDto> findAllOrderByAge(@PathVariable Integer age){
        return userService.findAllOrderByAge(age);
    }

}
