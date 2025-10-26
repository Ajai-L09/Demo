package com.expensesplit.service;

import com.expensesplit.dto.BalanceDTO;
import com.expensesplit.model.Expense;
import com.expensesplit.model.ExpenseParticipant;
import com.expensesplit.model.GroupMember;
import com.expensesplit.model.User;
import com.expensesplit.repository.ExpenseParticipantRepository;
import com.expensesplit.repository.ExpenseRepository;
import com.expensesplit.repository.GroupMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private ExpenseParticipantRepository expenseParticipantRepository;

    public List<BalanceDTO> calculateGroupBalances(Integer groupId) {
        Map<Integer, BigDecimal> userBalances = new HashMap<>();
        List<GroupMember> members = groupMemberRepository.findByGroup_GroupId(groupId);
        if (members.isEmpty()) {
            throw new RuntimeException("Group not found or has no members");
        }

        for (GroupMember member : members) {
            userBalances.put(member.getUser().getUserId(), BigDecimal.ZERO);
        }

        List<Expense> expenses = expenseRepository.findByGroup_GroupId(groupId);

        for (Expense expense : expenses) {
            User payer = expense.getPaidBy();
            userBalances.put(payer.getUserId(),
                    userBalances.get(payer.getUserId()).add(expense.getAmount()));

            List<ExpenseParticipant> participants = expenseParticipantRepository.findByExpense_ExpenseId(expense.getExpenseId());
            for (ExpenseParticipant participant : participants) {
                User user = participant.getUser();
                userBalances.put(user.getUserId(),
                        userBalances.get(user.getUserId()).subtract(participant.getShareAmount()));
            }
        }

        return members.stream().map(member -> {
            User user = member.getUser();
            BigDecimal balance = userBalances.get(user.getUserId());
            return new BalanceDTO(user, balance);
        }).collect(Collectors.toList());
    }
}
