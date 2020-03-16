package switch2019.project.controllers;

import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.services.CreateGroupAccountService;

public class US007CreateGroupAccountController {

    public boolean createGroupAccount (CreateGroupAccountService createGroupAccountService, String personName, String groupDescription, Denomination accountDenomination, Description accountDescription ) {
        return createGroupAccountService.createGroupAccount(personName, groupDescription, accountDenomination, accountDescription);
    }
}
