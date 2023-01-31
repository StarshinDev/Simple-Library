package development.com.controllers;

import development.com.dao.PersonDAO;
import development.com.models.Person;
import development.com.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final PersonValidator validator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator validator) {
        this.personDAO = personDAO;
        this.validator = validator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,  Model model) {
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.getBooksByPerson(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("newPerson", new Person());
        return "people/new";
    }
    @GetMapping("/{personId}/edit")
    public String editPerson(@PathVariable("personId") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }
    @PostMapping
    public String create(@ModelAttribute("newPerson") @Valid Person person,
                         BindingResult bindingResult) {
        validator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";
        personDAO.add(person);
        return "redirect:/people";
    }
    @PatchMapping("/{personId}")
    public String patch(@ModelAttribute("person") @Valid Person person,
                                 BindingResult bindingResult,
                                 @PathVariable("personId") int id) {
        validator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.edit(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
