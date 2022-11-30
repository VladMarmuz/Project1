package com.marmuz.controllers;

import com.marmuz.dao.PersonDAO;
import com.marmuz.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO personDAO;


    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "create";
    }

    @PostMapping("")
    public String create(@ModelAttribute("person") Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.findPersonById(id));
        model.addAttribute("books", personDAO.getAllBookByPersonID(id));

        return "show";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.findPersonById(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") Person person,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "edit";
        }
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @PostMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
