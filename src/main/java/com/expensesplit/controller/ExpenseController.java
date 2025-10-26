package com.expensesplit.controller;

import com.expensesplit.model.Expense;
import com.expensesplit.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense createdExpense = expenseService.createExpense(expense);
        return ResponseEntity.ok(createdExpense);
    }
    @GetMapping("/group/{id}")
    public ResponseEntity<List<Expense>> getExpensesForGroup(@PathVariable Integer id) {
        List<Expense> expenses = expenseService.getExpensesForGroup(id);
        return ResponseEntity.ok(expenses);
    }
}