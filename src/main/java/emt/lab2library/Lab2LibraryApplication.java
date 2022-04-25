package emt.lab2library;

import emt.lab2library.model.Author;
import emt.lab2library.model.Country;
import emt.lab2library.model.dto.BooksDto;
import emt.lab2library.model.enumerations.Category;
import emt.lab2library.service.AuthorService;
import emt.lab2library.service.BooksService;
import emt.lab2library.service.CountryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Lab2LibraryApplication {

    private final CountryService countryService;
    private final AuthorService authorService;
    private final BooksService bookService;

    public Lab2LibraryApplication(CountryService countryService, AuthorService authorService, BooksService bookService) {
        this.countryService = countryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    public static void main(String[] args) {
        SpringApplication.run(Lab2LibraryApplication.class, args);
    }

    @PostConstruct
    public void init() {
        Country country = countryService.create("United Kingdom","Europe");
        Country country1 = countryService.create("Russia", "Europe");
        authorService.create("William","Shakespeare",country.getId());
        authorService.create("Agatha", "Christie", country.getId());

        authorService.create("Fyodor", "Dostoevsky", country1.getId());

        bookService.create(new BooksDto("Book1", Category.FANTASY, 1L, 2));
        bookService.create(new BooksDto("Book2", Category.FANTASY, 1L, 5));
        bookService.create(new BooksDto("Book3", Category.FANTASY, 1L, 2));
        bookService.create(new BooksDto("Book4", Category.FANTASY, 1L, 5));
        bookService.create(new BooksDto("Book5", Category.FANTASY, 1L, 5));
        bookService.create(new BooksDto("Book6", Category.FANTASY, 1L, 5));
        bookService.create(new BooksDto("Book7", Category.FANTASY, 1L, 3));
        bookService.create(new BooksDto("Book8", Category.FANTASY, 1L, 5));

        bookService.create(new BooksDto("Book9", Category.FANTASY, 3L, 5));
        bookService.create(new BooksDto("Book10", Category.FANTASY, 3L, 3));
        bookService.create(new BooksDto("Book11", Category.FANTASY, 3L, 3));
        bookService.create(new BooksDto("Book12", Category.FANTASY, 3L, 3));
        bookService.create(new BooksDto("Book13", Category.FANTASY, 3L, 3));
        bookService.create(new BooksDto("Book14", Category.FANTASY, 3L, 5));
        bookService.create(new BooksDto("Book15", Category.FANTASY, 3L, 5));


    }
}

