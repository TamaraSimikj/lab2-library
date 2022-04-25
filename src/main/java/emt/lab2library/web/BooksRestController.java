package emt.lab2library.web;

import emt.lab2library.model.Books;
import emt.lab2library.model.dto.BooksDto;
import emt.lab2library.service.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api")
public class BooksRestController {

    private final BooksService booksService;

    public BooksRestController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping({"/","/books"})
    private List<Books> findAll() {
        return this.booksService.listAll();
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Books> findById(@PathVariable Long id) {
        return this.booksService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("books/add")
    public ResponseEntity<Books> addBook(@RequestBody BooksDto booksDto) {
        return this.booksService.create(booksDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("books/edit/{id}")
    public ResponseEntity<Books> update(@PathVariable Long id, @RequestBody BooksDto booksDto) {
        return this.booksService.update(id, booksDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("books/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.booksService.deleteById(id);
        if(this.booksService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("books/markAsTaken/{id}")
    public ResponseEntity<Books> markAsTaken(@PathVariable Long id){
            this.booksService.markAsTaken(id);
            return ResponseEntity.ok().build();
    }
}
