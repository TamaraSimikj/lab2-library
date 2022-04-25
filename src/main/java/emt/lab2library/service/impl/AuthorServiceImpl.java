package emt.lab2library.service.impl;

import emt.lab2library.model.Author;
import emt.lab2library.model.Country;
import emt.lab2library.model.exceptions.AuthorNotFoundException;
import emt.lab2library.repository.AuthorRepository;
import emt.lab2library.repository.CountryRepository;
import emt.lab2library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(()-> new AuthorNotFoundException(id));
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public void delete(Long id) {
      Author author =  this.findById(id);
      this.authorRepository.delete(author);
    }

    @Override
    public Author create(String name, String surname, Long countryId) {
        Country country = this.countryRepository.getById(countryId);
        Author author = new Author(name,surname,country);
        this.authorRepository.save(author);
        return author;
    }

    @Override
    public Author update(Long id, String name, String surname, Long countryId) {
        Author author = this.findById(id);
        author.setName(name);
        author.setSurname(surname);
        Country country = this.countryRepository.getById(countryId);
        author.setCountry(country);
        this.authorRepository.save(author);
        return author;
    }
}
