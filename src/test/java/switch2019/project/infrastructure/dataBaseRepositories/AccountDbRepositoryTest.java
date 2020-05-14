package switch2019.project.infrastructure.dataBaseRepositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.Assert;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

class AccountDbRepositoryTest {

    @Autowired
    AccountDbRepository repository;

    private Account cdg;
    private Account bpi;
    private Account bic;
    private Account san;

    @BeforeEach
    public void universeSetUp() {
        cdg = repository.createAccount(new Denomination("CGD"),
                new Description("CGD"), new PersonID(new Email("amadeu@gmail.com")));
        bpi = repository.createAccount(new Denomination("BPI"),
                new Description("BPI"), new PersonID(new Email("amadeu@gmail.com")));
        bic = repository.createAccount(new Denomination("BIC"),
                new Description("BIC"), new PersonID(new Email("amadeu@gmail.com")));
        san = repository.createAccount(new Denomination("SAN"),
                new Description("SAN"), new PersonID(new Email("amadeu@gmail.com")));
    }

    @Test
    @DisplayName("Test if one account was added to the repository with Person ID - Main Scenario")
    void createAccount() {
        //Arrange
        Account accountExpected = new Account(new Denomination("Revolut"),
                new Description("Online Expenses"), new PersonID((new Email("martacarda@hotmail.com"))));
        Long expectedSize = repository.repositorySize() +1;

        //Act
        Account accountCreated = repository.createAccount(new Denomination("Revolut"),
                new Description("Online Expenses"), new PersonID(new Email("martacarda@hotmail.com")));

        //Assert
        Assertions.assertAll(
                () -> Assert.notNull(repository.getByID(accountCreated.getID()), "Account saved is found"),
                () -> assertEquals(expectedSize, repository.repositorySize()),
                () -> assertEquals(accountExpected, accountCreated)
        );
    }

    @Test
    @DisplayName("Test if one account was added to the repository with Person ID - Exception")
    void createAccountException() {
        //Arrange
        repository.createAccount(new Denomination("Revolut"),
                new Description("Online Expenses"), new PersonID((new Email("martacarda@hotmail.com"))));
        //Act
        Throwable thrown = catchThrowable(() -> {  repository.createAccount(new Denomination("Revolut"),
                new Description("Online Expenses"), new PersonID((new Email("martacarda@hotmail.com"))));;});

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("This account already exists.");
    }

    @Test
    @DisplayName("Return account by owner ID - sucess")
    void returnAccountsByOwnerID() {
        //Arrange
        Set<Account> expected = new HashSet<>(Arrays.asList(cdg, bpi, bic, san));

        //Act
        Set<Account> real = repository.returnAccountsByOwnerID(new PersonID(new Email("amadeu@gmail.com")));

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Return accounts by Owner ID - no accounts")
    void returnAccountsByOwnerIDNoAccountsException() throws Exception{
        //Act
        Throwable thrown = catchThrowable(() -> { repository.returnAccountsByOwnerID(new PersonID(new Email("mimi@gmail.com")));});

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");
    }

    @Test
    @DisplayName("Return accounts by Owner ID - owner id null")
    void returnAccountsByOwnerIDNullOwnerIDExcetpion() throws Exception{
        //Act
        Throwable thrown = catchThrowable(() -> { repository.returnAccountsByOwnerID(null);});

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Owner ID can't be null.");
    }

    @Test
    @DisplayName("Test if one account is removed - main scenario")
    void removeAccount() {
        //Arrange
        Long expectedSize = repository.repositorySize() -1;

        //Act
       boolean result = repository.removeAccount(cdg);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedSize, repository.repositorySize()),
                () -> assertTrue(result)
        );
    }

    @Test
    @DisplayName("Test if one account is removed - exception scenario")
    void removeAccountException() {
        //Arrange
        Account account = new Account(new Denomination("MOEY"),
                new Description("MOEY"), new PersonID(new Email("amadeu@gmail.com")));
        //Act
        Throwable thrown = catchThrowable(() -> { repository.removeAccount(account);});

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");
    }

    @Test
    @DisplayName("Return account from jpaRepository - main scenario")
    void getByID() {
        //Arrange
        Account expected = san;

        //Act
        Account result = repository.getByID(san.getID());

        //Assert
        Assertions.assertAll(
                () -> Assert.notNull(result, "Account is returned"),
                () -> assertEquals(expected, result)
        );
    }
    @Test
    @DisplayName("Return account from jpaRepository - exception")
    void getByIDException() {
        //Arrange
        Account account = new Account(new Denomination("MOEY"),
                new Description("MOEY"), new PersonID(new Email("amadeu@gmail.com")));
        //Act
        Throwable thrown = catchThrowable(() -> { repository.getByID(account.getID());});

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");
    }

    @Test
    @DisplayName("Verify if ID is on jpaRepository - main scenario")
    void isIDOnRepository() {
        //Assert
        assertTrue(repository.isIDOnRepository(san.getID()));
    }

    @Test
    @DisplayName("Verify if ID is on jpaRepository - false")
    void isIDOnRepositoryFalse() {
        //Arrange
        Account account = new Account(new Denomination("MOEY"),
                new Description("MOEY"), new PersonID(new Email("amadeu@gmail.com")));

        //Assert
        assertFalse(repository.isIDOnRepository(account.getID()));
    }
}