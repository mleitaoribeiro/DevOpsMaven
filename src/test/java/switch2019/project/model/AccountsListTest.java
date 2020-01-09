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
        //Arrange
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");
        Account post=new Account("Post","Correios do Amadeu");

        HashSet<Account> expected =new HashSet<>(Arrays.asList(butcher,market,post));

        AccountsList september = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(expected);

        //Assert
        assertEquals(expected,september.getAccountsList());

    }

    @Test
    @DisplayName("Test if several accounts were added to an accounts list - with a null ")

    public void testIfSeveralAccountsWereAddedNull (){
        //Arrange
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");
        Account post=null;

        HashSet<Account> expected =new HashSet<>(Arrays.asList(butcher,market));
        HashSet<Account> added =new HashSet<>(Arrays.asList(butcher,market,post));


        AccountsList september = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(added);

        //Assert
        assertEquals(expected,september.getAccountsList());

    }

    @Test
    @DisplayName("Test if two account lists are the same - true")

    public void testIfTwoAccountListsAreTheSame(){
        //Arrange
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");


        HashSet<Account> firstList =new HashSet<>(Arrays.asList(butcher,market));


        AccountsList september = new AccountsList();
        AccountsList aMonth = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(firstList);
        aMonth.addSeveralAccountsToAList(firstList);

        boolean result=september.equals(aMonth);

        //Assert
        assertEquals(true,result);

    }

    @Test
    @DisplayName("Test if two account lists are the same - false")

    public void testIfTwoAccountListsAreTheSameNo(){
        //Arrange
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

        //Assert
        assertEquals(false,result);

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts list")
    public void testIfOneAccountWasRemoved (){
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");
        Account post=new Account("Post","Correios do Amadeu");

        HashSet<Account> expected =new HashSet<>(Arrays.asList(butcher,market,post));


        AccountsList september = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(expected);
        september.removeOneAccountFromAList(butcher);

        //Assert
        assertEquals(expected,september.getAccountsList());

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts list - not in the list")
    public void testIfOneAccountWasRemovedNotInTheList (){
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");
        Account post=new Account("Post","Correios do Amadeu");

        HashSet<Account> expected =new HashSet<>(Arrays.asList(butcher,market));


        AccountsList september = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(expected);
        september.removeOneAccountFromAList(post);

        //Assert
        assertEquals(expected,september.getAccountsList());

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts list - null")
    public void testIfOneAccountWasRemovedNull (){
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");
        Account post=null;

        HashSet<Account> expected =new HashSet<>(Arrays.asList(butcher,market,post));


        AccountsList september = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(expected);
        september.removeOneAccountFromAList(post);

        //Assert
        assertEquals(expected,september.getAccountsList());

    }


    @Test
    @DisplayName("Test if several accounts were removed from an accounts list - Positive ")

    public void testIfSeveralAccountsWereRemovedPositive (){
        //Arrange
        Account oneAccount = new Account("xpto", "xpto Account");
        Account otherAccount = new Account ("xyz","xyz Account");
        Account anotherAccount = new Account("abc","abc Account");

        HashSet<Account> accountsToBeAdded = new HashSet<>(Arrays.asList(oneAccount,otherAccount, anotherAccount));
        HashSet<Account> accountsToBeRemoved = new HashSet<>(Arrays.asList(oneAccount,otherAccount));
        HashSet<Account> expected = new HashSet<>(Collections.singletonList(anotherAccount));

        AccountsList oneAccountsList = new AccountsList();

        //Act

        oneAccountsList.addSeveralAccountsToAList(accountsToBeAdded);
        oneAccountsList.removeSeveralAccountsFromAList(accountsToBeRemoved);

        HashSet <Account> real = oneAccountsList.getAccountsList();

        //Arrange
        assertEquals(expected,real);

    }

    @Test
    @DisplayName("Test if several accounts were removed from an accounts list | OneAccount removed ")

    public void testIfSeveralAccountsWereRemoved_RemoveOneAccount (){
        //Arrange
        Account oneAccount = new Account("xpto", "xpto Account");
        Account otherAccount = new Account ("xyz","xyz Account");
        Account anotherAccount = new Account("abc","abc Account");

        HashSet<Account> accountsToBeAdded = new HashSet<>(Arrays.asList(oneAccount,otherAccount, anotherAccount));
        HashSet<Account> accountsToBeRemoved = new HashSet<>(Collections.singletonList(oneAccount));
        HashSet<Account> expected = new HashSet<>(Arrays.asList(otherAccount, anotherAccount));

        AccountsList oneAccountsList = new AccountsList();

        //Act

        oneAccountsList.addSeveralAccountsToAList(accountsToBeAdded);
        oneAccountsList.removeSeveralAccountsFromAList(accountsToBeRemoved);

        HashSet <Account> real = oneAccountsList.getAccountsList();

        //Arrange
        assertEquals(expected,real);
    }

    @Test
    @DisplayName("Test if several accounts were removed from an accounts list - Account dont Exists On The List ")

    public void testIfSeveralAccountsWereRemoved_AccountDoesntExistsOnTheList (){
        //Arrange
        Account oneAccount = new Account("xpto", "xpto Account");
        Account otherAccount = new Account ("xyz","xyz Account");
        Account anotherAccount = new Account("abc","abc Account");

        HashSet<Account> accountsToBeAdded = new HashSet<>(Arrays.asList(oneAccount,otherAccount));
        HashSet<Account> accountsToBeRemoved = new HashSet<>(Collections.singletonList(anotherAccount));
        HashSet<Account> expected = new HashSet<>(Arrays.asList(oneAccount,otherAccount));

        AccountsList oneAccountsList = new AccountsList();

        //Act

        oneAccountsList.addSeveralAccountsToAList(accountsToBeAdded);
        oneAccountsList.removeSeveralAccountsFromAList(accountsToBeRemoved);

        HashSet <Account> real = oneAccountsList.getAccountsList();

        //Arrange
        assertEquals(expected,real);

    }



}