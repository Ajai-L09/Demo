package com.expensesplit.repository;

import com.expensesplit.model.ExpenseParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ExpenseParticipantRepository extends JpaRepository<ExpenseParticipant, Integer> {
}
