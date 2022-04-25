package emt.lab2library.service;

import emt.lab2library.model.Books;
import emt.lab2library.model.dto.BooksDto;
import emt.lab2library.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BooksService {

   Optional<Books> findById(Long id);

    List<Books> listAll();

    Optional<Books> create(String name, Category category, Long authorId, Integer availableCopies);

    Optional<Books> create(BooksDto booksDto);

    Optional<Books> update(Long id, String name, Category category, Long authorId, Integer availableCopies);

    Optional<Books> update(Long id,BooksDto booksDto);

    void deleteById(Long id);

    Optional<Books> markAsTaken(Long id);
}
