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
@Table(name = "groups")

public class ExpenseGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer groupId;
    private String name;

    @ManyToOne
    @JoinColumn(name="created_by", referencedColumnName = "user_id")
    private User createdBy;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
}
