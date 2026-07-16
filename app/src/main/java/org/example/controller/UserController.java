package org.example.controller;

import org.example.Dto.CreateUserRepositoryDto;
import org.example.Entity.User;
import org.example.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<User> findAll() {
        return userService.findAll();
    }
    @GetMapping("/username/{name}")
    public User findByName(@PathVariable String name) {
        return userService.findByUserName(name);
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserRepositoryDto create(@RequestBody CreateUserRepositoryDto dto) {
        return userService.createUser(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CreateUserRepositoryDto update(@PathVariable Long id, @RequestBody CreateUserRepositoryDto dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
