package myLibrary.repository;

import myLibrary.entity.Author;
import myLibrary.entity.Book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
public void testFindAllWithBooks() {
    Author author = new Author();
    author.setName("Test Author");
    author.setNationality("Test");
    Book book = new Book();
    book.setTitle("Test Book");
    book.setIsbn("123456");
    author.getBooks().add(book);
    book.setAuthor(author);
    author = authorRepository.save(author); // Save with book
    List<Author> authors = authorRepository.findAllWithBooks();
    assertThat(authors).isNotEmpty();
    assertThat(authors.get(0).getBooks()).isNotEmpty();
}
    @Test
    public void testFindById() {
        Author author = new Author();
        author.setName("Test Author");
        author.setNationality("Test");
        author = authorRepository.save(author);
        Author foundAuthor = authorRepository.findById(author.getId()).orElse(null);
        assertNotNull(foundAuthor);
        assertEquals(author.getName(), foundAuthor.getName());
    }

    @Test
    public void testSave() {
        Author author = new Author();
        author.setName("Test Author");
        author.setNationality("Test");
        Author savedAuthor = authorRepository.save(author);
        assertNotNull(savedAuthor.getId());
    }

    @Test
    public void testDelete() {
        Author author = new Author();
        author.setName("Test Author");
        author.setNationality("Test");
        author = authorRepository.save(author);
        Long id = author.getId();
        authorRepository.deleteById(id);
        assertThat(authorRepository.findById(id)).isEmpty();
    }
}

