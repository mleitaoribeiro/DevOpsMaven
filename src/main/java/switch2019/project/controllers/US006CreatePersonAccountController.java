package switch2019.project.controllers;

import switch2019.project.model.person.Email;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US006CreatePersonAccountService;


public class US006CreatePersonAccountController {

    /**
     * US006
     * Create Person Account
     *
     * @param service
     * @param personEmail
     * @param accountDenomination
     * @param accountDescription
     * @return
     */

    public boolean createPersonAccount(US006CreatePersonAccountService service, PersonRepository personRepository,
                                       AccountRepository accountRepository, Email personEmail, Denomination accountDenomination,
                                       Description accountDescription) {

        return service.createPersonAccount(personRepository, accountRepository, personEmail, accountDenomination, accountDescription);

    }
}

