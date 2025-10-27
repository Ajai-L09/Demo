package com.expensesplit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class ExpenseParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_participant_id")
    private Integer expenseParticipantId;
    @ManyToOne
    @JoinColumn(name = "expense_id", referencedColumnName = "expense_id")
    private Expense expense;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    @Column(name = "share_amount")
    private BigDecimal shareAmount;
}
