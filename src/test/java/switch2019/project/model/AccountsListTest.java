package switch2019.project.model;

import org.junit.Assert;
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
        AccountsList accountsList = new AccountsList();

        //Act
        boolean real = accountsList.addAccountToAccountsList("xpto", "one account")
                && accountsList.addAccountToAccountsList("xyz", "general")
                && accountsList.addAccountToAccountsList("Millennium", "Millennium Account");

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if more than one account was added to the list - one Account Denomination Is Null")
    public void testIfAccountsWereAddedToTheList_False_oneAccountDenominationIsNull() {
        //Arrange
        AccountsList accountsList = new AccountsList();

        //Act
        try {
            accountsList.addAccountToAccountsList(null, "XOPT");
            accountsList.addAccountToAccountsList("xyz", "general");
            accountsList.addAccountToAccountsList("Millennium", "Millennium Account");
        }
        //Assert
        catch (IllegalArgumentException denomination) {
            assertEquals("The denomination can´t be null. Please try again.", denomination.getMessage());
        }
    }

    @Test
    @DisplayName("Test if more than one account was added to the list - one Account Denomination Is Null")
    public void testIfAccountsWereAddedToTheList_False_oneAccountDescriptionIsNull() {
        //Arrange
        AccountsList accountsList = new AccountsList();

        //Act
        try {
            accountsList.addAccountToAccountsList("xpto", null);
            accountsList.addAccountToAccountsList("xyz", "general");
            accountsList.addAccountToAccountsList("Millennium", "Millennium Account");
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The description can´t be null. Please try again.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test if the account was added to the list - the number of accounts on the list was increased")
    public void testIfAccountsListIncreased() {
        //Arrange
        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList("xpto", "one account");
        accountsList.addAccountToAccountsList("xyz", "other Account");
        int result = accountsList.numberOfAccountsInTheAccountsList();

        //Assert
        assertEquals(2, result);
    }


    @Test
    @DisplayName("Test if the account was added to the list - only one account added - oneAccount is not contained")
    public void testAccountsAreInList_Not() {
        //Arrange
        Account oneAccount = new Account("xpto", "cat acccount");
        Account otherAccount = new Account("xyz", "general");

        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList("xyz", "general");

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
        Account otherAccount = new Account("xyz", "general");
        Account anotherAccount = new Account("Millennium", "Millennium Account");

        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList("xpto", "cat acccount");
        accountsList.addAccountToAccountsList("xyz", "general");
        accountsList.addAccountToAccountsList("Millennium", "Millennium Account");

        boolean real = accountsList.validateIfAccountIsInTheAccountsList(oneAccount)
                && accountsList.validateIfAccountIsInTheAccountsList(otherAccount)
                && accountsList.validateIfAccountIsInTheAccountsList(anotherAccount);

        //Assert
        assertTrue(real);
    }

    /**
     * Test if Account is contained in the Accounts List
     */
    @Test
    @DisplayName("Test if one account is contained in the accounts list | True")
    public void testIfAccountsListContainAccount_true() {
        //Arrange
        Account oneAccount = new Account("xpto", "cat acccount");
        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList("xpto", "cat acccount");

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

    /**
     * Test if several accounts were added to list
     */
    /*@Test
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

    }*/

    /**
     * Test if two account lists are the same
     */
    @Test
    @DisplayName("Test if two account lists are the same - true")

    public void testIfTwoAccountListsAreTheSame() {
        //Arrange

        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";


        AccountsList september = new AccountsList();
        AccountsList aMonth = new AccountsList();

        //Act
        september.addAccountToAccountsList(butcherDenomination, butcherDescription);
        aMonth.addAccountToAccountsList(butcherDenomination, butcherDescription);

        boolean result = september.equals(aMonth);

        //Assert
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test if two account lists are the same - false")

    public void testIfTwoAccountListsAreTheSameNo() {
        //Arrange
        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";

        String marketDenomination = "Market";
        String marketDescription = "Mercado do Amadeu";


        AccountsList september = new AccountsList();
        AccountsList aMonth = new AccountsList();

        //Act
        september.addAccountToAccountsList(butcherDenomination, butcherDescription);
        aMonth.addAccountToAccountsList(marketDenomination, marketDescription);

        boolean result = september.equals(aMonth);

        //Assert
        assertEquals(false, result);

    }

    /**
     * Test if two lists are the same
     * With Hashcode
     */
    @Test
    @DisplayName("test if two lists are the same")
    public void testIfTwoAccountListsAreTheSameHashcode() {
        //Arrange

        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";


        AccountsList september = new AccountsList();
        AccountsList aMonth = new AccountsList();

        //Act
        september.addAccountToAccountsList(butcherDenomination, butcherDescription);
        aMonth.addAccountToAccountsList(butcherDenomination, butcherDescription);


        //Assert
        assertEquals(september.hashCode(), aMonth.hashCode());
    }

    @Test
    @DisplayName("test if two lists are the same - not the same")
    public void testIfTwoAccountListsAreNotTheSameHashcode() {
        //Arrange

        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";

        String marketDenomination = "Market";
        String marketDescription = "Mercado do Amadeu";


        AccountsList september = new AccountsList();
        AccountsList aMonth = new AccountsList();

        //Act
        september.addAccountToAccountsList(butcherDenomination, butcherDescription);
        aMonth.addAccountToAccountsList(marketDenomination,marketDescription);


        //Assert
        assertNotEquals(september.hashCode(), aMonth.hashCode());
    }


    /**
     * Test if Account was removed from list
     */
    /*@Test
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

    }*/

    /**
     * Test if several accounts were removed from list
     */
    /*@Test
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

    }*/

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
        accountsList.addAccountToAccountsList("xpto", "xpto Account");
        boolean validateIfAccountIsInTheAccountsList = accountsList.validateIfAccountIsInTheAccountsList(oneAccount);

        //Arrange
        assertTrue(validateIfAccountIsInTheAccountsList);
    }

    @Test
    @DisplayName("Test if account is in the List-False")
    void validateIfAccountIsInTheAccountsList_False() {
        //Arrange
        Account oneAccount = new Account("xpto", "xpto Account");
        Account otherAccount = new Account("xyz", "xyz Account");
        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.addAccountToAccountsList("xpto", "xpto Account");
        boolean validateIfAccountIsInTheAccountsList = accountsList.validateIfAccountIsInTheAccountsList(otherAccount);

        //Arrange
        assertFalse(validateIfAccountIsInTheAccountsList);
    }

}