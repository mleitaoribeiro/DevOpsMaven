package switch2019.project.controllerLayer.rest.unit;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switch2019.project.DTO.deserializationDTO.CreateTransactionInfoDTO;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonalTransactionDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.assemblers.LedgerDTOAssembler;
import switch2019.project.controllerLayer.rest.US008CreateTransactionControllerRest;

import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class US008CreateTransactionControllerRestUnitTest {

    @Mock
    private US008CreateTransactionService service;

    @InjectMocks
    private US008CreateTransactionControllerRest controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test create Personal transaction
     */

    @Test
    @DisplayName("Test create personal transactions - Main scenario - Happy Case")
    public void createPersonTransactionHappyCase()  {
        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";
        long id = 9;

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(category);
        createTransactionInfoDTO.setDescription(description);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreatePersonalTransactionDTO createPersonalTransactionDTO = LedgerDTOAssembler.transformToCreatePersonalTransactionDTO(personId, createTransactionInfoDTO);

        TransactionShortDTO transactionShortDTOExpected = new TransactionShortDTO(amount, Currency.getInstance(currency), description, accountFrom, accountTo, id);

        ResponseEntity <TransactionShortDTO> responseEntityExpected = new ResponseEntity<>(transactionShortDTOExpected, HttpStatus.CREATED);

        //Act
        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenReturn(transactionShortDTOExpected);

        ResponseEntity<TransactionShortDTO> responseEntityResult = controller.createPersonTransaction(personId, createTransactionInfoDTO);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(responseEntityExpected, responseEntityResult),
                () -> assertEquals(HttpStatus.CREATED, responseEntityResult.getStatusCode())
        );
    }

    /**
     * Test create Group transactions
     */


    /**
     * Test get transactions by id
     */



    /**
     * Test get transactions by owner id
     */
    @Test
    @DisplayName("Test get all transactions from Personal Ledger - Main scenario")
    public void getPersonalTransactionsByLedgerIdSuccess()  {
        //Arrange
        String personId = "1191780@isep.ipp.pt";

        List<TransactionShortDTO> expectedTransactions = new LinkedList<>();
        expectedTransactions.add(new TransactionShortDTO(20.0, Currency.getInstance("EUR"), "MOEY", "FITNESSUP", "DEBIT", 3L));
        expectedTransactions.add(new TransactionShortDTO(150.0, Currency.getInstance("EUR"), "MOEY", "DECATLHON", "DEBIT", 4L));

        ResponseEntity<Object> responseEntityExpected = new ResponseEntity<>(expectedTransactions, HttpStatus.OK);

        //Act
        Mockito.when(service.getTransactionsByLedgerId(personId)).thenReturn(expectedTransactions);

        ResponseEntity<Object> responseEntityResult = controller.getPersonTransactionsByLedgerId(personId);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(responseEntityExpected, responseEntityResult),
                () -> assertEquals(HttpStatus.OK, responseEntityResult.getStatusCode())
        );
    }

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - No Transactions")
    public void getPersonalTransactionsByLedgerIdNoTransactions()  {
        //Arrange
        String personId = "maria@gmail.com";

        List<TransactionShortDTO> expectedTransactions = new LinkedList<>();
        ResponseEntity<Object> responseEntityExpected = new ResponseEntity<>(expectedTransactions, HttpStatus.OK);

        Mockito.when(service.getTransactionsByLedgerId(personId)).thenReturn(expectedTransactions);

        //Act
        ResponseEntity<Object> responseEntityResult = controller.getPersonTransactionsByLedgerId(personId);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(responseEntityExpected, responseEntityResult),
                () -> assertEquals(HttpStatus.OK, responseEntityResult.getStatusCode())
        );
    }

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - Invalid id")
    public void getPersonalTransactionsByLedgerIdSuccessInvalidId()   {
        //Arrange
        String personId = "nobody@gmail.com";

        Mockito.when(service.getTransactionsByLedgerId(personId)).
                thenThrow(new IllegalArgumentException("No Ledger found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.getPersonTransactionsByLedgerId(personId);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No Ledger found with that ID.");
    }

    /**
     * Test getTransactionsByLedgerID - Group Ledger
     */

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - Main scenario")
    public void getGroupTransactionsByLedgerIdSuccess() {
        //Arrange
        String personId = "FAMILY CARDOSO";

        List<TransactionShortDTO> expectedTransactions = new LinkedList<>();
        expectedTransactions.add(new TransactionShortDTO(50.0, Currency.getInstance("EUR"), "REVOLUT", "NETFLIX", "DEBIT", 5L));

        ResponseEntity<Object> responseEntityExpected = new ResponseEntity<>(expectedTransactions, HttpStatus.OK);

        //Act
        Mockito.when(service.getTransactionsByLedgerId(personId)).thenReturn(expectedTransactions);

        ResponseEntity<Object> responseEntityResult = controller.getPersonTransactionsByLedgerId(personId);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(responseEntityExpected, responseEntityResult),
                () -> assertEquals(HttpStatus.OK, responseEntityResult.getStatusCode())
        );
    }

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - No Transactions")
    public void getGroupTransactionsByLedgerIdNoTransactions() {
        //Arrange
        String personId = "FRIENDS";

        List<TransactionShortDTO> expectedTransactions = new LinkedList<>();
        ResponseEntity<Object> responseEntityExpected = new ResponseEntity<>(expectedTransactions, HttpStatus.OK);

        Mockito.when(service.getTransactionsByLedgerId(personId)).thenReturn(expectedTransactions);

        //Act
        ResponseEntity<Object> responseEntityResult = controller.getPersonTransactionsByLedgerId(personId);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(responseEntityExpected, responseEntityResult),
                () -> assertEquals(HttpStatus.OK, responseEntityResult.getStatusCode())
        );
    }

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - Invalid id")
    public void getGroupTransactionsByLedgerIdSuccessInvalidId()  {
        //Arrange
        String groupID = "NO GROUP";

        Mockito.when(service.getTransactionsByLedgerId(groupID)).
                thenThrow(new IllegalArgumentException("No Ledger found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.getPersonTransactionsByLedgerId(groupID);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No Ledger found with that ID.");
    }
}


