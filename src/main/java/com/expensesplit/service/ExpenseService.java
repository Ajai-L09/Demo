package com.expensesplit.service;

import com.expensesplit.model.Expense;
import com.expensesplit.model.ExpenseGroup;
import com.expensesplit.model.ExpenseParticipant;
import com.expensesplit.model.User;
import com.expensesplit.repository.ExpenseGroupRepository;
import com.expensesplit.repository.ExpenseParticipantRepository;
import com.expensesplit.repository.ExpenseRepository;
import com.expensesplit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseGroupRepository expenseGroupRepository;
    @Autowired
    private ExpenseParticipantRepository expenseParticipantRepository;

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
    @Transactional
    public List<ExpenseParticipant> addExpenseParticipant(Integer expenseId, List<ExpenseParticipant> participants) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(()->new RuntimeException ("Expense not found"));
        BigDecimal totalShare = BigDecimal.ZERO;
        List<ExpenseParticipant> savedParticipants = new ArrayList<>();
        for(ExpenseParticipant participant : participants) {
            User user = userRepository.findById(participant.getUser().getUserId()).orElseThrow(()->new RuntimeException("Participant user not found"));
            participant.setExpense(expense);
            participant.setUser(user);
            totalShare = totalShare.add(participant.getShareAmount());
            savedParticipants.add(expenseParticipantRepository.save(participant));
        }

        if(expense.getAmount().compareTo(totalShare) != 0) {
            throw new RuntimeException("Share do not add up to the total expense amount. Total: "+expense.getAmount()+" Share: "+totalShare);
        }
        return savedParticipants;
    }

    public List<ExpenseParticipant> getParticipantsForExpense(Integer expenseId) {
        return expenseParticipantRepository.findByExpense_ExpenseId(expenseId);
    }
}