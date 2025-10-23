package com.jpmota.rising_pulse_app.users.entities;

import com.jpmota.rising_pulse_app.users.enums.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tb_user")
@Where(clause = "active = true")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Email
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRoleEnum role;
    @Column(name = "active")
    private boolean active = true;

    public UserEntity(String name, String password, String email, UserRoleEnum role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public boolean isActive(){
        return this.active;
    }

    public void setaActive(boolean active){
        this.active = active;
    }

}
