package switch2019.project.repository;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.account.Account;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest {

    /**
     * Test if accounts was added to the list
     */

    @Test
    @DisplayName("Test if  one account was added to the repository with Person ID - Main Scenario")
    public void testIfAccountsWasAddedToTheRepositoryWithPersonID() {
        //Arrange
        AccountRepository accountRepository = new AccountRepository();

        //Act
        boolean real = accountRepository.createAccount(new Denomination("Revolut"),
                new Description("Online Expenses"), new PersonID(new Email("martacarda@hotmail.com")));

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if  one account was added to the repository with null Person ID")
    public void testIfAccountsWasAddedToTheRepositoryWithNullPersonID() {
        //Arrange
        AccountRepository accountRepository = new AccountRepository();

        //Act
        boolean real = accountRepository.createAccount(new Denomination("Revolut"),
                new Description("Online Expenses"), new PersonID(null));

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if more than one account was added to the repository with Person ID ")
    public void testIfAccountsWereAddedToTheRepositoryWithPersonID() {
        //Arrange
        AccountRepository accountRepository = new AccountRepository();

        //Act
        boolean real = accountRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new PersonID(new Email("martacarda@hotmail.com")))
                && accountRepository.createAccount(new Denomination("xyz"),
                new Description("general"),  new PersonID(new Email("mariasousa@gmail.com")))
                && accountRepository.createAccount(new Denomination("Millennium"),
                new Description("Millennium Account"), new PersonID(new Email("antoniomagalhaes@isep.ipp.pt")));

        //Assert
        assertTrue(real);
    }


    @Test
    @DisplayName("Test if  one account was added to the repository with Group ID - Main Scenario")
    public void testIfAccountsWasAddedToTheRepositoryWithGroupID() {
        //Arrange
        AccountRepository accountRepository = new AccountRepository();

        //Act
        boolean real = accountRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new GroupID(new Description("Random Group")));

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if  one account was added to the repository with null Group ID")
    public void testIfAccountsWasAddedToTheRepositoryWithNullGroupID() {
        //Arrange
        AccountRepository accountRepository = new AccountRepository();

        //Act
        boolean real = accountRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new GroupID(new Description("Random Group")));

        //Assert
        assertTrue(real);
    }


    @Test
    @DisplayName("Test if more than one account was added to the list with Group ID")
    public void testIfAccountsWereAddedToTheRepositoryGroupID() {
        //Arrange
        AccountRepository accountRepository = new AccountRepository();

        //Act
        boolean real = accountRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new GroupID(new Description("Friends")))
                && accountRepository.createAccount(new Denomination("xyz"),
                new Description("general"),  new GroupID(new Description("Business")))
                && accountRepository.createAccount(new Denomination("Millennium"),
                new Description("Millennium Account"), new GroupID(new Description("Working Group")));

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if more than one account was added to the repository with PersonID - one Account Denomination Is Null")
    public void testIfAccountsWereAddedToTheRepositoryOneAccountDenominationIsNull() {
        //Arrange
        AccountRepository accountRepository = new AccountRepository();

        //Act
        try {
            accountRepository.createAccount(null, new Description("XPTO"), new PersonID(new Email("12345@isep.ipp.pt")));

        }
        //Assert
        catch (IllegalArgumentException denomination) {
            assertEquals("The denomination can´t be null or empty!", denomination.getMessage());
        }
    }

    @Test
    @DisplayName("Test if more than one account was added to the repository PersonID - one Account Denomination Is Null")
    public void testIfAccountIsAddedToTheRepositoryOneAccountDescriptionIsNull() {
        //Arrange
        AccountRepository accountRepository = new AccountRepository();

        //Act
        try {
            accountRepository.createAccount(new Denomination("xpto"), null, new PersonID(new Email("miguelito@outlook.pt")));

        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The description can´t be null or empty!", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test if the account was added to the repository with PersonID and GroupID - the number of accounts on the repository was increased")
    public void testIfAccountsRepositoryIncreased() {
        //Arrange
        AccountRepository accountRepository = new AccountRepository();

        //Act
        accountRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new PersonID(new Email("martacarda@live.pt.pt")));
        accountRepository.createAccount(new Denomination("xyz"),
                new Description("other Account"), new GroupID(new Description("Business")));
        int result = accountRepository.numberOfAccountsInTheAccountsRepository();

        //Assert
        assertEquals(2, result);
    }


    @Test
    @DisplayName("Test if the account was added to the repository with ID's- only one account added - oneAccount is not contained in the repository")
    public void testIfAccountsAreInRepositoryOneAccountIsFalse() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("cat account"), new GroupID(new Description("Kitten Friends")));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("general"), new PersonID(new Email("random.letters@123.com")));

        AccountRepository accountRepository = new AccountRepository();

        //Act
        accountRepository.createAccount(new Denomination("xyz"),
                new Description("general"), new PersonID(new Email("random.letters@123.com")));

        boolean real = !accountRepository.validateIfAccountIsInTheAccountsRepository(oneAccount)
                && accountRepository.validateIfAccountIsInTheAccountsRepository(otherAccount);

        //Assert
       // assertTrue(real);
    }

    @Test
    @DisplayName("Test if more than one account was added to the repository - all accounts are in the repository")
    public void testIfAccountsAreInRepositoryMoreThanOne() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("cat acccount"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("general"));
        Account anotherAccount = new Account(new Denomination("Millennium"),
                new Description("Millennium Account"));

        AccountRepository accountRepository = new AccountRepository();

        //Act
        accountRepository.createAccount(new Denomination("xpto"),
                new Description("cat acccount"));
        accountRepository.createAccount(new Denomination("xyz"),
                new Description("general"));
        accountRepository.createAccount(new Denomination("Millennium"),
                new Description("Millennium Account"));

        boolean real = accountRepository.validateIfAccountIsInTheAccountsRepository(oneAccount)
                && accountRepository.validateIfAccountIsInTheAccountsRepository(otherAccount)
                && accountRepository.validateIfAccountIsInTheAccountsRepository(anotherAccount);

        //Assert
        //assertTrue(real);
    }

    /**
     * Test if Account is contained in the Accounts Repository
     */
    @Test
    @DisplayName("Test if one account is contained in the accounts repository| True")
    public void testIfAccountsRepositoryContainAccountTrue() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"), new Description("cat acccount"));
        AccountRepository accountRepository = new AccountRepository();

        //Act
        accountRepository.createAccount(new Denomination("xpto"),
                new Description("cat acccount"));

        boolean expected = accountRepository.validateIfAccountIsInTheAccountsRepository(oneAccount);

        //Assert
        //assertTrue(expected);
    }

    @Test
    @DisplayName("Test if one account is contained in the accounts Repostiroy| False")
    public void testIfAccountsRepositoryContainAccount_false() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("cat acccount"));
        AccountRepository accountRepository = new AccountRepository();

        //Act

        boolean notContained = accountRepository.validateIfAccountIsInTheAccountsRepository(oneAccount);

        //Assert
        assertFalse(notContained);
    }

    /**
     * Test if two account Repository are the same
     */
    @Test
    @DisplayName("Test if two account Repository are the same - true")

    public void testIfTwoAccountRAreTheSame() {
        //Arrange

        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = new AccountRepository();

        //Act
        september.createAccount(new Denomination(butcherDenomination),
                new Description(butcherDescription));
        aMonth.createAccount(new Denomination(butcherDenomination),
                new Description(butcherDescription));

        boolean result = september.equals(aMonth);

        //Assert
        //assertEquals(true, result);

    }

    @Test
    @DisplayName("Test if two account Repository are the same - false")

    public void testIfTwoAccountsRepositoryAreTheSameNo() {
        //Arrange
        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";

        String marketDenomination = "Market";
        String marketDescription = "Mercado do Amadeu";


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = new AccountRepository();

        //Act
        september.createAccount(new Denomination(butcherDenomination),
                new Description(butcherDescription));
        aMonth.createAccount(new Denomination(butcherDenomination),
                new Description(butcherDescription));

        boolean result = september.equals(aMonth);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test if two account repository are the same - null denomination")

    public void testIfTwoAccountRepositoryAreTheSameNull() {
        //Arrange
        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";


        String marketDescription = "Mercado do Amadeu";


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = new AccountRepository();

        //Act
        try {
            september.createAccount(new Denomination(butcherDenomination),
                    new Description(butcherDescription));
            aMonth.createAccount(null, new Description(butcherDescription));
        }

        //Assert
        catch (IllegalArgumentException denomination) {
            assertEquals("The denomination can´t be null or empty!", denomination.getMessage());
        }
    }

    @Test
    @DisplayName("Test if two account repository are the same - null description")

    public void testIfTwoAccountRepositoryAreTheSameNullDescription() {
        //Arrange
        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";

        String marketDenomination = "Market";


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = new AccountRepository();

        //Act
        try {
            september.createAccount(new Denomination(butcherDenomination),
                    new Description(butcherDescription));
            aMonth.createAccount(new Denomination(butcherDenomination), null);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The description can´t be null or empty!", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test if two account Repository are the same - one of them is null")

    public void testIfTwoAccountRepositoryAreTheSameOneIsNull() {
        //Arrange
        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";


        AccountRepository september = new AccountRepository();
        AccountRepository aMonth = null;

        //Act
        september.createAccount(new Denomination(butcherDenomination),
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
        september.createAccount(new Denomination(butcherDenomination),
                new Description(butcherDescription));

        boolean result = september.equals(september);

        //Assert
        // assertTrue(result);
    }

    @Test
    @DisplayName("Test if two account lists are the same - Different objects")
    public void testIfTwoAccountListsAreTheSameDifferentObjects() {
        //Arrange
        String butcherDenomination = "Butcher";
        String butcherDescription = "Talho do Amadeu";


        AccountRepository september = new AccountRepository();
        Person onePerson = new Person("Maria", new DateAndTime(1990, 12, 04), new Address("Braga"),
                new Address("Rua das Flores", "Braga", "4432-045"), new Email("1234@isep.pt"));

        //Act
        september.createAccount(new Denomination(butcherDenomination),
                new Description(butcherDescription));
        boolean result = september.equals(onePerson);

        //Assert
        //assertFalse(result);
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
        september.createAccount(new Denomination(butcherDenomination),
                new Description(butcherDescription));
        aMonth.createAccount(new Denomination(butcherDenomination),
                new Description(butcherDescription));


        //Assert
        //assertEquals(september.hashCode(), aMonth.hashCode());
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
        september.createAccount(new Denomination(butcherDenomination),
                new Description(butcherDescription));
        aMonth.createAccount(new Denomination(butcherDenomination),
                new Description(butcherDescription));


        //Assert
        assertNotEquals(september.hashCode(), aMonth.hashCode());
    }


    /**
     * Test if Account was removed from the Repository
     */

    @Test
    @DisplayName("Test if an account was removed from an accounts Repository")
    public void testIfOneAccountWasRemoved() {
        Account butcher = new Account(new Denomination("Butcher"),
                new Description("Talho do Amadeu"));
        AccountRepository september = new AccountRepository();

        //Act
        september.createAccount(new Denomination("Butcher"),
                new Description("Talho do Amadeu"));
        september.createAccount(new Denomination("Market"),
                new Description("Mercado do Amadeu"));
        september.createAccount(new Denomination("Post"),
                new Description("Correios do Amadeu"));
        september.removeOneAccountFromRepository(butcher);

        //Assert
        //assertEquals(2, september.numberOfAccountsInTheAccountsList());

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts repository - not in the repository")
    public void testIfOneAccountWasRemovedNotInTheRepository() {
        Account post = new Account(new Denomination("Post"), new Description("Correios do Amadeu"));

        AccountRepository september = new AccountRepository();

        //Act
        september.createAccount(new Denomination("Butcher"),
                new Description("Talho do Amadeu"));
        september.createAccount(new Denomination("Market"),
                new Description("Mercado do Amadeu"));
        september.removeOneAccountFromRepository(post);

        //Assert
        assertEquals(2, september.numberOfAccountsInTheAccountsRepository());

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts repository - Account Null")
    public void testIfOneAccountWasRemovedNull() {

        //Arrange
        Account oneAccount = null;
        AccountRepository oneAccountsList = new AccountRepository();

        //Act
        oneAccountsList.createAccount(new Denomination("Butcher"),
                new Description("Talho do Amadeu"));
        oneAccountsList.createAccount(new Denomination("Market"),
                new Description("Mercado do Amadeu"));
        oneAccountsList.createAccount(new Denomination("Post"),
                new Description("Correios do Amadeu"));
        boolean real = oneAccountsList.removeOneAccountFromRepository(oneAccount);

        //Assert
        assertFalse(real);

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts repository - True")
    public void testIfOneAccountWasRemovedTrue() {

        //Arrange
        Account oneAccount = new Account(new Denomination("Post"),
                new Description("Correios do Amadeu"));
        AccountRepository oneAccountsList = new AccountRepository();

        //Act
        oneAccountsList.createAccount(new Denomination("Butcher"),
                new Description("Talho do Amadeu"));
        oneAccountsList.createAccount(new Denomination("Market"),
                new Description("Mercado do Amadeu"));
        oneAccountsList.createAccount(new Denomination("POST"),
                new Description("Correios do Amadeu"));
        boolean real = oneAccountsList.removeOneAccountFromRepository(oneAccount);

        //Assert
        //assertTrue(real);

    }


    /**
     * Test if account is in the repository
     */
    @Test
    @DisplayName("Test if account is in the Repository-True")
    void validateIfAccountIsInTheAccountsRepository() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"), new Description("xpto Account"));
        AccountRepository accountsRepository = new AccountRepository();

        //Act
        accountsRepository.createAccount(new Denomination("xpto"),
                new Description("xpto Account"));
        boolean validateIfAccountIsInTheAccountsList = accountsRepository.validateIfAccountIsInTheAccountsRepository(oneAccount);

        //Arrange
        //assertTrue(validateIfAccountIsInTheAccountsList);
    }

    @Test
    @DisplayName("Test if account is in the Repository-False")
    void validateIfAccountIsInTheAccountsRepository_False() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xpto Account"));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"));
        AccountRepository accountsList = new AccountRepository();

        //Act
        accountsList.createAccount(new Denomination("xpto"),
                new Description("xpto Account"));
        boolean validateIfAccountIsInTheAccountsList = accountsList.validateIfAccountIsInTheAccountsRepository(otherAccount);

        //Arrange
        assertFalse(validateIfAccountIsInTheAccountsList);
    }

    /**
     * AccountsList.toString test
     */
    // Result order cant be predicted since accountsRepository has an HashSet of accounts
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
        testAccountsList.createAccount(new Denomination("test account 1"),
                new Description("account for test purposes"));
        testAccountsList.createAccount(new Denomination("test account 2"),
                new Description("account for test purposes"));
        String result = testAccountsList.toString();

        //Assert:
        //assertEquals("Accounts List: [TEST ACCOUNT 1, ACCOUNT FOR TEST PURPOSES, 0.0 EUR€, TEST ACCOUNT 2, " +
         //       "ACCOUNT FOR TEST PURPOSES, 0.0 EUR€]", result);
    }
}
