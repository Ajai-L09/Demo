package com.expensesplit.repository;

import com.expensesplit.model.Expense;
import com.expensesplit.model.ExpenseParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseParticipantRepository extends JpaRepository<ExpenseParticipant, Integer> {
    List<ExpenseParticipant> findByExpense_ExpenseId(Integer expenseId);
}
