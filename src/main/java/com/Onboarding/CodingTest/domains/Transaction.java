package com.Onboarding.CodingTest.domains;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Transaction {
	private Long id;
	private BigDecimal amount;
	private String merchant;
	private String status;
	private Date date;

}
