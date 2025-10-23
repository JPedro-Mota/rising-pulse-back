package com.jpmota.rising_pulse_app.users.service;


import com.jpmota.rising_pulse_app.users.DTOs.CreateUserRecordDTO;
import com.jpmota.rising_pulse_app.users.entities.UserEntity;
import com.jpmota.rising_pulse_app.users.repositories.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Void> createUser(@Valid CreateUserRecordDTO createUserRecordDTO) {

        String encryptedPassword = passwordEncoder.encode(createUserRecordDTO.password());
        UserEntity newUser = new UserEntity(createUserRecordDTO.name(), encryptedPassword, createUserRecordDTO.email(),  createUserRecordDTO.role());
        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    public Optional<UserEntity> findById (long id) {
        return userRepository.findById(id);
    }

    public ResponseEntity<Object> deleteById (long id){
        Optional<UserEntity> response = userRepository.findById(id);

        if(response.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

         UserEntity user = response.get();
         user.setActive(false);
         userRepository.save(user);
         return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");


    }
    

}
