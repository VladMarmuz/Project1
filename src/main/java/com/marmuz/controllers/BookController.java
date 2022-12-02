package com.marmuz.controllers;

import com.marmuz.dao.BookDAO;
import com.marmuz.dao.PersonDAO;
import com.marmuz.models.Book;
import com.marmuz.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookDAO bookDAO;
    private PersonDAO personDAO;

    @Autowired

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String allBooks(Model model){
        model.addAttribute("books",bookDAO.getAllBook());
        return "book/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/create";
    }

    @PostMapping("")
    public String createBook(@ModelAttribute("book") Book book, BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return "book/create";
        else
            bookDAO.create(book);

        return "redirect:/books";
    }


    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model,@ModelAttribute("person") Person person){
        model.addAttribute("book",bookDAO.findBookById(id));

        Optional<Person> owner = bookDAO.getPersonOwner(id);
        if(owner.isPresent())
            model.addAttribute("person",owner.get());
        else
            model.addAttribute("people", personDAO.index());

        return "book/show";
    }

    @GetMapping("/{id}/edit")
    public String findBookForEdit(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookDAO.findBookById(id));
        return "book/update";
    }

    @PostMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book,
                             BindingResult bindingResult,
                             @PathVariable("id") int id){
        if(bindingResult.hasErrors())
            return "book/update";
        else
            bookDAO.updateBook(book, id);

        return "redirect:/books";
    }

    @PostMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/release")
    public String releaseBook(@PathVariable("id")int id){
        bookDAO.release(id);
        return "redirect:/books/"+id;
    }

    @PostMapping("/{id}/assign")
    public String assignBook(@PathVariable("id")int id, @ModelAttribute("person") Person person){
        bookDAO.assign(id,person);
        return "redirect:/books/"+id;
    }



}
