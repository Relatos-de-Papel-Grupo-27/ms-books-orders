package com.unir.orders.facade;

import com.unir.orders.facade.model.Account;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class AccountsFacade {

    private final WebClient webClient;

    public AccountsFacade(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public Account getAccountById(Long accountId) {
        return webClient
                .get()
                .uri("lb://ms-books-accounts/accounts/{id}", accountId)
                .retrieve()
                .bodyToMono(Account.class)
                .block();
    }
}