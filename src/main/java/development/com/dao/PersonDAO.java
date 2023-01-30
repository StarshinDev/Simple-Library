package development.com.dao;

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
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
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

    public Optional<Person> checkUniqueName(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name = ?",
                new BeanPropertyRowMapper<>(Person.class),name).stream().findAny();
    }

    public Optional<Person> checkUniqueTelephone(String telephone) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE telephoneNumber = ?",
                new BeanPropertyRowMapper<>(Person.class), telephone).stream().findAny();
    }
}
