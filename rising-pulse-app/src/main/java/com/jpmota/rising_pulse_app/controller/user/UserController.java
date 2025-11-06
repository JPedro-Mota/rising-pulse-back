package com.jpmota.rising_pulse_app.controller.user;


import com.jpmota.rising_pulse_app.DTOs.user.CreateUserRecordDTO;
import com.jpmota.rising_pulse_app.DTOs.user.UpdateUserRecordDTO;
import com.jpmota.rising_pulse_app.DTOs.user.UserResponseDTO;
import com.jpmota.rising_pulse_app.domain.entities.user.UserEntity;
import com.jpmota.rising_pulse_app.domain.service.user.UserService;
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
