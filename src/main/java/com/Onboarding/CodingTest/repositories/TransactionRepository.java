package com.Onboarding.CodingTest.repositories;

import java.io.IOException;
import java.util.List;

import com.Onboarding.CodingTest.Filters.TransactionFilter;
import com.Onboarding.CodingTest.domains.Transaction;

public interface TransactionRepository {
	List<Transaction> getFilteredTransactions(TransactionFilter filter) throws IOException;
}
