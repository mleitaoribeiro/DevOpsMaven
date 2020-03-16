package switch2019.project.services;


import switch2019.project.model.person.Person;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.PersonRepository;

public class createPersonAccountService {

    public boolean createPersonAccount(String personName,
                                       Denomination accountDenomination, Description accountDescription) {
        // Initialize PersonRepository
        PersonRepository personRepository = new PersonRepository();
        //Find Person by main attributes
        Person onePerson = personRepository.findPersonByAttributes(personName);
        //Get that person ID
        PersonID onePersonID= onePerson.getID();
        //Initialiaze AccountRepository
        AccountRepository accountsRepository = new AccountRepository();
        //Create an Account for OnePerson - Needs an ID
        return accountsRepository.createAndAddAccountToAccountsList(accountDenomination, accountDescription);
    }
}
