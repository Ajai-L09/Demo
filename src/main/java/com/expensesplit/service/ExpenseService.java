package com.expensesplit.service;

import com.expensesplit.model.Expense;
import com.expensesplit.model.ExpenseGroup;
import com.expensesplit.model.User;
import com.expensesplit.repository.ExpenseGroupRepository;
import com.expensesplit.repository.ExpenseRepository;
import com.expensesplit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseGroupRepository expenseGroupRepository;

    public Expense createExpense(Expense expense) {
        ExpenseGroup group = expenseGroupRepository.findById(expense.getGroup().getGroupId())
                .orElseThrow(()->new RuntimeException("Group not found"));
        User paidBy = userRepository.findById(expense.getPaidBy().getUserId())
                .orElseThrow(()->new RuntimeException("User (payer) not found"));

        expense.setGroup(group);
        expense.setPaidBy(paidBy);
        return expenseRepository.save(expense);
    }
    public List<Expense> getExpensesForGroup(Integer groupId) {
        return expenseRepository.findByGroup_GroupId(groupId);
    }
}