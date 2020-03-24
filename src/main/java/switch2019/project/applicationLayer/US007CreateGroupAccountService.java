package switch2019.project.applicationLayer;


import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.infrastructure.repositories.AccountRepository;
import switch2019.project.infrastructure.repositories.GroupsRepository;
import switch2019.project.infrastructure.repositories.PersonRepository;

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
            //return accountRepository.createAccount(oneAccountDenomination, oneAccountDescription, groupID);
        }
        return false;
    }

}
