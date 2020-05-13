package switch2019.project.infrastructure.dataBaseRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.dataAssemblers.AccountDomainDataAssembler;
import switch2019.project.dataModel.entities.AccountJpa;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.infrastructure.jpa.AccountJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.repositories.AccountRepository;

import java.util.HashSet;
import java.util.Set;

@Component("AccountDbRepository")
public class AccountDbRepository implements AccountRepository {

    @Autowired
    AccountJpaRepository accountJpaRepository;

    @Override
    public String toString() {
        return "Accounts Repository: " + accountJpaRepository.toString();
    }

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
        if (!isIDOnRepository(new AccountID(accountDenomination, ownerID))) {
            Account account = new Account(accountDenomination, accountDescription, ownerID);
            accountJpaRepository.save(AccountDomainDataAssembler.toData(account));
            return account;

        } else throw new ResourceAlreadyExistsException("This account already exists.");
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
            else throw new ArgumentNotFoundException("No accounts found with that ID.");
        } throw new IllegalArgumentException("Owner ID can't be null.");
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
        } else throw new ArgumentNotFoundException("No accounts found with that ID.");
    }

    // TODO: to update later
    @Override
    public Account getByID(ID accountID) {
        return null;
    }

    @Override
    public boolean isIDOnRepository(ID id) {
        return false;
    }
}
