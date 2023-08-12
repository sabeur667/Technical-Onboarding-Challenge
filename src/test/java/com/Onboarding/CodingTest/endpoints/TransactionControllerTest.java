package com.Onboarding.CodingTest.endpoints;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.Onboarding.CodingTest.endpoints.TransactionController;
import com.Onboarding.CodingTest.services.Transactionservice;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private Transactionservice service;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		
		when(service.getFilteredTransactions(null, null, null)).thenReturn(new ArrayList<>());
		this.mockMvc.perform(get("/Transaction")).andDo(print()).andExpect(status().isOk());
		}
}