package com.Onboarding.CodingTest.Filters;

import java.math.BigDecimal;

import lombok.Data;
@Data
public class TransactionFilter {
	private BigDecimal amount;
	private String merchant;
	private String status;

}
