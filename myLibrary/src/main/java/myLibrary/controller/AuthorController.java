package myLibrary.controller;

import myLibrary.entity.Author;
import myLibrary.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        System.out.println("Number of authors retrieved: " + authors.size()); // Debug
        authors.forEach(author -> System.out.println("Author: " + author)); // Debug
        model.addAttribute("authors", authors);
        return "author/list";
    }

    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/add";
    }

    @PostMapping("/add")
    public String addAuthor(@Valid @ModelAttribute Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "author/add";
        }
        try {
            authorService.saveAuthor(author);
            return "redirect:/author";
        } catch (Exception e) {
            result.reject("globalError", "Error saving author: " + e.getMessage());
            return "author/add";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        try {
            Author author = authorService.getAuthorById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));
            model.addAttribute("author", author);
            return "author/update";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/author";
        }
    }

    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable("id") Long id, @Valid @ModelAttribute Author author, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "author/update";
        }
        try {
            author.setId(id);
            authorService.saveAuthor(author);
            return "redirect:/author";
        } catch (Exception e) {
            result.reject("globalError", "Error updating author: " + e.getMessage());
            return "author/update";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Long id, Model model) {
        try {
            Author author = authorService.getAuthorById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid author Id:" + id));
            authorService.deleteAuthor(author);
            return "redirect:/author";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/author";
        }
    }
}