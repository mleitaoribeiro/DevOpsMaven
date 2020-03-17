package switch2019.project.controllers;

import switch2019.project.model.person.Email;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;
import switch2019.project.services.US007CreateGroupAccountService;

public class US007CreateGroupAccountController {

    /**
     * US007
     * As a group Admin, I want to create a group account
     *
     * @param personRepository
     * @param groupsRepository
     * @param accountRepository
     * @param US007CreateGroupAccountService
     * @param personEmail
     * @param groupDescription
     * @param accountDenomination
     * @param accountDescription
     * @return
     */
    public boolean createGroupAccount (PersonRepository personRepository, GroupsRepository groupsRepository,
                                       AccountRepository accountRepository, US007CreateGroupAccountService US007CreateGroupAccountService,
                                       Email personEmail, String groupDescription, Denomination accountDenomination, Description accountDescription ) {

        return US007CreateGroupAccountService.createGroupAccount(personRepository, groupsRepository, accountRepository,
                personEmail, groupDescription, accountDenomination, accountDescription);
    }
}
