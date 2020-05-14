package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupAccountDTO;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.AccountRepository;
import switch2019.project.domain.repositories.GroupRepository;
import switch2019.project.domain.repositories.PersonRepository;
import switch2019.project.utils.customExceptions.NoPermissionException;

import java.util.LinkedHashSet;
import java.util.Set;


@Service
public class US007CreateGroupAccountService {

    @Autowired
    @Qualifier("PersonInMemoryRepository")
    private PersonRepository personRepository;

    @Autowired
    @Qualifier("GroupInMemoryRepository")
    private GroupRepository groupsRepository;

    @Autowired
    @Qualifier("AccountInMemoryRepository")
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

        if  (!group.isGroupMember(personID)) {
            throw new NoPermissionException("This person is not member of this group.");
        }
        else if   (!group.isGroupAdmin(personID))
            throw new NoPermissionException("This person is not admin of this group.");
        else {
            Account account = accountRepository.createAccount(accountDenomination, accountDescription, groupID);
            return AccountDTOAssembler.createAccountDTOFromDomainObject(account);
        }
    }

    /**
     * method that finds an account by account ID
     *
     * @param groupDescription
     * @param accountDenomination
     * @return AccountDTO representing an Account
     */

    public AccountDTO getAccountByAccountID (String accountDenomination, String groupDescription) {

        //Find ownerID that created the Account
        OwnerID ownerID = groupsRepository.findGroupByDescription(new Description(groupDescription)).getID();

        //Transform in a category ID
        AccountID accountID = new AccountID(new Denomination(accountDenomination), ownerID);

        //Find the account by ID
        Account account = accountRepository.getByID(accountID);

        //Return DTO that represents category
        return AccountDTOAssembler.createAccountDTOFromDomainObject(account);
    }

    /**
     * method that finds all accounts by group ID
     *
     * @param groupDescription
     * @return all accounts from a specific group
     */

    public Set<AccountDTO> getAccountsByGroupID(String groupDescription) {

        //Find the ownerID (groupID):
        OwnerID groupID = groupsRepository.findGroupByDescription(new Description(groupDescription)).getID();

        //Getting all accounts associated with a groupID:
        Set<Account> accounts = accountRepository.returnAccountsByOwnerID(groupID);

        //Create a new set to receive all the AccountDTOs associated with the groupID:
        Set<AccountDTO> accountDTOSet = new LinkedHashSet<>();

        //Creating an accountDTO in the new set for each entry in the accounts set:
        for (Account i : accounts)
            accountDTOSet.add(AccountDTOAssembler.createAccountDTOFromDomainObject(i));

        //returning the ser with the information of the accounts of a group:
        return accountDTOSet;
    }
}
