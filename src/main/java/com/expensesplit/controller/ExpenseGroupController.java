package com.expensesplit.controller;

import com.expensesplit.model.ExpenseGroup;
import com.expensesplit.service.ExpenseGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class ExpenseGroupController {
    @Autowired
    private ExpenseGroupService expenseGroupService;
    @PostMapping
    public ResponseEntity<ExpenseGroup> createGroup(@RequestBody ExpenseGroup group){
        ExpenseGroup createdGroup = expenseGroupService.createGroup(group);
        return ResponseEntity.ok(createdGroup);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseGroup>> getAllGroups(){
        List<ExpenseGroup> groups = expenseGroupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }
}
