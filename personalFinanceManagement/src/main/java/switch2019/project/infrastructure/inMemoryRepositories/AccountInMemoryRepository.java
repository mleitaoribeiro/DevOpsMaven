package switch2019.project.infrastructure.inMemoryRepositories;

import org.springframework.stereotype.Component;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.AccountID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.repositories.AccountRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.HashSet;
import java.util.Set;

@Component
public class AccountInMemoryRepository implements AccountRepository {

    //Private instance variables
    private final Set<Account> accounts;

    public AccountInMemoryRepository() {
        accounts = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Accounts Repository: " + accounts.toString();
    }

    public Account getByID (ID accountID) {
        for (Account account : accounts) {
            if (account.getID().equals(accountID))
                return account;
        } throw new ArgumentNotFoundException("No account found with that ID.");
    }

    public boolean isIDOnRepository(ID accountID) {
        for (Account account : accounts)
            if (account.getID().equals(accountID))
                return true;
        return false;
    }

    public long repositorySize () {
        return this.accounts.size();
    }

    public Account createAccount(Denomination accountDenomination, Description accountDescription, OwnerID ownerID) {
        if (!isIDOnRepository(new AccountID(accountDenomination, ownerID))) {
            Account accountToAdd = new Account(accountDenomination, accountDescription, ownerID);
            this.accounts.add(accountToAdd);
            return accountToAdd;
        } else throw new ResourceAlreadyExistsException("This account already exists.");
    }

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

    public boolean removeAccount(Account accountToBeRemoved) {
        if (accountToBeRemoved != null)
            return accounts.remove(accountToBeRemoved);
        else return false;
    }
}
