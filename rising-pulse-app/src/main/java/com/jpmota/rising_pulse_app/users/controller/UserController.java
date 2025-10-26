package com.jpmota.rising_pulse_app.users.controller;


import com.jpmota.rising_pulse_app.users.DTOs.CreateUserRecordDTO;
import com.jpmota.rising_pulse_app.users.DTOs.UpdateUserRecordDTO;
import com.jpmota.rising_pulse_app.users.DTOs.UserResponseDTO;
import com.jpmota.rising_pulse_app.users.entities.UserEntity;
import com.jpmota.rising_pulse_app.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> findById(@PathVariable long id){
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>delete(@PathVariable("id") long id) {
        return userService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @PathVariable("id") long id, @Valid UpdateUserRecordDTO data) {
        return userService.update(id, data);
    }

}
