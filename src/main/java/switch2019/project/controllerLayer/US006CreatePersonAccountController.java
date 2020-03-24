package switch2019.project.controllerLayer;

import switch2019.project.applicationLayer.US006CreatePersonAccountService;


public class US006CreatePersonAccountController {

    private US006CreatePersonAccountService service;

    public US006CreatePersonAccountController (US006CreatePersonAccountService service) {
        this.service = service;
    }

    /**
     * US006
     * Create Person Account
     *
     * @param personEmail
     * @param accountDenomination
     * @param accountDescription
     * @return
     */

    public boolean createPersonAccount(String personEmail, String accountDenomination,
                                       String accountDescription) {
        return service.createPersonAccount(personEmail, accountDenomination, accountDescription);
    }
}

