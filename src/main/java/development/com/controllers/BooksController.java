package development.com.controllers;

import development.com.dao.BookDAO;
import development.com.dao.PersonDAO;
import development.com.models.Book;
import development.com.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping // Все книги
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{bookId}") // Вывод информации о книге и ее владельце
    public String show(@PathVariable("bookId") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        Optional<Person> owner = personDAO.getPersonByBook(id);
        if(owner.isPresent())
            model.addAttribute("owner", owner.get());
        else
            model.addAttribute("people", personDAO.index());
        return "books/show";
    }
    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("newBook" , new Book());
        return "books/new";
    }

    @GetMapping("/{bookId}/edit")
    public String editBook(@PathVariable("bookId") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("newBook") @Valid Book book,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return("books/new");
        bookDAO.add(book);
        return "redirect:/books";
    }

    @PatchMapping("/{bookId}")
    public String patch(@ModelAttribute("book") @Valid Book book,
                        BindingResult bindingResult,
                        @PathVariable("bookId") int id) {
        if(bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.edit(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{bookId}")
    public String delete(@PathVariable("bookId") int id) {
        bookDAO.delete(id);
        return"redirect:/books";
    }

    @PatchMapping("/{bookId}/addOwner") // Добавление нового владельца книге
    public String addOwner(@PathVariable("bookId") int id, @ModelAttribute("book") Book book) {
        bookDAO.addOwner(id, book.getPersonId());
        return "redirect:/books/" + id;
    }
    @PatchMapping("/{bookId}/deleteOwner") // Удаление владельца книги
    public String deleteOwner(@PathVariable("bookId") int id) {
        bookDAO.deleteOwner(id);
        return "redirect:/books/" + id;
    }
}
