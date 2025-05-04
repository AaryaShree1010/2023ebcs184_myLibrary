package myLibrary.service;

import myLibrary.entity.Author;
import myLibrary.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    private Author testAuthor;

    @BeforeEach
    public void setUp() {
        testAuthor = new Author();
        testAuthor.setId(1L);
        testAuthor.setName("Arya");

        when(authorRepository.findAll()).thenReturn(List.of(testAuthor));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
    }

    @Test
    public void testGetAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        assertEquals(1, authors.size());
        assertEquals("Aarya", authors.get(0).getName());
    }

    @Test
    public void testGetAuthorById() {
        Optional<Author> author = authorService.getAuthorById(1L);
        assertTrue(author.isPresent());
        assertEquals("Arya", author.get().getName());
    }

    @Test
    public void testSaveAuthor() {
        Author newAuthor = new Author();
        newAuthor.setName("John");
        when(authorRepository.save(newAuthor)).thenReturn(newAuthor);

        Author savedAuthor = authorService.saveAuthor(newAuthor);
        assertEquals("John", savedAuthor.getName());
    }

    @Test
    public void testDeleteAuthor() {
        authorService.deleteAuthor(testAuthor);
        // Note: Since we're mocking, we can't verify actual deletion, but we can test the method call
        // Add verification if needed with Mockito.verify(authorRepository).delete(testAuthor);
    }
}