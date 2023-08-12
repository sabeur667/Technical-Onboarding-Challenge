package com.Onboarding.CodingTest.repositories;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.Onboarding.CodingTest.Filters.TransactionFilter;
import com.Onboarding.CodingTest.domains.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
	/**
	 * Récupère les données des transactions à partir du fichier JSON.
	 *
	 * @return Liste des transactions à partir du fichier JSON.
	 * @throws IOException En cas d'erreur de lecture des données.
	 */
	public List<Transaction> getDataFromJson() throws IOException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
		ClassPathResource resource = new ClassPathResource("transactionsMock.json");
		Reader reader = new InputStreamReader(resource.getInputStream());
		Transaction[] transactionsArray = gson.fromJson(reader, Transaction[].class);
		return Arrays.asList(transactionsArray);

	}

	/**
	 * Récupère une liste de transactions filtrée en fonction du filtre fourni.
	 *
	 * @param transactionFilter Filtre de transactions contenant les critères de
	 *                          filtrage.
	 * @return Liste de transactions filtrée.
	 * @throws IOException En cas d'erreur de lecture des données.
	 */
	public List<Transaction> getFilteredTransactions(TransactionFilter transactionFilter) throws IOException {

		return getDataFromJson().stream().filter(transaction -> filterTransaction(transaction, transactionFilter))
				.collect(Collectors.toList());
	}

	/**
	 * Filtre une transaction en fonction des critères du filtre.
	 *
	 * @param transaction       La transaction à filtrer.
	 * @param transactionFilter Filtre de transactions contenant les critères de
	 *                          filtrage.
	 * @return True si la transaction passe le filtrage, sinon False.
	 */
	private Boolean filterTransaction(Transaction transaction, TransactionFilter transactionFilter) {
		if (transactionFilter.getAmount() != null && !transaction.getAmount().equals(transactionFilter.getAmount())) {
			return false;
		}
		if (transactionFilter.getMerchant() != null
				&& !transaction.getMerchant().equals(transactionFilter.getMerchant())) {
			return false;
		}
		if (transactionFilter.getStatus() != null && !transaction.getStatus().equals(transactionFilter.getStatus())) {
			return false;
		}
		return true;
	}
}
