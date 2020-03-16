package switch2019.project.controllers;

import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.CreateGroupAccountService;

public class US007CreateGroupAccountController {

    public boolean createGroupAccount (PersonRepository personRepository, GroupsRepository groupsRepository,
                                       AccountRepository accountRepository, CreateGroupAccountService createGroupAccountService,
                                       String personName, String groupDescription, Denomination accountDenomination, Description accountDescription ) {

        return createGroupAccountService.createGroupAccount(personRepository, groupsRepository, accountRepository,
                personName, groupDescription, accountDenomination, accountDescription);
    }
}
