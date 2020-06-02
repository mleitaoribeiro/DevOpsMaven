package switch2019.project.infrastructure.inMemoryRepositories;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.AccountRepository;
import switch2019.project.domain.repositories.CategoryRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountInMemoryRepositoryTest {

    private AccountRepository accountRepository;

    @BeforeEach
    void universeSetUp() {
        accountRepository = new AccountInMemoryRepository();
        accountRepository.createAccount(new Denomination("CGD"),
                new Description("CGD"), new PersonID(new Email("amadeu1@gmail.com")));
        accountRepository.createAccount(new Denomination("BPI"),
                new Description("BPI"), new PersonID(new Email("amadeu2@gmail.com")));
        accountRepository.createAccount(new Denomination("BIC"),
                new Description("BIC"), new PersonID(new Email("amadeu2@gmail.com")));
        accountRepository.createAccount(new Denomination("SAN"),
                new Description("SAN"), new PersonID(new Email("amadeu3@gmail.com")));
    }


    /**
     * Test if accounts was added to the list
     */

    @Test
    @DisplayName("Test if one account was added to the repository with Person ID - Main Scenario")
    public void testIfAccountsWasAddedToTheRepositoryWithPersonID() {
        //Arrange
        Account accountExpected = new Account(new Denomination("Revolut"),
                new Description("Online Expenses"), new PersonID((new Email("martacarda@hotmail.com"))));

        //Act
        Account accountCreated = accountRepository.createAccount(new Denomination("Revolut"),
                new Description("Online Expenses"), new PersonID(new Email("martacarda@hotmail.com")));

        //Assert
        assertEquals(accountExpected, accountCreated);
    }

    @Test
    @DisplayName("Test if  one account was added to the repository with null Person ID")
    public void testIfAccountsWasAddedToTheRepositoryWithNullPersonID() {
        //Act
        try {
            accountRepository.createAccount(new Denomination("Revolut"),
                    new Description("Online Expenses"), new PersonID(null));
        }
        //Assert
        catch (IllegalArgumentException email) {
            assertEquals("The email can't be null.", email.getMessage());
        }
    }

    @Test
    @DisplayName("Test if more than one account was added to the repository with Person ID ")
    public void testIfAccountsWereAddedToTheRepositoryWithPersonID() {
        //Arrange
        Account accountExpected = new Account(new Denomination("xpto"),
                new Description("one account"), new PersonID(new Email("martacarda@hotmail.com")));
        Account accountExpected2 = new Account(new Denomination("xyz"),
                new Description("general"), new PersonID(new Email("mariasousa@gmail.com")));
        Account accountExpected3 = new Account(new Denomination("Millennium"),
                new Description("Millennium Account"), new PersonID(new Email("antoniomagalhaes@isep.ipp.pt")));


        //Act
        Account accountCreated = accountRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new PersonID(new Email("martacarda@hotmail.com")));
        Account accountCreated2 = accountRepository.createAccount(new Denomination("xyz"),
                new Description("general"), new PersonID(new Email("mariasousa@gmail.com")));
        Account accountCreated3 = accountRepository.createAccount(new Denomination("Millennium"),
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
        //Act
        try {
            accountRepository.createAccount(new Denomination("CGD"),
                    new Description("CGD"), new PersonID(new Email("amadeu1@gmail.com")));
        } catch (ResourceAlreadyExistsException ex) {
            assertEquals("This account already exists.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Test if one account was created if it already exist -  Group ID")
    public void testIfAccountWasAddedIfItAlreadyExistsGroupID() {
        //Arrange
        accountRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new GroupID(new Description("Friends")));

        //Act
        try {
            accountRepository.createAccount(new Denomination("xpto"),
                    new Description("one account"), new GroupID(new Description("Friends")));
        } catch (ResourceAlreadyExistsException ex) {
            assertEquals("This account already exists.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Test if  one account was added to the repository with Group ID - Main Scenario")
    public void testIfAccountsWasAddedToTheRepositoryWithGroupID() {
        //Arrange
        Account accountExpected = new Account(new Denomination("Revolut"),
                new Description("Online Expenses"), new GroupID((new Description("Random Group"))));

        //Act
        Account accountCreated = accountRepository.createAccount(new Denomination("Revolut"),
                new Description("Online Expenses"), new GroupID(new Description("Random Group")));

        //Assert
        assertEquals(accountExpected, accountCreated);
    }

    @Test
    @DisplayName("Test if  one account was added to the repository with null Group ID")
    public void testIfAccountsWasAddedToTheRepositoryWithNullGroupID() {
        //Act
        try {
            accountRepository.createAccount(new Denomination("xpto"),
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
        Account accountExpected = new Account(new Denomination("xpto"),
                new Description("one account"), new GroupID(new Description("familia")));
        Account accountExpected2 = new Account(new Denomination("xyz"),
                new Description("general"), new GroupID(new Description("copos")));
        Account accountExpected3 = new Account(new Denomination("Millennium"),
                new Description("Millennium Account"), new GroupID(new Description("andorinhas")));


        //Act
        Account accountCreated = accountRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new GroupID(new Description("familia")));
        Account accountCreated2 = accountRepository.createAccount(new Denomination("xyz"),
                new Description("general"), new GroupID(new Description ("copos")));
        Account accountCreated3 = accountRepository.createAccount(new Denomination("Millennium"),
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

        //Act
        try {
            accountRepository.createAccount(null, new Description("XPTO"), new PersonID(new Email("12345@isep.ipp.pt")));

        }
        //Assert
        catch (IllegalArgumentException denomination) {
            assertEquals("Neither the Denomination nor OwnerID can be null.", denomination.getMessage());
        }
    }

    @Test
    @DisplayName("Test if more than one account was added to the repository PersonID - one Account Denomination Is Null")
    public void testIfAccountIsAddedToTheRepositoryOneAccountDescriptionIsNull() {

        //Act
        try {
            accountRepository.createAccount(new Denomination("xpto"), null, new PersonID(new Email("miguelito@outlook.pt")));

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
        AccountRepository accountRepository = new AccountInMemoryRepository();
        //Act
        accountRepository.createAccount(new Denomination("xpto"),
                new Description("one account"), new PersonID(new Email("martacarda@live.pt.pt")));
        accountRepository.createAccount(new Denomination("xyz"),
                new Description("other Account"), new GroupID(new Description("Business")));
        long result = accountRepository.repositorySize();

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

        //Act
        accountRepository.createAccount(new Denomination("xyz"),
                new Description("general"), new PersonID(new Email("random.letters@123.com")));

        boolean real = !accountRepository.isIDOnRepository(oneAccount.getID())
                && accountRepository.isIDOnRepository(otherAccount.getID());

        //Assert
        assertTrue(real);
    }

    @Test
    @DisplayName("Test if accounts all accounts are in the repository")
    public void testIfAccountsAreInRepositoryMoreThanOne() {
        //Arrange
        Account oneAccount = new Account(new Denomination("CGD"),
                new Description("CGD"), new PersonID(new Email("amadeu1@gmail.com")));
        Account otherAccount = new Account(new Denomination("BPI"),
                new Description("BPI"), new PersonID(new Email("amadeu2@gmail.com")));
        Account anotherAccount = new Account(new Denomination("SAN"),
                new Description("SAN"), new PersonID(new Email("amadeu3@gmail.com")));

        //Act
        boolean real = accountRepository.isIDOnRepository(oneAccount.getID())
                && accountRepository.isIDOnRepository(otherAccount.getID())
                && accountRepository.isIDOnRepository(anotherAccount.getID());

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
        Account oneAccount = new Account(new Denomination("SAN"),
                new Description("SAN"), new PersonID(new Email("amadeu3@gmail.com")));
        //Act
        boolean expected = accountRepository.isIDOnRepository(oneAccount.getID());

        //Assert
        assertTrue(expected);
    }

    @Test
    @DisplayName("Test if one account is contained in the accounts Repository | False")
    public void testIfAccountsRepositoryContainAccountFalse() {
        //Arrange
        Account oneAccount = new Account(new Denomination("xpto"),
                new Description("cat acccount"), new PersonID(new Email("mocho@gmail.com")));

        //Act
        boolean notContained = accountRepository.isIDOnRepository(oneAccount.getID());

        //Assert
        assertFalse(notContained);
    }

    @Test
    @DisplayName("Test if an account was removed from an accounts Repository")
    public void testIfOneAccountWasRemoved() {
        //Arrange
        Account cgd = new Account(new Denomination("CGD"),
                new Description("CGD"), new PersonID(new Email("amadeu1@gmail.com")));

        accountRepository.removeAccount(cgd);

        //Assert
        assertEquals(3, accountRepository.repositorySize());

    }

    @Test
    @DisplayName("Test if an account was removed from an accounts repository - not in the repository")
    public void testIfOneAccountWasRemovedNotInTheRepository() {
        //Arrange
        Account post = new Account(new Denomination("Post"), new Description("Correios do Amadeu"),
                new PersonID(new Email("amadeu1@gmail.com")));
        //Act
        accountRepository.removeAccount(post);

        // Assert
        assertEquals(4, accountRepository.repositorySize());
    }

    @Test
    @DisplayName("Test if an account was removed from an accounts Repository")
    public void testIfOneAccountWasRemovedTrue() {
        //Arrange
        Account cgd = new Account(new Denomination("CGD"),
                new Description("CGD"), new PersonID(new Email("amadeu1@gmail.com")));

        boolean result = accountRepository.removeAccount(cgd);

        //Assert
        assertTrue(result);

    }



    @Test
    @DisplayName("Test if an account was removed from an accounts repository - Account Null")
    public void testIfOneAccountWasRemovedNull() {
        //Arrange
        Account oneAccount = null;
        //Act
        boolean real = accountRepository.removeAccount(oneAccount);

        //Assert
        assertFalse(real);

    }


    /**
     * Test if account is in the repository
     */

    @Test
    @DisplayName("Test if account is in the Repository-True")
    void validateIfAccountIsInTheAccountsRepository() {
        //Arrange
        Account oneAccount = new Account(new Denomination("CGD"),
        new Description("CGD"), new PersonID(new Email("amadeu1@gmail.com")));

        //Act
        boolean validateIfAccountIsInTheAccountsList = accountRepository.isIDOnRepository(oneAccount.getID());

        //Arrange
        assertTrue(validateIfAccountIsInTheAccountsList);
    }

    @Test
    @DisplayName("Test if account is in the Repository - False")
    void validateIfAccountIsInTheAccountsRepositoryFalse() {
        //Arrange
        Account otherAccount = new Account(new Denomination("xyz"),
                new Description("xyz Account"), new PersonID(new Email("xyz@gmail.com")));
        //Act
        boolean validateIfAccountIsInTheAccountsList = accountRepository.isIDOnRepository(otherAccount.getID());

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
        AccountRepository testAccountsList = new AccountInMemoryRepository();

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
        Account accountExpected = new Account(new Denomination("CGD"),
                new Description("CGD"), new PersonID(new Email("amadeu1@gmail.com")));

        //Act
        Account accountReturned = accountRepository.getByID(new AccountID(new Denomination("CGD"),
                new PersonID(new Email("amadeu1@gmail.com"))));

        //Arrange
        assertEquals(accountExpected, accountReturned);
    }

    @Test
    @DisplayName("Test if Account is return when asked by ID - false")
    void findAccountByIDFalse() {
        //Arrange
        Account accountExpected = new Account(new Denomination("CGD"),
        new Description("CGD"), new PersonID(new Email("amadeu1@gmail.com")));


        //Act
        Account accountReturned = accountRepository.getByID(new AccountID(new Denomination("BIC"),
                new PersonID(new Email("amadeu2@gmail.com"))));

        //Arrange
        assertNotEquals(accountExpected, accountReturned);
    }


    /**
     * Tests to method returnAccountsByOwnerID
     */

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
        Set<Account> real = accountRepository.returnAccountsByOwnerID(new PersonID(new Email("amadeu2@gmail.com")));

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Test if Accounts are returned by OwnerID - owner id not exists")
    void returnAccountsByOwnerIDDontExistException() {
        //Arrange
        PersonID fakeID = (new PersonID(new Email("amadeu5@gmail.com")));
        try {
            Set<Account> real = accountRepository.returnAccountsByOwnerID(fakeID);
        } catch (ArgumentNotFoundException ex) {
            assertEquals("No accounts found with that ID.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Test if Accounts are returned by OwnerID - null ID")
    void returnAccountsByOwnerIDNullException() {
        //Arrange
        try {
            Set<Account> real = accountRepository.returnAccountsByOwnerID(null);
        } catch (IllegalArgumentException ex) {
            assertEquals("Owner ID can't be null.", ex.getMessage());
        }
    }



    @Test
    @DisplayName("Test if Account is return when asked by ID - exception")
    void findAccountByIDException() {
        try {
            accountRepository.getByID(new AccountID(new Denomination("xpto"),
                    new PersonID(new Email("notfound@gmail.com"))));
        } catch (ArgumentNotFoundException ex) {
            assertEquals("No account found with that ID.", ex.getMessage());
        }
    }


}
