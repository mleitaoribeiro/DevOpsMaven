package switch2019.project.controllers;

import switch2019.project.model.person.Email;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
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

    public boolean createPersonAccount(US006CreatePersonAccountService service, Email personEmail, Denomination accountDenomination, Description accountDescription) {
        return service.createPersonAccount(personEmail, accountDenomination, accountDescription);
    }
}
