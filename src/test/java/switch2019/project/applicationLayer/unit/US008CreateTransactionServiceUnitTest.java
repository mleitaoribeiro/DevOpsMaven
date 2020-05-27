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
import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.dataModel.dataAssemblers.TransactionDomainDataAssembler;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.LedgerRepository;
import switch2019.project.infrastructure.dataBaseRepositories.AccountDbRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;

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

        TransactionShortDTO transactionDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR"),
                "GOLD CARD", "IKEA", "CREDIT", 1L);

        TransactionShortDTO transactionDTO1 = new TransactionShortDTO(50.0,  Currency.getInstance("EUR"),
                "MASTERCARD", "KWIK E MART", "CREDIT", 1L);

        List<TransactionShortDTO> expected = Arrays.asList(transactionDTO, transactionDTO1);

        Transaction transaction = new Transaction(new MonetaryValue(100.00,
                Currency.getInstance("EUR")), new Description("Bought a cheap sofa"), new DateAndTime(2020,
                2, 14, 11, 24), new CategoryID(new Denomination("house"), personID),
                new AccountID(new Denomination("gold card"), personID), new AccountID(new Denomination("ikea"),
                personID), new Type(true));

        Transaction transaction1 = new Transaction(new MonetaryValue(50.00, Currency.getInstance("EUR")),
                new Description("Grocery for baking cookies"), new DateAndTime(2020,
                3, 20, 13, 4), new CategoryID(new Denomination("house"), personID),
                new AccountID(new Denomination("mastercard"), personID), new AccountID(new Denomination("kwik e mart"),
                personID), new Type(true));


        List<Transaction> transactions = Arrays.asList(transaction, transaction1);


        Mockito.when(ledgerRepository.findAllTransactionsByLedgerID(email)).
                thenReturn(transactions);

        //Act
        List<TransactionShortDTO> result = service.getTransactionsByLedgerId(email);

        //Assert
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Get Transactions By ledgerID - empty ledger")
    void getTransactionsByLedgerIdEmptyLedger() {

        //Arrange
        String email = "bart.simpson@gmail.com";

        List <Transaction> transactions = Collections.emptyList();

        List <TransactionShortDTO> expected = Collections.emptyList();

        Mockito.when(ledgerRepository.findAllTransactionsByLedgerID(email)).
                thenReturn(transactions);

        //Act
        List<TransactionShortDTO> result = service.getTransactionsByLedgerId(email);

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
            service.getTransactionsByLedgerId(personID.toString());
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No Ledger found with that ID.");
    }

    /**
     * Tests for the method: getTransactionByID
     */
/*
   @Test
    @DisplayName("Get Transaction By ID - PersonLedger - happy case")
    void getTransactionByIDPersonLedgerHappyCase() {

       //Arrange
       String email = "marge@hotmail.com";
       Long id = 2L;

       PersonID personID = new PersonID(new Email(email));

       Category category= new Category (new Denomination ("HOUSE"), personID );

       Account accountFrom = new Account (new Denomination("MASTERCARD"),
               new Description("For daily expenses"), personID);
       Account accountTo = new Account(new Denomination("Kwik-E-Mart"),
               new Description("Food and Grocery") , personID);

       Transaction expectedTransaction = new Transaction(new MonetaryValue(50.0, Currency.getInstance("EUR")),
               new Description("GROCERY FOR BAKING COOKIES"),
               new DateAndTime(2020,03,20,13,04),
               category.getID(), accountFrom.getID(), accountTo.getID(), new Type(false));


        Mockito.when(ledgerRepository.getTransactionByID(email,id)).
                thenReturn(expectedTransaction);


        TransactionDTO transactionDTOexpected = new TransactionDTO
                (50.0, Currency.getInstance("EUR"), "GROCERY FOR BAKING COOKIES",
                        "2020-03-20 13:04", "HOUSE",
                        "MASTERCARD", "KWIK E MART", "DEBIT");

        //Act
        TransactionDTO result = service.getTransactionByID(email, id);

        //Assert
        assertEquals(transactionDTOexpected, result);
        }*/



}

