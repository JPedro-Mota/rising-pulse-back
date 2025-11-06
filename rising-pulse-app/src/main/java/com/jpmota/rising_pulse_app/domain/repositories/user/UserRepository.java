package com.jpmota.rising_pulse_app.domain.repositories.user;
import com.jpmota.rising_pulse_app.domain.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
//    List<UserEntity> findAllByActiveTrue(Sort sort);
    List<UserEntity> findAllByActiveTrue();
}
