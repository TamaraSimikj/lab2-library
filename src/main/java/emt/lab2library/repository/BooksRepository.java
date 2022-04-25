package emt.lab2library.repository;

import emt.lab2library.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface BooksRepository extends JpaRepository< Books ,Long > {

    void deleteByName(String name);
    Page<Books> findAll(Pageable pageable);
}
