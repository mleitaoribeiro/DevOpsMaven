package switch2019.project.infrastructure.repositories;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountInMemoryRepositoryTest {

    private AccountInMemoryRepository accountInMemoryRepository;

    @BeforeEach
    void universeSetUp() {
        accountInMemoryRepository = new AccountInMemoryRepository();
        accountInMemoryRepository.createAccount(new Denomination("CGD"),
                new Description("CGD"), new PersonID(new Email("amadeu1@gmail.com")));
        accountInMemoryRepository.createAccount(new Denomination("BPI"),
                new Description("BPI"), new PersonID(new Email("amadeu2@gmail.com")));
        accountInMemoryRepository.createAccount(new Denomination("BIC"),
                new Description("BIC"), new PersonID(new Email("amadeu2@gmail.com")));
        accountInMemoryRepository.createAccount(new Denomination("SAN"),
                new Description("SAN"), new PersonID(new Email("amadeu3@gmail.com")));
    }


    /**
     * Test if accounts was added to the list
     */

    @Test
    @DisplayName("Test if one account was added to the repository with Person ID - Main Scenario")
    public void testIfAccountsWasAddedToTheRepositoryWithPersonID() {
        //Arrange
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();
        Account accountExpected = new Account(new Denomination("Revolut"),
                new Description("Online Expenses"), new PersonID((new Email("martacarda@hotmail.com"))));

        //Act
        Account accountCreated = accountInMemoryRepository.createAccount(new Denomination("Revolut"),
                new Description("Online Expenses"), new PersonID(new Email("martacarda@hotmail.com")));

        //Assert
        assertEquals(accountExpected, accountCreated);
    }

    @Test
    @DisplayName("Test if  one account was added to the repository with null Person ID")
    public void testIfAccountsWasAddedToTheRepositoryWithNullPersonID() {
        //Arrange
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();

        //Act
        try {
            accountInMemoryRepository.createAccount(new Denomination("Revolut"),
                    new Description("Online Expenses"), new PersonID(null));
        }
        //Assert
        catch (IllegalArgumentException email) {
            assertEquals("email can't be null.", email.getMessage());
        }
    }

    @Test
    @DisplayName("Test if more than one account was added to the repository with Person ID ")
    public void testIfAccountsWereAddedToTheRepositoryWithPersonID() {
        //Arrange
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();
        Account accountExpected = new Account(new Denomination("xpto"),
                new Description("one account"), new PersonID(new Email("martacarda@hotmail.com")));
        Account accountExpected2 = new Account(new Denomination("xyz"),
                new Description("general"), new PersonID(new Email("mariasousa@gmail.com")));
        Account accountExpected3 = new Account(new Denomination("Millennium"),
                new Description("Millennium Account"), new PersonID(new Email("antoniomagalhaes@isep.ipp.pt")));


        //Act
        Account accountCreated = accountInMemoryRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new PersonID(new Email("martacarda@hotmail.com")));
        Account accountCreated2 = accountInMemoryRepository.createAccount(new Denomination("xyz"),
                new Description("general"), new PersonID(new Email("mariasousa@gmail.com")));
        Account accountCreated3 = accountInMemoryRepository.createAccount(new Denomination("Millennium"),
                new Description("Millennium Account"), new PersonID(new Email("antoniomagalhaes@isep.ipp.pt")));

        //Assert
        Assertions.assertAll(
                () -> assertEquals(accountExpected, accountCreated),
                () -> assertEquals(accountExpected2, accountCreated2),
                () -> assertEquals(accountExpected3, accountCreated3)
        );
    }

    @Test
    @DisplayName("Test if one account was created if it already exist - Person ID")
    public void testIfAccountWasAddedIfItAlreadyExistsPersonID() {
        //Arrange
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();
        accountInMemoryRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new PersonID(new Email("martacarda@hotmail.com")));

        //Act
        try {
            accountInMemoryRepository.createAccount(new Denomination("xpto"),
                    new Description("one account"), new PersonID(new Email("martacarda@hotmail.com")));
        } catch (IllegalArgumentException ex) {
            assertEquals("This account already exists.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Test if one account was created if it already exist - Person Group ID")
    public void testIfAccountWasAddedIfItAlreadyExistsGroupID() {
        //Arrange
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();
        accountInMemoryRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new GroupID(new Description("Friends")));

        //Act
        try {
            accountInMemoryRepository.createAccount(new Denomination("xpto"),
                    new Description("one account"), new GroupID(new Description("Friends")));
        } catch (IllegalArgumentException ex) {
            assertEquals("This account already exists.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Test if  one account was added to the repository with Group ID - Main Scenario")
    public void testIfAccountsWasAddedToTheRepositoryWithGroupID() {
        //Arrange
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();
        Account accountExpected = new Account(new Denomination("Revolut"),
                new Description("Online Expenses"), new GroupID((new Description("Randam Group"))));


        //Act
        Account accountCreated = accountInMemoryRepository.createAccount(new Denomination("Revolut"),
                new Description("Online Expenses"), new GroupID(new Description("Randam Group")));

        //Assert
        assertEquals(accountExpected, accountCreated);
    }

    @Test
    @DisplayName("Test if  one account was added to the repository with null Group ID")
    public void testIfAccountsWasAddedToTheRepositoryWithNullGroupID() {
        //Arrange
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();
        //Act
        try {
            Account accountCreated = accountInMemoryRepository.createAccount(new Denomination("xpto"),
                    new Description("one account"), new GroupID(new Description(null)));
            fail();
        }
        // Assert
        catch (IllegalArgumentException email) {
            assertEquals("The description can't be null or empty.", email.getMessage());
        }
    }

    @Test
    @DisplayName("Test if more than one account was added to the list with Group ID")
    public void testIfAccountsWereAddedToTheRepositoryGroupID() {
        //Arrange
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();
        Account accountExpected = new Account(new Denomination("xpto"),
                new Description("one account"), new GroupID(new Description("familia")));
        Account accountExpected2 = new Account(new Denomination("xyz"),
                new Description("general"), new GroupID(new Description("copos")));
        Account accountExpected3 = new Account(new Denomination("Millennium"),
                new Description("Millennium Account"), new GroupID(new Description("andorinhas")));


        //Act
        Account accountCreated = accountInMemoryRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new GroupID(new Description("familia")));
        Account accountCreated2 = accountInMemoryRepository.createAccount(new Denomination("xyz"),
                new Description("general"), new GroupID(new Description ("copos")));
        Account accountCreated3 = accountInMemoryRepository.createAccount(new Denomination("Millennium"),
                new Description("Millennium Account"), new GroupID(new Description("andorinhas")));

        //Assert
        Assertions.assertAll(
                () -> assertEquals(accountExpected, accountCreated),
                () -> assertEquals(accountExpected2, accountCreated2),
                () -> assertEquals(accountExpected3, accountCreated3)
        );
    }


    @Test
    @DisplayName("Test if more than one account was added to the repository with PersonID - one Account Denomination Is Null")
    public void testIfAccountsWereAddedToTheRepositoryOneAccountDenominationIsNull() {
        //Arrange
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();

        //Act
        try {
            accountInMemoryRepository.createAccount(null, new Description("XPTO"), new PersonID(new Email("12345@isep.ipp.pt")));

        }
        //Assert
        catch (IllegalArgumentException denomination) {
            assertEquals("Neither the Denomination nor OwnerID can be null.", denomination.getMessage());
        }
    }

    @Test
    @DisplayName("Test if more than one account was added to the repository PersonID - one Account Denomination Is Null")
    public void testIfAccountIsAddedToTheRepositoryOneAccountDescriptionIsNull() {
        //Arrange
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();

        //Act
        try {
            accountInMemoryRepository.createAccount(new Denomination("xpto"), null, new PersonID(new Email("miguelito@outlook.pt")));

        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("Account description can't be null.", description.getMessage());
        }
    }

    @Test
    @DisplayName("Test if the account was added to the repository with PersonID and GroupID - the number of accounts on the repository was increased")
    public void testIfAccountsRepositoryIncreased() {
        //Arrange
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();

        //Act
        accountInMemoryRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new PersonID(new Email("martacarda@live.pt.pt")));
        accountInMemoryRepository.createAccount(new Denomination("xyz"),
                new Description("other Account"), new GroupID(new Description("Business")));
        int result = accountInMemoryRepository.repositorySize();

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

        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();

        //Act
        accountInMemoryRepository.createAccount(new Denomination("xyz"),
                new Description("general"), new PersonID(new Email("random.letters@123.com")));

        boolean real = !accountInMemoryRepository.isIDOnRepository(oneAccount.getID())
                && accountInMemoryRepository.isIDOnRepository(otherAccount.getID());

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if accounts all accounts are in the repository")
    public void testIfAccountsAreInRepositoryMoreThanOne() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("cat acccount"), new PersonID(new Email("xpto@email.pt")));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("general"), new PersonID(new Email("xyz@email.pt")));
        Account anotherAccount = new Account(new Denomination("Millennium"),
                new Description("Millennium Account"), new PersonID(new Email("millenum@isep.pt")));

        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();

        //Act
        accountInMemoryRepository.createAccount(new Denomination("xpto"),
                new Description("cat acccount"), new PersonID(new Email("xpto@email.pt")));
        accountInMemoryRepository.createAccount(new Denomination("xyz"),
                new Description("general"), new PersonID(new Email("xyz@email.pt")));
        accountInMemoryRepository.createAccount(new Denomination("Millennium"),
                new Description("Millennium Account"), new PersonID(new Email("millenum@isep.pt")));

        boolean real = accountInMemoryRepository.isIDOnRepository(oneAccount.getID())
                && accountInMemoryRepository.isIDOnRepository(otherAccount.getID())
                && accountInMemoryRepository.isIDOnRepository(anotherAccount.getID());

        //Assert
        assertTrue(real);
    }

    /**
     * Test if Account is contained in the Accounts Repository
     */

    @Test
    @DisplayName("Test if one account is contained in the accounts repository| True")
    public void testIfAccountsRepositoryContainAccountTrue() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"), new Description("cat acccount"),
                new PersonID(new Email("mocho@gmail.com")));
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();

        //Act
        accountInMemoryRepository.createAccount(new Denomination("xpto"),
                new Description("cat acccount"), new PersonID(new Email("mocho@gmail.com")));

        boolean expected = accountInMemoryRepository.isIDOnRepository(oneAccount.getID());

        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Test if one account is contained in the accounts Repository | False")
    public void testIfAccountsRepositoryContainAccountFalse() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("cat acccount"), new PersonID(new Email("mocho@gmail.com")));
        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();

        //Act

        boolean notContained = accountInMemoryRepository.isIDOnRepository(oneAccount.getID());

        //Assert
        assertFalse(notContained);
    }

    @Test
    @DisplayName("Test if an account was removed from an accounts Repository")
    public void testIfOneAccountWasRemoved() {
        Account butcher = new Account(new Denomination("Butcher"),
                new Description("Talho do Amadeu"), new PersonID(new Email("amadeu1@gmail.com")));

        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();

        //Act
        accountInMemoryRepository.createAccount(new Denomination("Butcher"),
                new Description("Talho do Amadeu"), new PersonID(new Email("amadeu1@gmail.com")));
        accountInMemoryRepository.createAccount(new Denomination("Market"),
                new Description("Mercado do Amadeu"), new PersonID(new Email("amadeu2@gmail.com")));
        accountInMemoryRepository.createAccount(new Denomination("Post"),
                new Description("Correios do Amadeu"), new PersonID(new Email("amadeu3@gmail.com")));

        accountInMemoryRepository.removeAccount(butcher);

        //Assert
        assertEquals(2, accountInMemoryRepository.repositorySize());

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts repository - not in the repository")
    public void testIfOneAccountWasRemovedNotInTheRepository() {
        Account post = new Account(new Denomination("Post"), new Description("Correios do Amadeu"),
                new PersonID(new Email("amadeu1@gmail.com")));

        AccountInMemoryRepository accountInMemoryRepository = new AccountInMemoryRepository();

        //Act
        accountInMemoryRepository.createAccount(new Denomination("Butcher"),
                new Description("Talho do Amadeu"), new PersonID(new Email("amadeu1@gmail.com")));
        accountInMemoryRepository.createAccount(new Denomination("Market"),
                new Description("Mercado do Amadeu"), new PersonID(new Email("amadeu1@gmail.com")));

        accountInMemoryRepository.removeAccount(post);

        // Assert
        assertEquals(2, accountInMemoryRepository.repositorySize());

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts repository - Account Null")
    public void testIfOneAccountWasRemovedNull() {

        //Arrange
        Account oneAccount = null;
        AccountInMemoryRepository oneAccountsList = new AccountInMemoryRepository();

        //Act
        oneAccountsList.createAccount(new Denomination("Butcher"),
                new Description("Talho do Amadeu"), new PersonID(new Email("amadeu1@gmail.com")));
        oneAccountsList.createAccount(new Denomination("Market"),
                new Description("Mercado do Amadeu"), new PersonID(new Email("amadeu1@gmail.com")));
        oneAccountsList.createAccount(new Denomination("Post"),
                new Description("Correios do Amadeu"), new PersonID(new Email("amadeu1@gmail.com")));
        boolean real = oneAccountsList.removeAccount(oneAccount);

        //Assert
        assertFalse(real);

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts repository - True")
    public void testIfOneAccountWasRemovedTrue() {

        //Arrange
        Account oneAccount = new Account(new Denomination("Post"),
                new Description("Correios do Amadeu"), new PersonID(new Email("amadeu1@gmail.com")));

        AccountInMemoryRepository oneAccountsList = new AccountInMemoryRepository();

        //Act
        oneAccountsList.createAccount(new Denomination("Butcher"),
                new Description("Talho do Amadeu"), new PersonID(new Email("amadeu1@gmail.com")));
        oneAccountsList.createAccount(new Denomination("Market"),
                new Description("Mercado do Amadeu"), new PersonID(new Email("amadeu2@gmail.com")));
        oneAccountsList.createAccount(new Denomination("POST"),
                new Description("Correios do Amadeu"), new PersonID(new Email("amadeu1@gmail.com")));

        boolean real = oneAccountsList.removeAccount(oneAccount);

        //Assert
        assertTrue(real);

    }

    /**
     * Test if account is in the repository
     */

    @Test
    @DisplayName("Test if account is in the Repository-True")
    void validateIfAccountIsInTheAccountsRepository() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"), new Description("xpto Account"), new PersonID(new Email("amadeu1@gmail.com")));
        AccountInMemoryRepository accountsRepository = new AccountInMemoryRepository();

        //Act
        accountsRepository.createAccount(new Denomination("xpto"),
                new Description("xpto Account"), new PersonID(new Email("amadeu1@gmail.com")));
        boolean validateIfAccountIsInTheAccountsList = accountsRepository.isIDOnRepository(oneAccount.getID());

        //Arrange
        assertTrue(validateIfAccountIsInTheAccountsList);
    }

    @Test
    @DisplayName("Test if account is in the Repository - False")
    void validateIfAccountIsInTheAccountsRepositoryFalse() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("xpto Account"), new PersonID(new Email("xpto@gmail.com")));
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"), new PersonID(new Email("xyz@gmail.com")));
        AccountInMemoryRepository accountsList = new AccountInMemoryRepository();

        //Act
        accountsList.createAccount(new Denomination("xpto"),
                new Description("xpto Account"), new PersonID(new Email("xpto@gmail.com")));
        boolean validateIfAccountIsInTheAccountsList = accountsList.isIDOnRepository(otherAccount.getID());

        //Arrange
        assertFalse(validateIfAccountIsInTheAccountsList);
    }

    /**
     * AccountsList.toString test
     */

    // Result order cant be predicted since accountsRepository has an HashSet of accounts
    @Test
    @DisplayName("test if an accountList can be put into a string")
    void toStringOfAccountsRepositoryTest() {

        //Arrange:
        AccountInMemoryRepository testAccountsList = new AccountInMemoryRepository();

        //Act:
        testAccountsList.createAccount(new Denomination("test account 1"),
                new Description("account for test purposes"), new PersonID(new Email("test@gmail.com")));
        testAccountsList.createAccount(new Denomination("test account 2"),
                new Description("account for test purposes"), new PersonID(new Email("test2@gmail.com")));
        String result = testAccountsList.toString();

        //Assert:
        assertEquals("Accounts Repository: [ACCOUNT FOR TEST PURPOSES, 0.0 EUR€, TEST ACCOUNT 1, test@gmail.com, ACCOUNT FOR TEST PURPOSES, 0.0 EUR€, TEST ACCOUNT 2, test2@gmail.com]", result);
    }


    /**
     * Tests to method findByID
     */

    @Test
    @DisplayName("Test if Account is return when asked by ID - true")
    void findAccountByID() {
        //Arrange
        AccountInMemoryRepository accountsRepository = new AccountInMemoryRepository();
        Account accountExpected = new Account(new Denomination("xpto"), new Description("xpto"),
                new PersonID(new Email("amadeu1@gmail.com")));
        accountsRepository.createAccount(new Denomination("xpto"),
                new Description("xpto Account"), new PersonID(new Email("amadeu1@gmail.com")));

        //Act
        Account accountReturned = accountsRepository.getByID(new AccountID(new Denomination("xpto"),
                new PersonID(new Email("amadeu1@gmail.com"))));

        //Arrange
        assertEquals(accountExpected, accountReturned);
    }

    @Test
    @DisplayName("Test if Account is return when asked by ID - false")
    void findAccountByIDFalse() {
        //Arrange
        AccountInMemoryRepository accountsRepository = new AccountInMemoryRepository();
        Account accountExpected = new Account(new Denomination("xpto"), new Description("xpto"),
                new PersonID(new Email("lol@gmail.com")));
        accountsRepository.createAccount(new Denomination("xpto"),
                new Description("xpto Account"), new PersonID(new Email("amadeu1@gmail.com")));

        //Act
        Account accountReturned = accountsRepository.getByID(new AccountID(new Denomination("xpto"),
                new PersonID(new Email("amadeu1@gmail.com"))));

        //Arrange
        assertNotEquals(accountExpected, accountReturned);
    }


    /**
     * Tests to method returnAccountsByOwnerID
     */

    @Test
    @DisplayName("Test if Accounts are returned by OwnerID - success case")
    void returnAccountsByOwnerIDOneAccount() {
        //Arrange
        Account cgdAccount = new Account(new Denomination("CGD"),
                new Description("CGD"), new PersonID(new Email("amadeu1@gmail.com")));

        //Expected List of Accounts
        Set<Account> expected = new HashSet<>(Arrays.asList(cgdAccount));

        //Act
        Set<Account> real = accountInMemoryRepository.returnAccountsByOwnerID(new PersonID(new Email("amadeu1@gmail.com")));

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Test if Accounts are returned by OwnerID - success case")
    void returnAccountsByOwnerIDSeveralAccount() {
        //Arrange
        Account bpiAccount = new Account(new Denomination("BPI"),
                new Description("BPI"), new PersonID(new Email("amadeu2@gmail.com")));
        Account bicAccount = new Account(new Denomination("BIC"),
                new Description("BIC"), new PersonID(new Email("amadeu2@gmail.com")));

        //Expected List of Accounts
        Set<Account> expected = new HashSet<>(Arrays.asList(bpiAccount, bicAccount));

        //Act
        Set<Account> real = accountInMemoryRepository.returnAccountsByOwnerID(new PersonID(new Email("amadeu2@gmail.com")));

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Test if Accounts are returned by OwnerID - owner id not exists")
    void returnAccountsByOwnerIDDontExistException() {
        //Arrange
        PersonID fakeID = (new PersonID(new Email("amadeu5@gmail.com")));
        try {
            Set<Account> real = accountInMemoryRepository.returnAccountsByOwnerID(fakeID);
        } catch (IllegalArgumentException ex) {
            assertEquals("No account found with that ID.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Test if Accounts are returned by OwnerID - null ID")
    void returnAccountsByOwnerIDNullException() {
        //Arrange
        try {
            Set<Account> real = accountInMemoryRepository.returnAccountsByOwnerID(null);
        } catch (IllegalArgumentException ex) {
            assertEquals("Owner ID can't be null.", ex.getMessage());
        }
    }


    /**
     * Tests to method findByID
     */

    @Test
    @DisplayName("Find Category by ID- success case")
    void findCategoryByID() {
        //Arrange
        CategoryInMemoryRepository categoryInMemoryRepository = new CategoryInMemoryRepository();
        Category expected = new Category(new Denomination("Mello"),
                new PersonID(new Email("miu@gmail.com")));
        categoryInMemoryRepository.createCategory(new Denomination("Mello"),
                new PersonID(new Email("miu@gmail.com")));

        //Act
        Category real = categoryInMemoryRepository.getByID(new CategoryID(new Denomination("Mello"),
                new PersonID(new Email("miu@gmail.com"))));

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Find Category by ID -ID not found")
    void findCategoryByIDNotFound() {
        //Arrange
        CategoryInMemoryRepository categoryInMemoryRepository = new CategoryInMemoryRepository();
        Category expected = new Category(new Denomination("Mello"),
                new PersonID(new Email("miu@gmail.com")));
        categoryInMemoryRepository.createCategory(new Denomination("Mello"),
                new PersonID(new Email("miu@gmail.com")));
        try {
            Category real = categoryInMemoryRepository.getByID(new CategoryID(new Denomination("Millo"),
                    new PersonID(new Email("miu@gmail.com"))));
        } catch (IllegalArgumentException ex) {
            assertEquals("No category found with that ID.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Test if Account is return when asked by ID - exception")
    void findAccountByIDException() {
        //Arrange
        AccountInMemoryRepository accountsRepository = new AccountInMemoryRepository();
        Account accountExpected = new Account(new Denomination("xpto"), new Description("xpto"),
                new PersonID(new Email("lol@gmail.com")));
        accountsRepository.createAccount(new Denomination("xpto"),
                new Description("xpto Account"), new PersonID(new Email("amadeu1@gmail.com")));

        try {
            Account accountReturned = accountsRepository.getByID(new AccountID(new Denomination("xpto"),
                    new PersonID(new Email("notfound@gmail.com"))));
        } catch (IllegalArgumentException ex) {
            assertEquals("No account found with that ID.", ex.getMessage());
        }
    }


}
