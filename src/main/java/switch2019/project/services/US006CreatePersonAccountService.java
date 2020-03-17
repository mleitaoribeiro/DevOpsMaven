package switch2019.project.services;


import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.PersonRepository;

public class US006CreatePersonAccountService {

    public boolean createPersonAccount(PersonRepository personRepository,
                                     AccountRepository accountRepository, Email personEmail, Denomination accountDenomination,
                                     Description accountDescription) {
        //Find Person by ID attribute
        Person onePerson = personRepository.findPersonByEmail(personEmail);
        //Get that person ID
        PersonID onePersonID = onePerson.getID();
        //Create an Account for OnePerson
        return accountRepository.createAccount(accountDenomination, accountDescription, onePersonID);
    }
}
