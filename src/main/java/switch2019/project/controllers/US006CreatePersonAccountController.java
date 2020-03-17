package switch2019.project.controllers;

import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.PersonID;
import switch2019.project.services.US006CreatePersonAccountService;


public class US006CreatePersonAccountController {

    private US006CreatePersonAccountService service;

    public US006CreatePersonAccountController (US006CreatePersonAccountService service) {
        this.service = service;
    }

    /**
     * US006
     * Create Person Account
     *
     * @param onPersonID
     * @param accountDenomination
     * @param accountDescription
     * @return
     */

    public boolean createPersonAccount(PersonID onPersonID, Denomination accountDenomination,
                                       Description accountDescription) {

        return service.createPersonAccount(onPersonID, accountDenomination, accountDescription);

    }
}

