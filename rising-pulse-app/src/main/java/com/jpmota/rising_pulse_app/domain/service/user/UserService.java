package com.jpmota.rising_pulse_app.domain.service.user;


import com.jpmota.rising_pulse_app.DTOs.user.CreateUserRecordDTO;
import com.jpmota.rising_pulse_app.DTOs.user.UpdateUserRecordDTO;
import com.jpmota.rising_pulse_app.DTOs.user.UserResponseDTO;
import com.jpmota.rising_pulse_app.domain.entities.user.UserEntity;
import com.jpmota.rising_pulse_app.domain.repositories.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserEntity> users = userRepository.findAllByActiveTrue();
        List<UserResponseDTO> userDTO = users.stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()

                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTO);
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

    public ResponseEntity<Object> update (long id, @Valid UpdateUserRecordDTO updateUserRecordDTO){
        Optional<UserEntity> response = userRepository.findById(id);

        if(response.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

        UserEntity user = response.get();
        user.setName(updateUserRecordDTO.name());
        user.setEmail(updateUserRecordDTO.email());
        user.setRole(updateUserRecordDTO.role());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");


    }

}
