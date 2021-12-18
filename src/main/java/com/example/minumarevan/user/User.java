package com.example.minumarevan.user;

import com.example.minumarevan.model.Analysis;
import com.example.minumarevan.model.ContactNumbers;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 45, nullable = false, name = "first_name")
    private String firstname;
    @Column(length = 45, nullable = false, name = "last_name")
    private String lastname;
    @Column(nullable = false, unique = true, name = "email_address")
    private String email;
    @Column(length = 30, nullable = false)
    private String password;
    @Column(length = 30, nullable = false)
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_numbers_id", referencedColumnName = "id")
    private ContactNumbers contactNumbers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "analysis_id", referencedColumnName = "id")
    private Analysis analysis;

}
