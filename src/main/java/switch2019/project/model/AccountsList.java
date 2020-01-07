package switch2019.project.model;

import java.util.HashSet;

public class AccountsList {
    private HashSet<Account> accounts;

    public AccountsList() {
        accounts = new HashSet<Account>();

    }

    /**
     * method to get Accounts inside an AccountsList
     * @return AccountsList
     */

    public HashSet<Account> getAccountsList()
    {
        return new HashSet<>(this.accounts);
    }

    /**
     * Method to check the number of Accounts inside the list.
     */
    public int howManyAccounts(){
        return this.accounts.size();
    }

    /**
     * method to add one account to the list
     */
    public void addAccountToAccountsList (Account oneAccount) {
    }

}
