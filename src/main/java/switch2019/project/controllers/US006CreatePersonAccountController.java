package switch2019.project.controllers;

import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.services.createPersonAccountService;

public class US006CreatePersonAccountController {

    /**
     * Create Person Account
     * @param service
     * @param personName
     * @param accountDenomination
     * @param accountDescription
     * @return
     */

    public boolean createPersonAccount(createPersonAccountService service, String personName, Denomination accountDenomination, Description accountDescription) {
        return service.createPersonAccount(personName, accountDenomination, accountDescription);
    }
}
