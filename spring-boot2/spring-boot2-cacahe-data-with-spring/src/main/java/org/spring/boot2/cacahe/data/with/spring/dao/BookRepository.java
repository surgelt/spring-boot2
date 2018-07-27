package org.spring.boot2.cacahe.data.with.spring.dao;

import org.spring.boot2.cacahe.data.with.spring.entity.Book;

public interface BookRepository {
    Book getByIsbn(String isbn);
}