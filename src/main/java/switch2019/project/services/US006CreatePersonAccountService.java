package switch2019.project.services;

import switch2019.project.model.person.Email;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.PersonRepository;

public class US006CreatePersonAccountService {

    private PersonRepository personRepository;
    private AccountRepository accountRepository;

    public US006CreatePersonAccountService(PersonRepository personRepository, AccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Create Person Account
     * @param
     * @param accountDenomination
     * @param accountDescription
     * @return
     */

    public boolean createPersonAccount(String personEmail, String accountDenomination, String accountDescription) {
            if (personRepository.isPersonIDOnRepository(new PersonID(new Email(personEmail))))
                return accountRepository.createAccount(new Denomination(accountDenomination), new Description(accountDescription), new PersonID( new Email(personEmail)));
            else throw new IllegalArgumentException("This Person ID doesn't exist.");
    }
}
