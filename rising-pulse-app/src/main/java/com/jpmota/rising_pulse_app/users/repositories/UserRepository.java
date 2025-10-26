package com.jpmota.rising_pulse_app.users.repositories;
import com.jpmota.rising_pulse_app.users.entities.UserEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
//    List<UserEntity> findAllByActiveTrue(Sort sort);
    List<UserEntity> findAllByActiveTrue();
}
