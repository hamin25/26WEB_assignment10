package com.webservice.sample.dto;

import com.webservice.sample.entity.Book;

import java.time.LocalDateTime;

public record BookResponse(
        Long id, String title, String author, Integer price, String category, LocalDateTime createdAt, LocalDateTime updatedAt
) {
    public static BookResponse from(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(),
                book.getPrice(), book.getCategory(), book.getCreadtedAt(), book.getUpdatedAt());
    }
}
