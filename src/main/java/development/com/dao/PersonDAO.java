package development.com.dao;

import development.com.models.Book;
import development.com.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person order by name",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE personId = ?",
                new BeanPropertyRowMapper<>(Person.class), id).stream().findAny().orElse(null);
    }

    public void add(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, age, telephoneNumber) VALUES (?, ?, ?)",
                person.getName(),
                person.getAge(),
                person.getTelephoneNumber()
        );
    }

    public void edit(int id, Person person){
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, telephoneNumber=? WHERE personId = ?",
                person.getName(),
                person.getAge(),
                person.getTelephoneNumber(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE personId = ?", id);
    }

    public Optional<Person> getPersonByBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE personId = (SELECT personId from Book where bookId = ?)",
                new BeanPropertyRowMapper<>(Person.class), id).stream().findAny();
    }

    public List<Book> getBooksByPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE personId = ?",
                new BeanPropertyRowMapper<>(Book.class), id);
    }
    public Optional<Person> checkUniqueName(Person person) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name = ? and personId != ?",
                new BeanPropertyRowMapper<>(Person.class),person.getName(), person.getPersonId()).stream().findAny();
    }

    public Optional<Person> checkUniqueTelephone(Person person) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE telephoneNumber = ? and personId != ?",
                new BeanPropertyRowMapper<>(Person.class), person.getTelephoneNumber(), person.getPersonId()).stream().findAny();
    }
}
