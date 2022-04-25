package emt.lab2library.service;

import emt.lab2library.model.Author;

import java.util.List;

public interface AuthorService {

    Author findById(Long id);

    List<Author> listAll();

    void delete(Long id);

    Author create(String name, String surname, Long countryId);

    Author update(Long id, String name, String surname, Long countryId);
}
