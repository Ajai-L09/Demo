package com.expensesplit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Integer expenseId;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id" )
    private ExpenseGroup group;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "paid_by", referencedColumnName = "user_id")
    private User paidBy;

    @Column(name="expense_date")
    private Timestamp expenseDate;
}
