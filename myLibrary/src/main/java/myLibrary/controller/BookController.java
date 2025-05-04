package myLibrary.controller;

import myLibrary.entity.Author;
import myLibrary.entity.Book;
import myLibrary.service.BookService;
import myLibrary.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("book", bookService.getAllBooks());
        return "book/list"; // Fixed view name
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book/add"; // Fixed view name
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            return "book/add";
        }
        try {
            bookService.saveBook(book);
            return "redirect:/book";
        } catch (Exception e) {
            result.reject("globalError", "Error saving book: " + e.getMessage());
            model.addAttribute("authors", authorService.getAllAuthors());
            return "book/add";
        }
    }

    
    /*@GetMapping("/update/{id}")
   public String showEditForm(@PathVariable Long id, Model model) {
       model.addAttribute("book", bookService.getBookById(id));
       model.addAttribute("authors", authorService.getAllAuthors());
       return "book/update";
   }*/
   @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
    Book book = bookService.getBookById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
    model.addAttribute("book", book);
    model.addAttribute("authors", authorService.getAllAuthors());
    return "book/update";
    }



    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        try {
            Author author = book.getAuthor() != null ? authorService.getAuthorById(book.getAuthor().getId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + book.getAuthor().getId())) : null;
            if (author != null) {
                book.setAuthor(author);
            }
            bookService.updateBook(id, book);
            redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating book: " + e.getMessage());
        }
        return "redirect:/book";
    }

    @GetMapping("/by-author/{authorId}")
   public String listBooksByAuthor(@PathVariable Long authorId, Model model) {
       model.addAttribute("books", bookService.getBookById(authorId));
       model.addAttribute("author", authorService.getAuthorById(authorId));
       return "book/list";
   }

   @GetMapping("/delete/{id}")
   public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
       try {
           bookService.deleteBook(id);
           redirectAttributes.addFlashAttribute("successMessage", "Book deleted successfully!");
       } catch (Exception e) {
           redirectAttributes.addFlashAttribute("errorMessage", "Error deleting book: " + e.getMessage());
       }
       return "redirect:/book";
   }

   

}