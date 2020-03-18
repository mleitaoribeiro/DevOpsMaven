package switch2019.project.services;


import switch2019.project.model.group.Group;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.GroupID;
import switch2019.project.model.shared.PersonID;
import switch2019.project.repository.AccountRepository;
import switch2019.project.repository.GroupsRepository;
import switch2019.project.repository.PersonRepository;

public class US007CreateGroupAccountService {

    private PersonRepository personRepository;
    private GroupsRepository groupsRepository;
    private AccountRepository accountRepository;

    public US007CreateGroupAccountService (PersonRepository personRepository, GroupsRepository groupsRepository, AccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.groupsRepository = groupsRepository;
        this.accountRepository = accountRepository;
    }


    /**
     *US007 - As a group Admin, I want to create a group account
     *
     * @param onePersonID
     * @param oneGroupID
     * @param accountDenomination
     * @param accountDescription
     * @return
     */
    public boolean createGroupAccount (PersonID onePersonID, GroupID oneGroupID,
                                       Denomination accountDenomination, Description accountDescription ) {

            Group oneGroup = groupsRepository.findGroupByID(oneGroupID);

            boolean personIsGroupAdmin = oneGroup.isGroupAdmin(onePersonID);

            if (personIsGroupAdmin) {
                return accountRepository.createAccount(accountDenomination, accountDescription, oneGroupID);
            }
            return false;

    }

}
