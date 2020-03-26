package switch2019.project.applicationLayer;

import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreateGroupAccountDTO;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.domain.domainEntities.account.Account;
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
     * @param createGroupAccountDTO
     * @param
     * @return
     */

    public AccountDTO createGroupAccount (CreateGroupAccountDTO createGroupAccountDTO) {

        PersonID personID = personRepository.findPersonByEmail(new Email(createGroupAccountDTO.getPersonEmail())).getID();

        Denomination accountDenomination = new Denomination(createGroupAccountDTO.getAccountDenomination());
        Description accountDescription = new Description(createGroupAccountDTO.getAccountDescription());

        Group group = groupsRepository.findGroupByDescription(new Description(createGroupAccountDTO.getGroupDescription()));

        GroupID groupID = group.getID();

        if (group.isGroupAdmin(personID)) {
            Account account = accountRepository.createAccount(accountDenomination, accountDescription, groupID);
            return AccountDTOAssembler.createAccountDTO(account.getOwnerID(), account.getDenomination(), account.getDescription());
        }
        else throw new IllegalArgumentException("This person is not Admin of this group");
    }
}
