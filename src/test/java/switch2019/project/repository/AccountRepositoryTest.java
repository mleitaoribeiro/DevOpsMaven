package switch2019.project.repository;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.security.krb5.internal.crypto.Des;
import switch2019.project.model.account.Account;
import switch2019.project.model.person.Person;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.PersonName;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Denomination;
import switch2019.project.model.shared.Description;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {


    /**
     * Test if account was added to the list
     */
    @Test
    @DisplayName("Test if more than one account was added to the list - True")
    public void testIfAccountsWereAddedToTheList_True() {
        //Arrange
        AccountRepository accountsList = new AccountRepository();

        //Act
        boolean real = accountsList.createAndAddAccountToAccountsList(new Denomination("xpto"),
                new Description("one account"))
                && accountsList.createAndAddAccountToAccountsList(new Denomination("xyz"),
                new Description("general"))
                && accountsList.createAndAddAccountToAccountsList(new Denomination("Millennium"),
                new Description("Millennium Account"));

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if more than one account was added to the list - one Account Denomination Is Null")
    public void testIfAccountsWereAddedToTheList_False_oneAccountDenominationIsNull() {
        //Arrange
        AccountRepository accountsList = new AccountRepository();

        //Act
        try {
            accountsList.createAndAddAccountToAccountsList(null, new Description("XOPT"));
            accountsList.createAndAddAccountToAccountsList(new Denomination("xyz"),
                    new Description("general"));
            accountsList.createAndAddAccountToAccountsList(new Denomination("Millennium"),
                    new Description("Millennium Account"));
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
        AccountRepository accountsList = new AccountRepository();

        //Act
        try {
            accountsList.createAndAddAccountToAccountsList(new Denomination("xpto"), null);
            accountsList.createAndAddAccountToAccountsList(new Denomination("xyz"),
                    new Description("general"));
            accountsList.createAndAddAccountToAccountsList(new Denomination("Millennium"),
                    new Description("Millennium Account"));
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
        AccountRepository accountsList = new AccountRepository();

        //Act
        accountsList.createAndAddAccountToAccountsList(new Denomination("xpto"),
                new Description("one account"));
        accountsList.createAndAddAccountToAccountsList(new Denomination("xyz"),
                new Description("other Account"));
        int result = accountsList.numberOfAccountsInTheAccountsList();

        //Assert
        assertEquals(2, result);
    }


    @Test
    @DisplayName("Test if the account was added to the list - only one account added - oneAccount is not contained")
    public void testAccountsAreInList_Not() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("cat account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("general"));

        AccountRepository accountsList = new AccountRepository();

        //Act
        accountsList.createAndAddAccountToAccountsList(new Denomination("xyz"),
                new Description("general"));

        boolean real = !accountsList.validateIfAccountIsInTheAccountsList(oneAccount)
                && accountsList.validateIfAccountIsInTheAccountsList(otherAccount);

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if more than one account was added to the list - all accounts are in the list")
    public void testIfAccountsAreInList_MoreThanOne() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("cat acccount"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("general"));
        Account anotherAccount = new Account(new Denomination("Millennium"),
                new Description("Millennium Account"));

        AccountRepository accountsList = new AccountRepository();

        //Act
        accountsList.createAndAddAccountToAccountsList(new Denomination("xpto"),
                new Description("cat acccount"));
        accountsList.createAndAddAccountToAccountsList(new Denomination("xyz"),
                new Description("general"));
        accountsList.createAndAddAccountToAccountsList(new Denomination("Millennium"),
                new Description("Millennium Account"));

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
        Account oneAccount = new Account(new Denomination("xpto"), new Description("cat acccount"));
        AccountRepository accountsList = new AccountRepository();

        //Act
        accountsList.createAndAddAccountToAccountsList(new Denomination("xpto"),
                new Description("cat acccount"));

        boolean expected = accountsList.validateIfAccountIsInTheAccountsList(oneAccount);

        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Test if one account is contained in the accounts list | False")
    public void testIfAccountsListContainAccount_false() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("cat acccount"));
        AccountRepository accountsList = new AccountRepository();

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


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = new AccountRepository();

        //Act
        september.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                new Description(butcherDescription));
        aMonth.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                new Description(butcherDescription));

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


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = new AccountRepository();

        //Act
        september.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                new Description(butcherDescription));
        aMonth.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                new Description(butcherDescription));

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


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = new AccountRepository();

        //Act
        try {
            september.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                    new Description(butcherDescription));
            aMonth.createAndAddAccountToAccountsList(null, new Description(butcherDescription));
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


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = new AccountRepository();

        //Act
        try {
            september.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                    new Description(butcherDescription));
            aMonth.createAndAddAccountToAccountsList(new Denomination(butcherDenomination), null);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The description can´t be null or empty!", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test if two account lists are the same - one of them is null")

    public void testIfTwoAccountListsAreTheSameOneIsNull() {
        //Arrange
        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = null;

        //Act
        september.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                new Description(butcherDescription));

        boolean result = september.equals(aMonth);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test if two account lists are the same -  Same List")
    public void testIfTwoAccountListsAreTheSameBeingTheSame() {
        //Arrange
        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";


        AccountRepository september = new AccountRepository();

        //Act
        september.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                new Description(butcherDescription));

        boolean result = september.equals(september);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if two account lists are the same - Different objects")
    public void testIfTwoAccountListsAreTheSameDifferentObjects() {
        //Arrange
        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";


        AccountRepository september = new AccountRepository();
        Person onePerson = new Person("Maria", new DateAndTime(1990, 12, 04), new Address("Braga"), new Address("Rua das Flores", "Braga", "4432-045"));

        //Act
        september.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                new Description(butcherDescription));
        boolean result = september.equals(onePerson);

        //Assert
        assertFalse(result);
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


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = new AccountRepository();

        //Act
        september.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                new Description(butcherDescription));
        aMonth.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                new Description(butcherDescription));


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


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = new AccountRepository();

        //Act
        september.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                new Description(butcherDescription));
        aMonth.createAndAddAccountToAccountsList(new Denomination(butcherDenomination),
                new Description(butcherDescription));


        //Assert
        assertNotEquals(september.hashCode(), aMonth.hashCode());
    }


    /**
     * Test if Account was removed from list
     */

    @Test
    @DisplayName("Test if an account was removed from an accounts list")
    public void testIfOneAccountWasRemoved() {
        Account butcher = new Account(new Denomination("Butcher"),
                new Description("Talho do Amadeu"));
        AccountRepository september = new AccountRepository();

        //Act
        september.createAndAddAccountToAccountsList(new Denomination("Butcher"),
                new Description("Talho do Amadeu"));
        september.createAndAddAccountToAccountsList(new Denomination("Market"),
                new Description("Mercado do Amadeu"));
        september.createAndAddAccountToAccountsList(new Denomination("Post"),
                new Description("Correios do Amadeu"));
        september.removeOneAccountFromAList(butcher);

        //Assert
        assertEquals(2, september.numberOfAccountsInTheAccountsList());

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts list - not in the list")
    public void testIfOneAccountWasRemovedNotInTheList() {
        Account post = new Account(new Denomination("Post"), new Description("Correios do Amadeu"));

        AccountRepository september = new AccountRepository();

        //Act
        september.createAndAddAccountToAccountsList(new Denomination("Butcher"),
                new Description("Talho do Amadeu"));
        september.createAndAddAccountToAccountsList(new Denomination("Market"),
                new Description("Mercado do Amadeu"));
        september.removeOneAccountFromAList(post);

        //Assert
        assertEquals(2, september.numberOfAccountsInTheAccountsList());

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts list - Account Null")
    public void testIfOneAccountWasRemovedNull() {

        //Arrange
        Account oneAccount = null;
        AccountRepository oneAccountsList = new AccountRepository();

        //Act
        oneAccountsList.createAndAddAccountToAccountsList(new Denomination("Butcher"),
                new Description("Talho do Amadeu"));
        oneAccountsList.createAndAddAccountToAccountsList(new Denomination("Market"),
                new Description("Mercado do Amadeu"));
        oneAccountsList.createAndAddAccountToAccountsList(new Denomination("Post"),
                new Description("Correios do Amadeu"));
        boolean real = oneAccountsList.removeOneAccountFromAList(oneAccount);

        //Assert
        assertFalse(real);

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts list - True")
    public void testIfOneAccountWasRemovedTrue() {

        //Arrange
        Account oneAccount = new Account(new Denomination("Post"),
                new Description("Correios do Amadeu"));
        AccountRepository oneAccountsList = new AccountRepository();

        //Act
        oneAccountsList.createAndAddAccountToAccountsList(new Denomination("Butcher"),
                new Description("Talho do Amadeu"));
        oneAccountsList.createAndAddAccountToAccountsList(new Denomination("Market"),
                new Description("Mercado do Amadeu"));
        oneAccountsList.createAndAddAccountToAccountsList(new Denomination("Post"),
                new Description("Correios do Amadeu"));
        boolean real = oneAccountsList.removeOneAccountFromAList(oneAccount);

        //Assert
        assertTrue(real);

    }


    /**
     * Test if account is in the List
     */
    @Test
    @DisplayName("Test if account is in the List-True")
    void validateIfAccountIsInTheAccountsList() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"), new Description("xpto Account"));
        AccountRepository accountsList = new AccountRepository();

        //Act
        accountsList.createAndAddAccountToAccountsList(new Denomination("xpto"),
                new Description("xpto Account"));
        boolean validateIfAccountIsInTheAccountsList = accountsList.validateIfAccountIsInTheAccountsList(oneAccount);

        //Arrange
        assertTrue(validateIfAccountIsInTheAccountsList);
    }

    @Test
    @DisplayName("Test if account is in the List-False")
    void validateIfAccountIsInTheAccountsList_False() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        AccountRepository accountsList = new AccountRepository();

        //Act
        accountsList.createAndAddAccountToAccountsList(new Denomination("xpto"),
                new Description("xpto Account"));
        boolean validateIfAccountIsInTheAccountsList = accountsList.validateIfAccountIsInTheAccountsList(otherAccount);

        //Arrange
        assertFalse(validateIfAccountIsInTheAccountsList);
    }

    /**
     * AccountsList.toString test
     */
    @Test
    @DisplayName("test if an accountList can be put into a string")
    void toStringOfAccountsListTest() {

        //Arrange:
        Account account1 = new Account(new Denomination("test account 1"),
                new Description("account for test purposes"));
        Account account2 = new Account(new Denomination("test account 2"),
                new Description("account for test purposes"));
        AccountRepository testAccountsList = new AccountRepository();

        //Act:
        testAccountsList.createAndAddAccountToAccountsList(new Denomination("test account 1"),
                new Description("account for test purposes"));
        testAccountsList.createAndAddAccountToAccountsList(new Denomination("test account 2"),
                new Description("account for test purposes"));
        String result = testAccountsList.toString();

        //Assert:
        assertEquals("Accounts List: [TEST ACCOUNT 2, account for test purposes, 0.0€, TEST ACCOUNT 1, account for test purposes, 0.0€]", result);
    }
}
