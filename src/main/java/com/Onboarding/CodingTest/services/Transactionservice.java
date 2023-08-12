package com.Onboarding.CodingTest.services;

import java.io.IOException;
import java.util.List;

import com.Onboarding.CodingTest.Filters.TransactionFilter;
import com.Onboarding.CodingTest.domains.Transaction;

public interface Transactionservice {

	List<Transaction> getFilteredTransactions(TransactionFilter transactionFilter, String SortBy, String sortDirection)
			throws IOException;
}
