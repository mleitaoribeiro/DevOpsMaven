package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonAccountDTO;
import switch2019.project.assemblers.AccountDTOAssembler;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.AccountID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.domain.repositories.AccountRepository;
import switch2019.project.domain.repositories.PersonRepository;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class US006CreatePersonAccountService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AccountRepository accountRepository;

    public AccountDTO createPersonAccount(CreatePersonAccountDTO createPersonAccountDTO) {

        PersonID personID = personRepository.findPersonByEmail(new Email(createPersonAccountDTO.getPersonEmail())).getID();

        Denomination accountDenomination = new Denomination(createPersonAccountDTO.getAccountDenomination());
        Description accountDescription = new Description(createPersonAccountDTO.getAccountDescription());

        Account account = accountRepository.createAccount(accountDenomination, accountDescription, personID);

        return AccountDTOAssembler.createAccountDTOFromDomainObject(account);

    }

    public AccountDTO getAccountByAccountID(String accountDenomination, String personEmail) {

        //Find ownerID that created the Account
        OwnerID ownerID = personRepository.findPersonByEmail(new Email(personEmail)).getID();

        //Transform in a category ID
        AccountID accountID = new AccountID(new Denomination(accountDenomination), ownerID);

        //Find the account by ID
        Account account = accountRepository.getByID(accountID);

        //Return DTO that represents category
        return AccountDTOAssembler.createAccountDTOFromDomainObject(account);
    }

    public Set<AccountDTO> getAccountsByPersonID(String personEmail) {

        OwnerID ownerID = personRepository.findPersonByEmail(new Email(personEmail)).getID();

        Set <Account> accounts = accountRepository.returnAccountsByOwnerID(ownerID);

        Set<AccountDTO> accountsDTO = new LinkedHashSet<>();

        for (Account account : accounts)
            accountsDTO.add(AccountDTOAssembler.createAccountDTOFromDomainObject(account));

        return accountsDTO;
    }
}
