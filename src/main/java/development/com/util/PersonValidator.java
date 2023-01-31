package development.com.util;


import development.com.dao.PersonDAO;
import development.com.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(personDAO.checkUniqueName(person).isPresent())
            errors.rejectValue("name", "", "A similar name has already been entered");
        if(personDAO.checkUniqueTelephone(person).isPresent())
            errors.rejectValue("telephoneNumber", "","A similar telephone has already been entered");
    }
}
