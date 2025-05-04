package myLibrary.service;

import myLibrary.entity.Book;
import myLibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAllWithAuthors();
    }

    public Book saveBook(Book book) {
        // Check if a book with the same ISBN already exists
        if (bookRepository.findByIsbn(book.getIsbn()).isPresent()) {
            // If it exists, throw an exception or return null to indicate the issue
            throw new IllegalArgumentException("This ISBN already exists in the system.");
        }
        
        // If no existing book with that ISBN, proceed with saving the new book
        return bookRepository.save(book);
    }


    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    public void updateBook(Long id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
