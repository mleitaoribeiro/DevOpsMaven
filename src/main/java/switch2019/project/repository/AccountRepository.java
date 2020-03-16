package switch2019.project.repository;

import switch2019.project.model.account.Account;
import switch2019.project.model.frameworks.ID;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AccountRepository implements Repository {
    private Set<Account> accounts;

    /**
     * Construtor for Accounts List
     */

    public AccountRepository() {
        accounts = new HashSet<>();
    }

    /**
     * Develop @override of equals for Accounts List and @override of hashcode
     *
     * @param o
     * @return boolean
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountRepository accountsList = (AccountRepository) o;
        return Objects.equals(this.accounts, accountsList.accounts);
    }

    /**
     * Develop  @override of hashcode
     *
     * @return
     */

    @Override
    public int hashCode() {
        return Objects.hash(accounts);
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
     * Develop @override of toString()
     *
     * @return string
     */

    @Override
    public String toString() {
        return "Accounts List: " + accounts;
    }

    /**
     * Method to get the numbers of Accounts in the Accounts List
     *
     * @return int
     */

    public int numberOfAccountsInTheAccountsList() {
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
     * method to remove one account from a list
     *
     * @param accountToBeRemoved
     * @return boolean
     */

    public boolean removeOneAccountFromAList(Account accountToBeRemoved) {
        if (accountToBeRemoved != null) {
            return accounts.remove(accountToBeRemoved);
        } else
            return false;
    }

    /**
     * method to validate if the account is in the accounts list
     *
     * @param accountToValidate
     * @return boolean
     */

    public boolean validateIfAccountIsInTheAccountsList(Account accountToValidate) {
        return this.accounts.contains(accountToValidate);
    }
}
