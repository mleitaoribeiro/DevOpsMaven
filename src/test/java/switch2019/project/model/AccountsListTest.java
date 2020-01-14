package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AccountsListTest {


    /**
     * Test if account was added to the list
     */
    @Test
    @DisplayName("Test if more than one account was added to the list - True")
    public void testIfAccountsWereAddedToTheList_True() {
        //Arrange
        Account oneAccount = new Account ("xpto", "one account");
        Account otherAccount = new Account ("xyz", "general");
        Account anotherAccount = new Account ("Millennium", "Millennium Account");

        AccountsList accountsList = new AccountsList();

        //Act
        boolean real = accountsList.addAccountToAccountsList(oneAccount)
                && accountsList.addAccountToAccountsList(otherAccount)
                && accountsList.addAccountToAccountsList(anotherAccount);

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if more than one account was added to the list")
    public void testIfAccountsWereAddedToTheList_False_oneAccountIsNull() {
        //Arrange
        Account oneAccount = null;
        Account otherAccount = new Account ("xyz", "general");
        Account anotherAccount = new Account ("Millennium", "Millennium Account");

        AccountsList accountsList = new AccountsList();

        //Act
        boolean real = accountsList.addAccountToAccountsList(oneAccount)
                && accountsList.addAccountToAccountsList(otherAccount)
                && accountsList.addAccountToAccountsList(anotherAccount);

        //Assert
        assertFalse(real);
    }

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
        int result = accountsList.numberOfAccountsInTheAccountsList();

        //Assert
        assertEquals(2,result);
    }


    @Test
    @DisplayName("Test if the account was added to the list - only one account added - oneAccount is not contained")
    public void testAccountsAreInList_Not() {
        //Arrange
        Account oneAccount = new Account("xpto", "cat acccount");
        Account otherAccount = new Account ("xyz", "general");

        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList(otherAccount);

        boolean real = !accountsList.validateIfAccountIsInTheAccountsList(oneAccount)
                && accountsList.validateIfAccountIsInTheAccountsList(otherAccount);

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if more than one account was added to the list - all accounts are in the list")
    public void testIfAccountsAreInList_MoreThanOne() {
        //Arrange
        Account oneAccount = new Account("xpto", "cat acccount");
        Account otherAccount = new Account ("xyz", "general");
        Account anotherAccount = new Account ("Millennium", "Millennium Account");

        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList(oneAccount);
        accountsList.addAccountToAccountsList(otherAccount);
        accountsList.addAccountToAccountsList(anotherAccount);

        boolean real = accountsList.validateIfAccountIsInTheAccountsList(oneAccount)
                && accountsList.validateIfAccountIsInTheAccountsList(otherAccount)
                && accountsList.validateIfAccountIsInTheAccountsList(anotherAccount);

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if more than one account was added to the list - one account is not contained - Null")
    public void testIfAccountsAreInList_OneAccountIsNull() {
        //Arrange
        Account oneAccount = null;
        Account otherAccount = new Account ("xyz", "general");
        Account anotherAccount = new Account ("Millennium", "Millennium Account");

        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList(oneAccount);
        accountsList.addAccountToAccountsList(otherAccount);
        accountsList.addAccountToAccountsList(anotherAccount);

        boolean real = accountsList.validateIfAccountIsInTheAccountsList(oneAccount)
                && accountsList.validateIfAccountIsInTheAccountsList(otherAccount)
                && accountsList.validateIfAccountIsInTheAccountsList(anotherAccount);
        //Assert
        assertFalse(real);
    }

    /**
     * Test if Account is cointained in the Accounts List
     */
    @Test
    @DisplayName("Test if one account is contained in the accounts list | True")
    public void testIfAccountsListContainAccount_true() {
        //Arrange
        Account oneAccount = new Account("xpto", "cat acccount");
        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList(oneAccount);

        boolean expected = accountsList.validateIfAccountIsInTheAccountsList(oneAccount);

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

        boolean notContained = accountsList.validateIfAccountIsInTheAccountsList(oneAccount);

        //Assert
        assertFalse(notContained);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Test if several accounts were added to list
     */
    @Test
    @DisplayName("Test if several accounts were added to an accounts list - Positive ")

    public void testIfSeveralAccountsWereAdded_Positive (){
        //Arrange
        Account butcherAccount =new Account("Butcher", "Talho do Amadeu");
        Account marketAccount =new Account ("Market","Mercado do Amadeu");
        Account postAccount =new Account("Post","Correios do Amadeu");

        HashSet<Account> accounts =new HashSet<>(Arrays.asList(butcherAccount,marketAccount,postAccount));
        AccountsList septemberAccount = new AccountsList();

        //Act
        boolean real = septemberAccount.addSeveralAccountsToAList(accounts);

        //Assert
        assertTrue(real);
    }


    @Test
    @DisplayName("Test if several accounts were added to an accounts list - False ")

    public void testIfSeveralAccountsWereAdded_False_oneAccountIsNull (){
        //Arrange
        Account butcherAccount = null;
        Account marketAccount =new Account ("Market","Mercado do Amadeu");
        Account postAccount =new Account("Post","Correios do Amadeu");

        HashSet<Account> accounts =new HashSet<>(Arrays.asList(butcherAccount,marketAccount,postAccount));
        AccountsList septemberAccount = new AccountsList();

        //Act
        boolean real = septemberAccount.addSeveralAccountsToAList(accounts);

        //Assert
        assertFalse(real);
    }


    @Test
    @DisplayName("Test if several accounts were added to an accounts list - Positive ")

    public void testIfSeveralAccountsWereAddedPositive (){
        //Arrange
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");
        Account post=new Account("Post","Correios do Amadeu");

        HashSet<Account> accounts =new HashSet<>(Arrays.asList(butcher,market,post));
        int expected = 3;
        AccountsList september = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(accounts);


        int real = september.numberOfAccountsInTheAccountsList();


        //Assert
        assertEquals(expected,real);

    }

    @Test
    @DisplayName("Test if several accounts were added to an accounts list - with a null ")

    public void testIfSeveralAccountsWereAddedNull (){
        //Arrange
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");
        Account post=null;


        HashSet<Account> added =new HashSet<>(Arrays.asList(butcher,market,post));
        int expected = 2;

        AccountsList september = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(added);
        int real = september.numberOfAccountsInTheAccountsList();

        //Assert
        assertEquals(expected,real);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Test if two account lists are the same
     */
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Test if Account was removed from list
     */
    @Test
    @DisplayName("Test if an account was removed from an accounts list")
    public void testIfOneAccountWasRemoved (){
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");
        Account post=new Account("Post","Correios do Amadeu");

        HashSet<Account> created =new HashSet<>(Arrays.asList(butcher,market,post));

        AccountsList september = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(created);
        september.removeOneAccountFromAList(butcher);

        //Assert
        assertEquals(2,september.numberOfAccountsInTheAccountsList());

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
        assertEquals(2,september.numberOfAccountsInTheAccountsList());

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts list - null")
    public void testIfOneAccountWasRemovedNull (){
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        Account market=new Account ("Market","Mercado do Amadeu");
        Account post=null;

        HashSet<Account> created =new HashSet<>(Arrays.asList(butcher,market,post));

        AccountsList september = new AccountsList();

        //Act
        september.addSeveralAccountsToAList(created);
        september.removeOneAccountFromAList(post);

        //Assert
        assertEquals(2,september.numberOfAccountsInTheAccountsList());

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Test if several accounts were removed from an accounts list - Positive ")

    public void testIfSeveralAccountsWereRemovedPositive (){
        //Arrange
        Account oneAccount = new Account("xpto", "xpto Account");
        Account otherAccount = new Account ("xyz","xyz Account");
        Account anotherAccount = new Account("abc","abc Account");

        HashSet<Account> accountsToBeAdded = new HashSet<>(Arrays.asList(oneAccount,otherAccount, anotherAccount));
        HashSet<Account> accountsToBeRemoved = new HashSet<>(Arrays.asList(oneAccount,otherAccount));
        int expected = 1;

        AccountsList oneAccountsList = new AccountsList();

        //Act

        oneAccountsList.addSeveralAccountsToAList(accountsToBeAdded);
        oneAccountsList.removeSeveralAccountsFromAList(accountsToBeRemoved);

        int real = oneAccountsList.numberOfAccountsInTheAccountsList();

        //Arrange
        assertEquals(expected, real);

    }

    /**
     * Test if several accounts were removed from list
     */
    @Test
    @DisplayName("Test if several accounts were removed from an accounts list | OneAccount removed ")

    public void testIfSeveralAccountsWereRemoved_RemoveOneAccount (){
        //Arrange
        Account oneAccount = new Account("xpto", "xpto Account");
        Account otherAccount = new Account ("xyz","xyz Account");
        Account anotherAccount = new Account("abc","abc Account");

        HashSet<Account> accountsToBeAdded = new HashSet<>(Arrays.asList(oneAccount,otherAccount, anotherAccount));
        HashSet<Account> accountsToBeRemoved = new HashSet<>(Collections.singletonList(oneAccount));
        int expected = 2;

        AccountsList oneAccountsList = new AccountsList();

        //Act

        oneAccountsList.addSeveralAccountsToAList(accountsToBeAdded);
        oneAccountsList.removeSeveralAccountsFromAList(accountsToBeRemoved);

        int real = oneAccountsList.numberOfAccountsInTheAccountsList();

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

        AccountsList oneAccountsList = new AccountsList();

        //Act

        oneAccountsList.addSeveralAccountsToAList(accountsToBeAdded);
        oneAccountsList.removeSeveralAccountsFromAList(accountsToBeRemoved);

        boolean real = oneAccountsList.validateIfAccountIsInTheAccountsList(anotherAccount);

        //Arrange
        assertFalse(real);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Test if account is in the List
     */
    @Test
    @DisplayName("Test if account is in the List-True")
    void validateIfAccountIsInTheAccountsList() {
        //Arrange
        Account oneAccount = new Account("xpto", "xpto Account");
        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList(oneAccount);
        boolean validateIfAccountIsInTheAccountsList = accountsList.validateIfAccountIsInTheAccountsList(oneAccount);

        //Arrange
        assertTrue(validateIfAccountIsInTheAccountsList);
    }
    @Test
    @DisplayName("Test if account is in the List-False")
    void validateIfAccountIsInTheAccountsList_False() {
        //Arrange
        Account oneAccount = new Account("xpto", "xpto Account");
        Account otherAccount = new Account ("xyz","xyz Account");
        AccountsList accountsList= new AccountsList();

        //Act
        accountsList.addAccountToAccountsList(oneAccount);
        boolean validateIfAccountIsInTheAccountsList= accountsList.validateIfAccountIsInTheAccountsList(otherAccount);

        //Arrange
        assertFalse(validateIfAccountIsInTheAccountsList);
    }

}