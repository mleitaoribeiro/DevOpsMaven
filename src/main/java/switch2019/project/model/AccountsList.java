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
     * Method to check if an Account is inside the Accounts List:
     */
    public boolean accountsListContains (Account oneAccount) {
        return this.accounts.contains(oneAccount);
    }

    /**
     * method to add one account to the list
     */
    public void addAccountToAccountsList (Account oneAccount) {
        if (oneAccount!=null){
            accounts.add(oneAccount);
        }
    }

    /**
     * method to add several accounts to a list
     */
    public void addSeveralAccountsToAList (HashSet<Account> many){

    }

}
