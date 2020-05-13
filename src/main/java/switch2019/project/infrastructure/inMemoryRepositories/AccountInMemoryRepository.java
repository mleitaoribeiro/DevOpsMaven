package switch2019.project.infrastructure.inMemoryRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import switch2019.project.dataModel.entities.AccountJpa;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.infrastructure.jpa.AccountJpaRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.repositories.AccountRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class AccountInMemoryRepository implements AccountRepository {

    @Autowired
    AccountJpaRepository accountJpaRepository;

    //Private instance variables
    private Set<Account> accounts;

    public AccountInMemoryRepository() {
        accounts = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Accounts Repository: " + accounts.toString();
    }

    /**
     * Find account by ID
     *
     * @param accountID
     * @return account
     */

    public Account getByID (ID accountID) {
        for (Account account : accounts) {
            if (account.getID().equals(accountID))
                return account;
        }
        throw new ArgumentNotFoundException("No account found with that ID.");
    }

    /**
     * method to validate if the account is in the accounts Repository
     *
     * @param accountID
     * @return boolean
     */

    public boolean isIDOnRepository(ID accountID) {
        for (Account account : accounts)
            if (account.getID().equals(accountID))
                return true;
        return false;
    }

    /**
     * Method to get the numbers of Accounts in the Repository
     *
     * @return int
     */

    public long repositorySize () {
        return this.accounts.size();
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
            Account accountToAdd = new Account(accountDenomination, accountDescription, ownerID);
            this.accounts.add(accountToAdd);

            AccountJpa accountJpa = new AccountJpa(ownerID.toString(), accountDenomination.toString(),
                    accountDescription.toString(), accountToAdd.getBalance().toString());

            // accountJpaRepository.save(accountJpa);
            return accountToAdd;

        } else throw new ResourceAlreadyExistsException("This account already exists.");
    }

    /**
     * Get list of Accounts By Owner ID - not validated
     *
     * @param ownerID
     * @return
     */

    public Set<Account> returnAccountsByOwnerID(OwnerID ownerID) {
        Set<Account> listOfAccountsByOwnerID = new HashSet<>();
        if (ownerID != null) {
            for (Account account : accounts)
                if (account.getOwnerID().equals(ownerID))
                    listOfAccountsByOwnerID.add(account);
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
        if (accountToBeRemoved != null)
            return accounts.remove(accountToBeRemoved);
        else return false;
    }
}
