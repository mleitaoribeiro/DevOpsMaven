package switch2019.project.model;

import java.util.HashSet;
import java.util.Objects;

public class AccountsList {
    private HashSet<Account> accounts;

    public AccountsList() {
        accounts = new HashSet<Account>();

    }

    /**
     *Develop @override of equals for Accounts List and @override of hashcode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountsList accountsList = (AccountsList) o;
        return Objects.equals(this.accounts, accountsList.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accounts);
    }


    /**
     * method to get Accounts inside an AccountsList
     * @return AccountsList
     */

    public HashSet<Account> getAccountsList() {
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
    public void addSeveralAccountsToAList (HashSet<Account> many) {
        for (Account account : many) {
            addAccountToAccountsList(account);
        }
    }

    /**
     * method to remove several accounts from a list
     */
    public void removeSeveralAccountsFromAList (HashSet<Account> AccountsToBeRemoved) {

    }


}
