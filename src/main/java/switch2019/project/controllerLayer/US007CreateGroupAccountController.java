package switch2019.project.controllerLayer;

import switch2019.project.applicationLayer.US007CreateGroupAccountService;

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

    public boolean createGroupAccount (String personEmail, String groupDescription, String accountDenomination, String accountDescription ) {

        return service.createGroupAccount(personEmail, groupDescription, accountDenomination, accountDescription);

    }

}
