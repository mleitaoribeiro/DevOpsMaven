package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupAccountDTO;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.AccountRepository;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;


@Service
public class US007CreateGroupAccountService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private GroupRepository groupsRepository;
    @Autowired
    private AccountRepository accountRepository;

    /**
     *US007 - As a group Admin, I want to create a group account
     *
     * @param createGroupAccountDTO
     * @param
     * @return accountDTO
     */


    public AccountDTO createGroupAccount (CreateGroupAccountDTO createGroupAccountDTO) {

        PersonID personID = personRepository.findPersonByEmail(new Email(createGroupAccountDTO.getPersonEmail())).getID();

        Denomination accountDenomination = new Denomination(createGroupAccountDTO.getAccountDenomination());
        Description accountDescription = new Description(createGroupAccountDTO.getAccountDescription());

        Group group = groupsRepository.findGroupByDescription(new Description(createGroupAccountDTO.getGroupDescription()));

        GroupID groupID = group.getID();

        if (group.isGroupMember(personID)) {
            if (group.isGroupAdmin(personID)) {
                Account account = accountRepository.createAccount(accountDenomination, accountDescription, groupID);
                return AccountDTOAssembler.createAccountDTOFromDomainObject(account);
            } else throw new IllegalArgumentException("This person is not admin of this group.");
        } else throw new IllegalArgumentException("This person is not member of this group.");


    }


}
