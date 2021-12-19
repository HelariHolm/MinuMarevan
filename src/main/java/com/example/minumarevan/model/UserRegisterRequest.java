package com.example.minumarevan.model;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegisterRequest implements Serializable {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String username;
    private Integer doctor;
    private Integer nextOfKin;
}
