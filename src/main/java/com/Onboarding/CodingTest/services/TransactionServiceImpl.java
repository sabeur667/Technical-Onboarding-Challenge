package com.Onboarding.CodingTest.services;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Onboarding.CodingTest.Filters.TransactionFilter;
import com.Onboarding.CodingTest.domains.Transaction;
import com.Onboarding.CodingTest.repositories.TransactionRepository;

@Service
public class TransactionServiceImpl implements Transactionservice {
	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * Récupère une liste de transactions filtrée et triée en fonction des
	 * paramètres fournis.
	 *
	 * @param transactionFilter Filtre de transactions contenant les critères de
	 *                          filtrage.
	 * @param sortBy            Champ de tri des transactions (facultatif).
	 * @param sortDirection     Direction de tri (ascendant ou descendant)
	 *                          (facultatif).
	 * @return Liste de transactions filtrée et triée.
	 * @throws IOException En cas d'erreur de lecture des données.
	 */

	@Override
	public List<Transaction> getFilteredTransactions(TransactionFilter transactionFilter, String sortBy,
			String sortDirection) throws IOException {
		// Récupérer la liste de transactions filtrée en fonction du filtre fourni
		List<Transaction> listTransactions = transactionRepository.getFilteredTransactions(transactionFilter);

		// Appliquer le tri si le champ de tri est spécifié
		Comparator<Transaction> comparator;
		if (sortBy != null) {
			if ("amount".equals(sortBy)) {
				comparator = Comparator.comparing(Transaction::getAmount);
			} else if ("merchant".equals(sortBy)) {
				comparator = Comparator.comparing(Transaction::getMerchant);
			} else if ("status".equals(sortBy)) {
				comparator = Comparator.comparing(Transaction::getStatus);
			} else {
				throw new IllegalArgumentException("Invalid sortBy parameter");
			}

			if ("desc".equals(sortDirection)) {
				comparator = comparator.reversed();
			}

			// Trier la liste de transactions en utilisant le comparateur
			listTransactions.sort(comparator);
		}
		return listTransactions;

	}

}
