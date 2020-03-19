package switch2019.project.services;


import switch2019.project.model.group.Group;
import switch2019.project.model.person.Email;
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
     * @param personEmail
     * @param groupDescription
     * @param accountDenomination
     * @param accountDescription
     * @return
     */

    public boolean createGroupAccount (String personEmail, String groupDescription ,
                                       String accountDenomination, String accountDescription ) {

        PersonID personID = personRepository.findPersonByEmail(new Email (personEmail)).getID();

        Denomination oneAccountDenomination = new Denomination(accountDenomination);
        Description oneAccountDescription = new Description(accountDescription);

        Group group = groupsRepository.findGroupByDescription( new Description( groupDescription));
        GroupID groupID = group.getID();

        boolean personIsGroupAdmin = group.isGroupAdmin(personID);

        if (personIsGroupAdmin) {
            return accountRepository.createAccount(oneAccountDenomination, oneAccountDescription, groupID);
        }
        return false;
    }

}
