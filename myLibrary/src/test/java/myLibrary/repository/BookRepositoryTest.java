package myLibrary.repository;

import myLibrary.entity.Author;
import myLibrary.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
   
    public void testFindAllWithAuthors() {
    Author author = new Author();
    author.setName("Test Author");
    author = authorRepository.save(author); // Persist author first
    Book book = new Book();
    book.setTitle("Test Book");
    book.setIsbn("123456");
    book.setAuthor(author);
    bookRepository.save(book); // Persist book with author
    List<Book> books = bookRepository.findAllWithAuthors();
    assertThat(books).isNotEmpty();
    assertThat(books.get(0).getAuthor()).isNotNull();
}
    @Test
    public void testFindByAuthorId() {
        Author author = new Author();
        author.setName("Test Author");
        author = authorRepository.save(author); // Persist author first
        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn("123456");
        book.setAuthor(author);
        bookRepository.save(book); // Persist book with author
        List<Book> books = bookRepository.findByAuthorId(author.getId());
        assertThat(books).isNotEmpty();
        assertThat(books.get(0).getAuthor()).isNotNull();
    }
}