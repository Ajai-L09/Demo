package com.expensesplit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(name  = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private String name;
    @Column(unique = true)
    private String email;

    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "join_date", updatable = false)
    private Timestamp joinDate = new Timestamp(System.currentTimeMillis());
}
