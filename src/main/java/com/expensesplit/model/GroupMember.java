package com.expensesplit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_member_id")
    private Integer groupMemberId;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private ExpenseGroup group;
    @ManyToOne
    @JoinColumn(name= "user_id", referencedColumnName = "user_id")
    private User user;
    @Column(name = "joined_at", updatable = false)
    private Timestamp joinedAt = new Timestamp(System.currentTimeMillis());

}
