package development.com.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int bookId;
    private int personId;
    @NotEmpty(message = "Title shouldn't be empty")
    @Size(min = 3, max = 40, message = "Title should be between 3 and 40 characters")
    private String title;
    @NotEmpty(message = "Author's FIO  shouldn't be empty")
    @Size(min = 8, max = 50, message = "Author's FIO should be between 8 and 50 characters")
    private String author;
    @Min(value = 1800, message = "Wrong year entered (Min 1800)")
    private int year;

    public Book() {
    }

    public Book(int bookId, String title, String author, int year) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
