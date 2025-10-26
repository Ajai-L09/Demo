package com.expensesplit.repository;

import com.expensesplit.model.ExpenseGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseGroupRepository extends JpaRepository<ExpenseGroup, Integer> {
}
