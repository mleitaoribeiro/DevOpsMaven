package switch2019.project.applicationLayer.unit;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.dataModel.dataAssemblers.TransactionDomainDataAssembler;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.infrastructure.dataBaseRepositories.AccountDbRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@SpringBootTest
public class US008CreateTransactionServiceUnitTest {


    @Mock
    private LedgerRepository ledgerRepository;

    @InjectMocks
    private US008CreateTransactionService service;


    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }


    /**
     * Test to get Transactions by LedgerId
     */

    @Test
    @DisplayName("Get Transactions By ledgerID - happy case")
    void getTransactionsByLedgerIdHappyCase() {

        //Arrange
        String email = "marge@hotmail.com";

        PersonID personID = new PersonID(new Email(email));

        TransactionDTO transactionDTO = new TransactionDTO("100.0 EUR", "Bought a cheap sofa".toUpperCase(),
                "2020-02-14 11:24", "HOUSE, marge@hotmail.com", "GOLD CARD, marge@hotmail.com",
                "IKEA, marge@hotmail.com", "DEBIT");

        TransactionDTO transactionDTO1 = new TransactionDTO("50.0 EUR", "Grocery for baking cookies".toUpperCase(),
                "2020-03-20 13:04", "HOUSE, marge@hotmail.com", "MASTERCARD, marge@hotmail.com",
                "KWIK E MART, marge@hotmail.com", "DEBIT");

        List<TransactionDTO> expected = Arrays.asList(transactionDTO, transactionDTO1);

        Transaction transaction = new Transaction(new MonetaryValue(100.00,
                Currency.getInstance("EUR")), new Description("Bought a cheap sofa"), new DateAndTime(2020,
                2, 14, 11, 24), new CategoryID(new Denomination("house"), personID),
                new AccountID(new Denomination("gold card"), personID), new AccountID(new Denomination("ikea"),
                personID), new Type(false));

        Transaction transaction1 = new Transaction(new MonetaryValue(50.00, Currency.getInstance("EUR")),
                new Description("Grocery for baking cookies"), new DateAndTime(2020,
                3, 20, 13, 4), new CategoryID(new Denomination("house"), personID),
                new AccountID(new Denomination("mastercard"), personID), new AccountID(new Denomination("kwik e mart"),
                personID), new Type(false));


        List<Transaction> transactions = Arrays.asList(transaction, transaction1);


        Mockito.when(ledgerRepository.findAllTransactionsByLedgerID(email)).
                thenReturn(transactions);

        //Act
        List<TransactionDTO> result = service.getTransactionsByLedgerId(new PersonID(
                new Email(email)));

        //Assert
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Get Transactions By ledgerID - empty ledger")
    void getTransactionsByLedgerIdEmptyLedger() {

        //Arrange
        String email = "bart.simpson@gmail.com";

        PersonID personID = new PersonID(new Email(email));

        List <Transaction> transactions = Collections.emptyList();

        List <TransactionDTO> expected = Collections.emptyList();

        Mockito.when(ledgerRepository.findAllTransactionsByLedgerID(email)).
                thenReturn(transactions);

        //Act
        List<TransactionDTO> result = service.getTransactionsByLedgerId(personID);

        //Assert
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Get Transactions By ledgerID - not found")
    void getTransactionsByLedgerIdException() {

        //Arrange
        String email = "pikachu@hotmail.com";

        PersonID personID = new PersonID(new Email(email));

        Mockito.when(ledgerRepository.findAllTransactionsByLedgerID(email)).
                thenThrow(new ArgumentNotFoundException("No Ledger found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            service.getTransactionsByLedgerId(personID);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");
    }



}
