package com.example.minumarevan.model;

import com.example.minumarevan.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contact_numbers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactNumbers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false, name = "doctor")
    private Integer doctor;

    @Column(nullable = false, name = "next_of_kin")
    private Integer nextOfKin;

    @OneToOne(mappedBy = "contactNumbers")
    private User user;
}
