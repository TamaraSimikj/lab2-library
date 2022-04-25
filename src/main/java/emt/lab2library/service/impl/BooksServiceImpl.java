package emt.lab2library.service.impl;

import emt.lab2library.model.Author;
import emt.lab2library.model.Books;
import emt.lab2library.model.dto.BooksDto;
import emt.lab2library.model.enumerations.Category;
import emt.lab2library.model.exceptions.AuthorNotFoundException;
import emt.lab2library.model.exceptions.BookNotFoundException;
import emt.lab2library.repository.AuthorRepository;
import emt.lab2library.repository.BooksRepository;
import emt.lab2library.service.BooksService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;
    private final AuthorRepository authorRepository;

    public BooksServiceImpl(BooksRepository booksRepository, AuthorRepository authorRepository) {
        this.booksRepository = booksRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Books> findById(Long id) {
        return this.booksRepository.findById(id);
    }

    @Override
    public List<Books> listAll() {
        return this.booksRepository.findAll();
    }

    @Override
    public Optional<Books> create(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(()-> new AuthorNotFoundException(authorId));
        Books b = new Books(name,category,author,availableCopies);
        this.booksRepository.save(b);
        return Optional.of(b);
    }

    @Transactional
    @Override
    public Optional<Books> create(BooksDto booksDto) {
        Author author = this.authorRepository.findById(booksDto.getAuthor())
                .orElseThrow(()->new AuthorNotFoundException(booksDto.getAuthor()));

        this.booksRepository.deleteByName(booksDto.getName());
        Books book = new Books(booksDto.getName(),booksDto.getCategory(),author,booksDto.getAvailableCopies());
        this.booksRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Books> update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Books b = this.booksRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        b.setName(name);
        b.setCategory(category);
        Author author = this.authorRepository.findById(authorId).orElseThrow(()-> new AuthorNotFoundException(authorId));
        b.setAuthor(author);
        b.setAvailableCopies(availableCopies);

        this.booksRepository.save(b);
        return Optional.of(b);
    }

    @Override
    public Optional<Books> update(Long id, BooksDto booksDto) {
        Books b = this.booksRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        b.setName(booksDto.getName());
        b.setCategory(booksDto.getCategory());
        Author author = this.authorRepository.findById(booksDto.getAuthor())
                .orElseThrow(()-> new AuthorNotFoundException(booksDto.getAuthor()));
        b.setAuthor(author);
        this.booksRepository.save(b);
        return Optional.of(b);
    }

    @Override
    public void deleteById(Long id) {
        Books b = this.booksRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        this.booksRepository.delete(b);
    }

    @Override
    public Optional<Books> markAsTaken(Long id) {
        Books b = this.booksRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        Integer copies = b.getAvailableCopies();
        if(copies >0){
            b.setAvailableCopies(copies-1);
            this.booksRepository.save(b);
        }
        return Optional.of(b);
    }
}
