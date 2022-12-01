package com.marmuz.dao;

import com.marmuz.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> getAllBook() {
        return jdbcTemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Book (title,author,year) VALUES (?,?,?)"
                , book.getTitle(), book.getAuthor(), book.getYear());
    }

    public Book findBookById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id =?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void updateBook(Book book, int id) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?"
                , book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }


}
