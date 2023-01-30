package development.com.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {
    private int personId;
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 10, max = 50, message = "Name should be between 10 and 50 characters")
    private String name;
    @Min(value = 14, message = "Too young age (Min 14)")
    private int age;
    @Pattern(regexp = "8-\\d{3}-\\d{3}-\\d{2}-\\d{2}", message = "Telephone should be like \"8-***-***-**-**\"")
    private String telephoneNumber;

    public Person() {
    }

    public Person(int personId, String name, int age, String telephoneNumber) {
        this.personId = personId;
        this.name = name;
        this.age = age;
        this.telephoneNumber = telephoneNumber;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
