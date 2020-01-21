package switch2019.project.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
        boolean real = accountsList.createAndAddAccountToAccountsList("xpto", "one account")
                && accountsList.createAndAddAccountToAccountsList("xyz", "general")
                && accountsList.createAndAddAccountToAccountsList("Millennium", "Millennium Account");

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
            accountsList.createAndAddAccountToAccountsList(null, "XOPT");
            accountsList.createAndAddAccountToAccountsList("xyz", "general");
            accountsList.createAndAddAccountToAccountsList("Millennium", "Millennium Account");
        }
        //Assert
        catch (IllegalArgumentException denomination) {
            assertEquals("The denomination can´t be null or empty!", denomination.getMessage());
        }
    }

    @Test
    @DisplayName("Test if more than one account was added to the list - one Account Denomination Is Null")
    public void testIfAccountsWereAddedToTheList_False_oneAccountDescriptionIsNull() {
        //Arrange
        AccountsList accountsList = new AccountsList();

        //Act
        try {
            accountsList.createAndAddAccountToAccountsList("xpto", null);
            accountsList.createAndAddAccountToAccountsList("xyz", "general");
            accountsList.createAndAddAccountToAccountsList("Millennium", "Millennium Account");
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The description can´t be null or empty!", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test if the account was added to the list - the number of accounts on the list was increased")
    public void testIfAccountsListIncreased() {
        //Arrange
        AccountsList accountsList = new AccountsList();

        //Act
        accountsList.createAndAddAccountToAccountsList("xpto", "one account");
        accountsList.createAndAddAccountToAccountsList("xyz", "other Account");
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
        accountsList.createAndAddAccountToAccountsList("xyz", "general");

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
        accountsList.createAndAddAccountToAccountsList("xpto", "cat acccount");
        accountsList.createAndAddAccountToAccountsList("xyz", "general");
        accountsList.createAndAddAccountToAccountsList("Millennium", "Millennium Account");

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
        accountsList.createAndAddAccountToAccountsList("xpto", "cat acccount");

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
        september.createAndAddAccountToAccountsList(butcherDenomination, butcherDescription);
        aMonth.createAndAddAccountToAccountsList(butcherDenomination, butcherDescription);

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
        september.createAndAddAccountToAccountsList(butcherDenomination, butcherDescription);
        aMonth.createAndAddAccountToAccountsList(marketDenomination, marketDescription);

        boolean result = september.equals(aMonth);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two account lists are the same - null denomination")

    public void testIfTwoAccountListsAreTheSameNull() {
        //Arrange
        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";


        String marketDescription = "Mercado do Amadeu";


        AccountsList september = new AccountsList();
        AccountsList aMonth = new AccountsList();

        //Act
        try {
            september.createAndAddAccountToAccountsList(butcherDenomination, butcherDescription);
            aMonth.createAndAddAccountToAccountsList(null, marketDescription);
        }

        //Assert
        catch (IllegalArgumentException denomination) {
            assertEquals("The denomination can´t be null or empty!", denomination.getMessage());
        }
    }

        @Test
        @DisplayName("Test if two account lists are the same - null description")

        public void testIfTwoAccountListsAreTheSameNullDescription() {
            //Arrange
            String butcherDenomination = "Butcher";
            String butcherDescription = "Talho do Amadeu";

            String marketDenomination = "Market";



            AccountsList september = new AccountsList();
            AccountsList aMonth = new AccountsList();

            //Act
            try {
                september.createAndAddAccountToAccountsList(butcherDenomination, butcherDescription);
                aMonth.createAndAddAccountToAccountsList(marketDenomination, null);
            }

            //Assert
            catch (IllegalArgumentException description) {
                assertEquals("The description can´t be null or empty!", description.getMessage());
            }


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
        september.createAndAddAccountToAccountsList(butcherDenomination, butcherDescription);
        aMonth.createAndAddAccountToAccountsList(butcherDenomination, butcherDescription);


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
        september.createAndAddAccountToAccountsList(butcherDenomination, butcherDescription);
        aMonth.createAndAddAccountToAccountsList(marketDenomination,marketDescription);


        //Assert
        assertNotEquals(september.hashCode(), aMonth.hashCode());
    }


    /**
     * Test if Account was removed from list
     */
    @Test
    @DisplayName("Test if an account was removed from an accounts list")
    public void testIfOneAccountWasRemoved (){
        Account butcher=new Account("Butcher", "Talho do Amadeu");
        AccountsList september = new AccountsList();

        //Act
        september.createAndAddAccountToAccountsList("Butcher", "Talho do Amadeu");
        september.createAndAddAccountToAccountsList("Market","Mercado do Amadeu");
        september.createAndAddAccountToAccountsList("Post","Correios do Amadeu");
        september.removeOneAccountFromAList(butcher);

        //Assert
        assertEquals(2,september.numberOfAccountsInTheAccountsList());

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts list - not in the list")
    public void testIfOneAccountWasRemovedNotInTheList (){
        Account post = new Account("Post","Correios do Amadeu");

        AccountsList september = new AccountsList();

        //Act
        september.createAndAddAccountToAccountsList("Butcher", "Talho do Amadeu");
        september.createAndAddAccountToAccountsList("Market","Mercado do Amadeu");
        september.removeOneAccountFromAList(post);

        //Assert
        assertEquals(2,september.numberOfAccountsInTheAccountsList());

    }

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
        accountsList.createAndAddAccountToAccountsList("xpto", "xpto Account");
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
        accountsList.createAndAddAccountToAccountsList("xpto", "xpto Account");
        boolean validateIfAccountIsInTheAccountsList = accountsList.validateIfAccountIsInTheAccountsList(otherAccount);

        //Arrange
        assertFalse(validateIfAccountIsInTheAccountsList);
    }

}
