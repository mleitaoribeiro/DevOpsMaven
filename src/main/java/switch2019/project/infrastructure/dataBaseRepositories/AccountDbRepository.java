package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.dataAssemblers.AccountDomainDataAssembler;
import switch2019.project.dataModel.entities.AccountIDJpa;
import switch2019.project.dataModel.entities.AccountJpa;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.AccountID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.repositories.AccountRepository;
import switch2019.project.infrastructure.jpa.AccountJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component("AccountDbRepository")
public class AccountDbRepository implements AccountRepository {

    @Autowired
    AccountJpaRepository accountJpaRepository;

    // String literals - Exceptions
    private static final String NO_ACCOUNT_FOUND = "No account found with that ID.";
    private static final String ACCOUNT_ALREADY_EXISTS = "This account already exists.";
    private static final String NULL_OWNER = "Owner ID can't be null.";

    /**
     * Method to get the numbers of Accounts in the Repository
     *
     * @return int
     */

    public long repositorySize() {
        return accountJpaRepository.count();
    }

    /**
     * method to add one account to the repository with an Owner
     *
     * @param accountDenomination
     * @param accountDescription
     * @param ownerID
     * @return
     */

    public Account createAccount(Denomination accountDenomination, Description accountDescription, OwnerID ownerID) {
        if (isIDOnRepository(new AccountID(accountDenomination, ownerID)))
            throw new ResourceAlreadyExistsException(ACCOUNT_ALREADY_EXISTS);
        else  {
            Account account = new Account(accountDenomination, accountDescription, ownerID);
            accountJpaRepository.save(AccountDomainDataAssembler.toData(account));
            return account;
        }
    }

    /**
     * Get list of Accounts By Owner ID - not validated
     *
     * @param ownerID
     * @return
     */


    public Set<Account> returnAccountsByOwnerID(OwnerID ownerID) {
        if (ownerID != null) {
            Set<Account> listOfAccountsByOwnerID = new HashSet<>();
            for (AccountJpa accountjpa : accountJpaRepository.findAllByAccountIDJpa_Owner(ownerID.toString()))
                listOfAccountsByOwnerID.add(AccountDomainDataAssembler.toDomain(accountjpa));
            if (!listOfAccountsByOwnerID.isEmpty())
                return listOfAccountsByOwnerID;
            else throw new ArgumentNotFoundException(NO_ACCOUNT_FOUND);
        } throw new IllegalArgumentException(NULL_OWNER);
    }

    /**
     * method to remove one account from the Repository
     *
     * @param accountToBeRemoved
     * @return boolean
     */

    public boolean removeAccount(Account accountToBeRemoved) {
        if (this.isIDOnRepository(accountToBeRemoved.getID())) {
            accountJpaRepository.delete(AccountDomainDataAssembler.toData(accountToBeRemoved));
            return true;
        } else throw new ArgumentNotFoundException(NO_ACCOUNT_FOUND);
    }

    @Override
    public Account getByID(ID accountID) {
        String[] split = accountID.toString().replace(", ", ",").split(",");
        Optional<AccountJpa> accountJpa = accountJpaRepository.
                findByAccountIDJpa(new AccountIDJpa(split[1], split[0]));
        if (accountJpa.isPresent())
            return AccountDomainDataAssembler.toDomain(accountJpa.get());
        else throw new ArgumentNotFoundException(NO_ACCOUNT_FOUND);
    }

    @Override
    public boolean isIDOnRepository(ID accountID) {
        String[] split = accountID.toString().replace(", ", ",").split(",");
        Optional<AccountJpa> accountJpa = accountJpaRepository.
                findByAccountIDJpa(new AccountIDJpa(split[1], split[0]));
        return accountJpa.isPresent();
    }
}
