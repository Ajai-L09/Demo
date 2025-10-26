package com.expensesplit.service;

import com.expensesplit.model.ExpenseGroup;
import com.expensesplit.model.GroupMember;
import com.expensesplit.model.User;
import com.expensesplit.repository.ExpenseGroupRepository;
import com.expensesplit.repository.GroupMemberRepository;
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
    @Autowired
    private GroupMemberRepository groupMemberRepository;

    public ExpenseGroup createGroup(ExpenseGroup group) {
        User createdBy = userRepository.findById(group.getCreatedBy().getUserId()).orElseThrow(()->new RuntimeException("User not found"));
        group.setCreatedBy(createdBy);
        return expenseGroupRepository.save(group);
    }
    public List<ExpenseGroup> getAllGroups() {
        return expenseGroupRepository.findAll();
    }
    public GroupMember addMemberToGroup(Integer groupId, GroupMember groupMember) {
        ExpenseGroup group = expenseGroupRepository.findById(groupId).orElseThrow(()->new RuntimeException("Group not found"));

        User user = userRepository.findById(groupMember.getUser().getUserId()).orElseThrow(()->new RuntimeException("User not found"));
        groupMember.setUser(user);
        groupMember.setGroup(group);

        return groupMemberRepository.save(groupMember);
    }

    public List<GroupMember> getGroupMembers(Integer groupId) {
        return groupMemberRepository.findByGroup_GroupId(groupId);
    }

}
