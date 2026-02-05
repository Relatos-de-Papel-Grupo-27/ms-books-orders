package com.unir.orders.facade;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BooksCatalogueFacade {

    private final WebClient webClient;

    public BooksCatalogueFacade(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public boolean isBookAvailable(Long bookId) {
        return webClient
                .get()
                .uri("lb://ms-books-catalogue/api/v1/books/{id}/availability", bookId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
