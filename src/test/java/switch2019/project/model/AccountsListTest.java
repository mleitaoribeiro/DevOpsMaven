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
    @DisplayName("Test if more than one account was added to the list")
    public void testIfAccountsAreInList_OneAccountIsNull() {
        //Arrange
        Account oneAccount = null;
        Account otherAccount = new Account ("xyz", "general");
        Account anotherAccount = new Account ("Millennium", "Millennium Account");
        HashSet<Account> expected = new HashSet<>(Arrays.asList(otherAccount, anotherAccount));
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
    public void testIfAccountsListContainAccount_true() {
        //Arrange
        Account oneAccount = new Account("xpto", "cat acccount");
        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList(oneAccount);

        boolean expected = accountsList.accountsListContains(oneAccount);

        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Test if one account is contained in the accounts list | False")
    public void testIfAccountsListContainAccount_false() {
        //Arrange
        Account oneAccount = new Account("xpto", "cat acccount");
        AccountsList accountsList = new AccountsList();

        //Act

        boolean notContained = accountsList.accountsListContains(oneAccount);

        //Assert
        assertFalse(notContained);
    }

    @Test
    @DisplayName("Test if several accounts were added to an accounts list - Positive ")

    public void testIfSeveralAccountsWereAddedPositive (){
        //Act
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");
        Account post=new Account("Post","Correios do Amadeu");

        HashSet<Account> expected =new HashSet<>(Arrays.asList(butcher,market,post));

        AccountsList september = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(expected);

        //Arrange
        assertEquals(expected,september.getAccountsList());

    }

    @Test
    @DisplayName("Test if several accounts were added to an accounts list - with a null ")

    public void testIfSeveralAccountsWereAddedNull (){
        //Act
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");
        Account post=null;

        HashSet<Account> expected =new HashSet<>(Arrays.asList(butcher,market));
        HashSet<Account> added =new HashSet<>(Arrays.asList(butcher,market,post));


        AccountsList september = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(added);

        //Arrange
        assertEquals(expected,september.getAccountsList());

    }

    @Test
    @DisplayName("Test if two account lists are the same - true")

    public void testIfTwoAccountListsAreTheSame(){
        //Act
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");


        HashSet<Account> firstList =new HashSet<>(Arrays.asList(butcher,market));


        AccountsList september = new AccountsList();
        AccountsList aMonth = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(firstList);
        aMonth.addSeveralAccountsToAList(firstList);

        boolean result=september.equals(aMonth);

        //Arrange
        assertEquals(true,result);

    }

    @Test
    @DisplayName("Test if two account lists are the same - false")

    public void testIfTwoAccountListsAreTheSameNo(){
        //Act
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");


        HashSet<Account> firstList =new HashSet<>(Arrays.asList(butcher,market));
        HashSet<Account> secondList =new HashSet<>(Arrays.asList(butcher));


        AccountsList september = new AccountsList();
        AccountsList aMonth = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(firstList);
        aMonth.addSeveralAccountsToAList(secondList);

        boolean result=september.equals(aMonth);

        //Arrange
        assertEquals(false,result);

    }


}