package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AccountsListTest {

    @Test
    @DisplayName("Test if the account was added to the list - the number of accounts on the list was increased")
    public void testIfAccountsListIncreased() {
        //Arrange
        Account oneAccount = new Account ("xpto", "one account");
        Account otherAccount = new Account ("xyz", "other Account");
        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList(oneAccount);
        accountsList.addAccountToAccountsList(otherAccount);
        int result = accountsList.howManyAccounts();

        //Assert
        assertEquals(2,result);
    }


    @Test
    @DisplayName("Test if the account was added to the list - the account added is not in the list")
    public void testAccountIsInList_Not() {
        //Arrange
        Account oneAccount = new Account("xpto", "cat acccount");
        Account otherAccount = new Account ("xyz", "general");

        HashSet <Account> expected = new HashSet<>(Collections.singleton(oneAccount));

        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList(otherAccount);
        HashSet <Account> real = accountsList.getAccountsList();

        //Assert
        assertNotEquals(expected, real);
    }

    @Test
    @DisplayName("Test if more than one account was added to the list")
    public void testIfAccountsAreInList_MoreThanOne() {
        //Arrange
        Account oneAccount = new Account("xpto", "cat acccount");
        Account otherAccount = new Account ("xyz", "general");
        Account anotherAccount = new Account ("Millennium", "Millennium Account");
        HashSet<Account> expected = new HashSet<>(Arrays.asList( oneAccount, otherAccount, anotherAccount));
        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList(oneAccount);
        accountsList.addAccountToAccountsList(otherAccount);
        accountsList.addAccountToAccountsList(anotherAccount);

        HashSet <Account> real = accountsList.getAccountsList();

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Test if one account is contained in the accounts list | True")
    public void testIfAccountsListCountainAccount_true() {
        //Arrange
        Account oneAccount = new Account("xpto", "cat acccount");
        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList(oneAccount);

        boolean expected = accountsList.accountsListContains(oneAccount);

        //Assert
        assertTrue(expected);
    }

}