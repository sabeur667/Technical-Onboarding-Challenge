package com.Onboarding.CodingTest.endpoints;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Onboarding.CodingTest.Filters.TransactionFilter;
import com.Onboarding.CodingTest.domains.Transaction;
import com.Onboarding.CodingTest.services.Transactionservice;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
	@Autowired
	private Transactionservice transactionService;

	/**
	 * Récupère une liste de transactions de carte de crédit avec la possibilité de
	 * filtrer, trier et paginer les résultats.
	 *
	 * @param amount   Le montant de la transaction à filtrer (facultatif).
	 * @param merchant Le nom du marchand où la transaction a été effectuée à
	 *                 filtrer (facultatif).
	 * @param status   Le statut de la transaction à filtrer (facultatif).
	 * @param page     Le numéro de la page de pagination (par défaut : 1).
	 * @param pageSize La taille de la page pour la pagination (par défaut : 26).
	 * @param sortBy   Le champ de tri des transactions (facultatif).
	 * @param sortD    La direction de tri (ascendant ou descendant) (facultatif).
	 * @return Une liste de transactions filtrée, triée et paginée.
	 * @throws IOException En cas d'erreur de lecture des données.
	 */
	@GetMapping
	public ResponseEntity<List<Transaction>> getTransactions(@RequestParam(required = false) BigDecimal amount,
			@RequestParam(required = false) String merchant, @RequestParam(required = false) String status,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize,
			@RequestParam(required = false) String sortBy, @RequestParam(required = false) String sortD)
			throws IOException {

		TransactionFilter filter = new TransactionFilter();
		filter.setAmount(amount);
		filter.setMerchant(merchant);
		filter.setStatus(status);
		// Récupérer la liste des transactions filtrées et triées depuis le service
		List<Transaction> transactions = transactionService.getFilteredTransactions(filter, sortBy, sortD);

		List<Transaction> transactionspagination;
		// Calculer les indices de début et de fin pour la pagination
		int startIndex = (page - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, transactions.size());
		// Appliquer la pagination en fonction des indices calculés
		if (startIndex > transactions.size()) {
			transactionspagination = transactions;
		} else if (endIndex > transactions.size()) {
			transactionspagination = transactions.subList(startIndex, transactions.size());
		} else {
			transactionspagination = transactions.subList(startIndex, endIndex);
		}

		return new ResponseEntity<List<Transaction>>(transactionspagination, HttpStatus.OK);
	}
}