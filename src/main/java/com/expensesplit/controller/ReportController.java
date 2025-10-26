package com.expensesplit.controller;

import com.expensesplit.dto.BalanceDTO;
import com.expensesplit.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @GetMapping("/group/{id}/balance")
    public ResponseEntity<List<BalanceDTO>>getGroupBalance(@PathVariable Integer id) {
        List<BalanceDTO> balances = reportService.calculateGroupBalances(id);
        return ResponseEntity.ok(balances);
    }
}