package development.com.dao;

import development.com.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book order by title",
                new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE bookId = ?",
                new BeanPropertyRowMapper<>(Book.class), id).stream().findAny().orElse(null);
    }
    public void add(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear()
        );
    }
    public void edit(int bookId, Book book) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE bookId=?",
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                bookId
        );
    }
    public void delete(int bookId) {
        jdbcTemplate.update("DELETE FROM Book WHERE bookid = ?", bookId);
    }
    public void addOwner(int idBook, int idPerson) {
        jdbcTemplate.update("UPDATE Book SET personid = ? WHERE bookid = ?", idPerson, idBook);
    }
    public void deleteOwner(int idBook) {
        jdbcTemplate.update("UPDATE Book SET personid = null WHERE bookid = ?", idBook);
    }
}
