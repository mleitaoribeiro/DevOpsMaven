package switch2019.project.model;

import java.util.HashSet;
import java.util.Objects;

public class AccountsList {
    private HashSet<Account> accounts;

    /**
     * Construtor for Accounts List
     */
    public AccountsList() {
        accounts = new HashSet<Account>();
    }

    /**
     * Develop @override of equals for Accounts List and @override of hashcode
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
     * Method to get the numbers of Accounts in the Accounts List
     */
    public int numberOfAccountsInTheAccountsList() {
        return this.accounts.size();
    }

    /**
     * method to add one account to the list
     *
     * @param accountDenomination
     * @param accountDescription
     */
    public boolean addAccountToAccountsList(String accountDenomination, String accountDescription) {
        Account oneAccount = new Account(accountDenomination, accountDescription);
        return accounts.add(oneAccount);
    }

    /**
     * method to add several accounts to a list
     * @param many
     */
    /*public boolean addSeveralAccountsToAList (HashSet<Account> many) {
        for (Account account : many) {
            this.addAccountToAccountsList(account);
        }
        return this.accounts.containsAll(many);
    }*/

    /**
     * method to remove one account from a list
     * @param accountToBeRemoved
     */
    /*public boolean removeOneAccountFromAList (Account accountToBeRemoved){
        if (accountToBeRemoved != null)
            return accounts.remove(accountToBeRemoved);
        else
            return false;
    }*/

    /**
     * method to remove several accounts from a list
     * @param accountsToBeRemoved
     */
    /*public boolean removeSeveralAccountsFromAList (HashSet<Account> accountsToBeRemoved) {
        for (Account account : accountsToBeRemoved) {
             this.removeOneAccountFromAList(account);
        }
        return !this.accounts.containsAll(accountsToBeRemoved);
    }*/


    /**
     * method to validate if the account is in the accounts list
     *
     * @param accountToValidate
     */
    public boolean validateIfAccountIsInTheAccountsList(Account accountToValidate) {
        return this.accounts.contains(accountToValidate);
    }
}
