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
     * method to add one account to the list
     */
    public boolean addAccountToAccountsList (Account oneAccount) {
        if (oneAccount!=null)
            return accounts.add(oneAccount);
        return false;
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
     * method to remove one account from a list
     */
    public void removeOneAccountFromAList (Account accountToBeRemoved){
        if (accountToBeRemoved != null)
        accounts.remove(accountToBeRemoved);
    }

    /**
     * method to remove several accounts from a list
     */
    public void removeSeveralAccountsFromAList (HashSet<Account> accountsToBeRemoved) {
        for (Account account : accountsToBeRemoved)
            removeOneAccountFromAList(account);
    }


}
