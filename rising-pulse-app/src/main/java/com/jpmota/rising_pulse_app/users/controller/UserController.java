package com.jpmota.rising_pulse_app.users.controller;


import com.jpmota.rising_pulse_app.users.DTOs.CreateUserRecordDTO;
import com.jpmota.rising_pulse_app.users.entities.UserEntity;
import com.jpmota.rising_pulse_app.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateUserRecordDTO data) {
        return userService.createUser(data);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>delete(@PathVariable("id") long id) {
        return userService.deleteById(id);
    }



}
