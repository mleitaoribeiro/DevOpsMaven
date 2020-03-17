package switch2019.project.repository;

import switch2019.project.model.account.Account;
import switch2019.project.model.frameworks.ID;
import switch2019.project.model.frameworks.OwnerID;
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

    /**
     * Find account by ID
     * @param accountID
     * @return account
     */

    public Account findByID(ID accountID){
        for(Account account: accounts) {
            if(account.getID().equals(accountID))
                return account;
        } throw new IllegalArgumentException("No account found with that ID.");
    }

    /**
     * Get list of Accounts By Owner ID - not validated
     * @param ownerID
     * @return
     */
    public Set<Account> returnAccountsByOwnerID(OwnerID ownerID){
        Set<Account> listOfAccountsByOwnerID = new HashSet<>();
        if(ownerID != null) {
            for (Account account : accounts) {
                if (account.getOwnerID().equals(ownerID)) {
                    listOfAccountsByOwnerID.add(account);
                }
            }
            return listOfAccountsByOwnerID;
        } else throw new IllegalArgumentException("No account found with that ID.");
    }


    /**
     * method to add one account to the repository with an owner
     * @param accountDenomination
     * @param accountDescription
     * @param ownerID
     * @return
     */

    public boolean createAccount(Denomination accountDenomination, Description accountDescription, OwnerID ownerID) {
        Account oneAccount = new Account(accountDenomination, accountDescription, ownerID);
        this.accounts.add(oneAccount);
        return this.accounts.contains(oneAccount);
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
     * method to add one account to the list
     *
     * @param accountDenomination
     * @param accountDescription
     * @return boolean
     */

    public boolean createAccount(Denomination accountDenomination, Description accountDescription) {
        Account oneAccount = new Account(accountDenomination, accountDescription);
        this.accounts.add(oneAccount);
        return this.accounts.contains(oneAccount);
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
     * @param accountToValidate
     * @return boolean
     */

    public boolean validateIfAccountIsInTheAccountsRepository(Account accountToValidate) {
        return this.accounts.contains(accountToValidate);
    }
}
