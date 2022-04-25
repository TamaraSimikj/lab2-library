package emt.lab2library.model.dto;

import emt.lab2library.model.Author;
import emt.lab2library.model.enumerations.Category;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Data
public class BooksDto {

    private String name;

    private Category category;

    private Long author;

    private Integer availableCopies;

    public BooksDto(){}

    public BooksDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
