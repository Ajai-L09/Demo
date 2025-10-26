package com.expensesplit.service;

import com.expensesplit.model.ExpenseGroup;
import com.expensesplit.model.User;
import com.expensesplit.repository.ExpenseGroupRepository;
import com.expensesplit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseGroupService {
    @Autowired
    private ExpenseGroupRepository expenseGroupRepository;
    @Autowired
    private UserRepository userRepository;

    public ExpenseGroup createGroup(ExpenseGroup group) {
        User createdBy = userRepository.findById(group.getCreatedBy().getUserId()).orElseThrow(()->new RuntimeException("User not found"));
        group.setCreatedBy(createdBy);
        return expenseGroupRepository.save(group);
    }
    public List<ExpenseGroup> getAllGroups() {
        return expenseGroupRepository.findAll();
    }


}
