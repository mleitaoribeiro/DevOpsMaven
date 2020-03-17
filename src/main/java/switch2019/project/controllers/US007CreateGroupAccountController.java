package switch2019.project.controllers;

import switch2019.project.model.person.Email;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.services.US007CreateGroupAccountService;

public class US007CreateGroupAccountController {


    private US007CreateGroupAccountService service;


    public US007CreateGroupAccountController (US007CreateGroupAccountService service) {
        this.service = service;
    }

    /**
     * US007 - As a group Admin, I want to create a group account
     *
     * @param personEmail
     * @param groupDescription
     * @param accountDenomination
     * @param accountDescription
     * @return
     */

    public boolean createGroupAccount (Email personEmail, Description groupDescription, Denomination accountDenomination, Description accountDescription ) {

        return service.createGroupAccount(personEmail, groupDescription, accountDenomination, accountDescription);

    }

}
