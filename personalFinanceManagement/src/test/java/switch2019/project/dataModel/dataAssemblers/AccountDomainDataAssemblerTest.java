package switch2019.project.dataModel.dataAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.dataModel.entities.AccountJpa;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountDomainDataAssemblerTest {

    @Test
    @DisplayName("Testing toData method in AccountDomainDataAssembler")
    public void toDataTest() {
        //Arrange:
        //Arrange parameters:
        String personEmail = "teste@gmail.com";

        String denomination = "GAMES";

        String description = "COMPUTER AND BOARD GAMES";

        //Arrange Account:
        Account account = new Account(new Denomination("Games"),
                new Description("Computer and board games"),
                new PersonID(new Email("teste@gmail.com")));

        //Arrange AccountJpa (expected):
        AccountJpa accountJpaExpected = new AccountJpa(personEmail, denomination, description, 0.0, "EUR");

        //Act:
        //Arrange Account Jpa (actual):
        AccountJpa accountJpaActual = AccountDomainDataAssembler.toData(account);

        //Assert:
        assertEquals(accountJpaExpected,accountJpaActual);
    }

    @Test
    @DisplayName("Testing toDomain method in AccountDomainDataAssembler - Person Owner")
    public void toDomainPersonTest() {
        //Arrange:
        //Arrange parameters:
        String personEmail = "teste@gmail.com";

        String denomination = "GAMES";

        String description = "COMPUTER AND BOARD GAMES";

        //Arrange AccountJpa:
        AccountJpa accountJpa= new AccountJpa(personEmail, denomination, description, 0.0, "EUR");

        //Arrange Account (expected):
        Account accountExpected = new Account(new Denomination(denomination), new Description(description), new PersonID(new Email(personEmail)));

        //Act:
        //Obtain Account (actual):
        Account accountActual = AccountDomainDataAssembler.toDomain(accountJpa);

        //Assert:
        assertEquals(accountExpected,accountActual);
    }

    @Test
    @DisplayName("Testing toDomain method in AccountDomainDataAssembler - Group Owner")
    public void toDomainGroupTest() {
        //Arrange:
        //Arrange parameters:
        String groupDescription = "FRIENDS";

        String denomination = "GAMES";

        String description = "COMPUTER AND BOARD GAMES";

        //Arrange AccountJpa:
        AccountJpa accountJpa= new AccountJpa(groupDescription, denomination, description, 0.0, "EUR");

        //Arrange Account (expected):
        Account accountExpected = new Account(new Denomination(denomination), new Description(description), new GroupID(new Description(groupDescription)));

        //Act:
        //Obtain Account (actual):
        Account accountActual = AccountDomainDataAssembler.toDomain(accountJpa);

        //Assert:
        assertEquals(accountExpected,accountActual);
    }

    @Test
    @DisplayName("Testing toDomain method in AccountDomainDataAssembler - Account already created and with a given balance")
    public void toDomainGroupBalanceTest() {
        //Arrange:
        //Arrange parameters:
        String groupDescription = "FRIENDS";

        String denomination = "GAMES";

        String description = "COMPUTER AND BOARD GAMES";

        //Arrange AccountJpa:
        AccountJpa accountJpa= new AccountJpa(groupDescription, denomination, description, 5.0, "EUR");

        //Arrange Account (expected):
        Account accountExpected = new Account(new Denomination(denomination), new Description(description),
                new GroupID(new Description(groupDescription)),new MonetaryValue(5.0, Currency.getInstance("EUR")));

        //Act:
        //Obtain Account (actual):
        Account accountActual = AccountDomainDataAssembler.toDomain(accountJpa);

        //Assert:
        assertEquals(accountExpected,accountActual);
    }
}
