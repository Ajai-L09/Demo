package com.expensesplit.dto;

import com.expensesplit.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class BalanceDTO {
    private User user;
    private BigDecimal netBalance;
}
