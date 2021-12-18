package com.example.minumarevan.model;

import com.example.minumarevan.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "analysis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false, name = "inr")
    private Float inr;

    @Column(nullable = false, name = "inrDate")
    private Date inrDate;

    @Column(nullable = false, name = "mondayPills")
    private Float mondayPills;

    @Column(nullable = false, name = "tuesdayPills")
    private Float tuesdayPills;

    @Column(nullable = false, name = "wednesdayPills")
    private Float wednesdayPills;

    @Column(nullable = false, name = "thursdayPills")
    private Float thursdayPills;

    @Column(nullable = false, name = "fridayPills")
    private Float fridayPills;

    @Column(nullable = false, name = "saturdayPills")
    private Float saturdayPills;

    @Column(nullable = false, name = "sundayPills")
    private Float sundayPills;

    @OneToOne(mappedBy = "analysis")
    private User user;
}
