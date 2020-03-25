package switch2019.project.applicationLayer;

import switch2019.project.DTO.CreateGroupAccountDTO;
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

import java.util.Optional;

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
     * @param accountDTO
     * @param
     * @return
     */

    public Optional<Account> createGroupAccount (CreateGroupAccountDTO accountDTO) {

        PersonID personID = personRepository.findPersonByEmail(new Email(accountDTO.getPersonEmail())).getID();

        Denomination oneAccountDenomination = new Denomination(accountDTO.getAccountDenomination());
        Description oneAccountDescription = new Description(accountDTO.getAccountDescription());

        Group group = groupsRepository.findGroupByDescription(new Description(accountDTO.getGroupDescription()));
        GroupID groupID = group.getID();

        if (group.isGroupAdmin(personID))
            return Optional.of(accountRepository.createAccount(oneAccountDenomination, oneAccountDescription, groupID));
        else return Optional.empty();
    }
}
