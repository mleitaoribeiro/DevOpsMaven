package switch2019.project.services;

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
     * @param onePersonID
     * @param accountDenomination
     * @param accountDescription
     * @return
     */

    public boolean createPersonAccount(PersonID onePersonID, Denomination accountDenomination, Description accountDescription) {
            if (personRepository.isPersonIDOnRepository(onePersonID))
                return accountRepository.createAccount(accountDenomination, accountDescription, onePersonID);
            else throw new IllegalArgumentException("This Person ID doesn't exist or it's null.");
    }
}
