package switch2019.project.repository;

import switch2019.project.model.account.Account;
import switch2019.project.model.frameworks.ID;
import switch2019.project.model.frameworks.Owner;
import switch2019.project.model.frameworks.OwnerID;
import switch2019.project.model.shared.AccountID;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;

import java.util.HashSet;
import java.util.Set;

public class AccountRepository implements Repository {
    //Private instance variables
    private Set<Account> accounts;

    public AccountRepository() {
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

    public Account findByID(ID accountID) {
        for (Account account : accounts) {
            if (account.getID().equals(accountID))
                return account;
        }
        throw new IllegalArgumentException("No account found with that ID.");
    }

    /**
     * method to add one account to the repository with an Owner
     *
     * @param accountDenomination
     * @param accountDescription
     * @param ownerID
     * @return
     */

    public boolean createAccount(Denomination accountDenomination, Description accountDescription, OwnerID ownerID) {
        Account oneAccount = new Account(accountDenomination, accountDescription, ownerID);
        if (!isAccountIDOnRepository(new AccountID(accountDenomination, ownerID)))
            return this.accounts.add(oneAccount);
        else throw new IllegalArgumentException("This Account already exists for that ID.");
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
            else throw new IllegalArgumentException("Any Account found with that ID.");
        }
        throw new IllegalArgumentException("Owner ID can't be null.");
    }

    /**
     * Method to get the numbers of Accounts in the Repository
     *
     * @return int
     */

    public int numberOfAccountsInTheAccountsRepository() {
        return this.accounts.size();
    }


    /**
     * method to remove one account from the Repository
     *
     * @param accountToBeRemoved
     * @return boolean
     */

    public boolean removeOneAccountFromRepository(Account accountToBeRemoved) {
        if (accountToBeRemoved != null) {
            return accounts.remove(accountToBeRemoved);
        } else
            return false;
    }

    /**
     * method to validate if the account is in the accounts Repository
     *
     * @param accountID
     * @return boolean
     */

    public boolean isAccountIDOnRepository(AccountID accountID) {
        for (Account accounts : accounts)
            if (accounts.getID().equals(accountID))
                return true;
        return false;
    }
}
