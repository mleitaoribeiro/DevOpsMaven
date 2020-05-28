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
import switch2019.project.DTO.serviceDTO.CreateGroupTransactionDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonalTransactionDTO;
import switch2019.project.applicationLayer.US008CreateTransactionService;
import switch2019.project.assemblers.LedgerDTOAssembler;
import switch2019.project.controllerLayer.rest.US008CreateTransactionControllerRest;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

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
    public void createPersonTransactionHappyCase() {
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

        ResponseEntity<TransactionShortDTO> responseEntityExpected = new ResponseEntity<>(transactionShortDTOExpected, HttpStatus.CREATED);

        //Act
        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenReturn(transactionShortDTOExpected);

        ResponseEntity<TransactionShortDTO> responseEntityResult = controller.createPersonTransaction(personId, createTransactionInfoDTO);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(responseEntityExpected, responseEntityResult),
                () -> assertEquals(HttpStatus.CREATED, responseEntityResult.getStatusCode())
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Person does not exists on Person Repository")
    void createPersonTransactionPersonDoesNotExists() throws Exception {

        //Arrange
        String personId = "not_existing_person@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new ArgumentNotFoundException("No person found with that email."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No person found with that email.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Category Does Not Exists")
    void createPersonTransactionCategoryDoesNotExists() throws Exception {


        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "Not existing category";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new ArgumentNotFoundException("No category found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No category found with that ID.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Account From Does Not Exists")
    void createPersonTransactionAccountFromDoesNotExists() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "Not existing account";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new ArgumentNotFoundException("No account found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - AccountTo Does Not Exists")
    void createPersonTransactionAccountToDoesNotExists() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Not existing account";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new ArgumentNotFoundException("No account found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null Amount")
    void createPersonTransactionNullAmount() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = null;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new NullPointerException());

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null Currency")
    void createPersonTransactionNullCurrency() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = null;
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new NullPointerException());

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null date")
    void createPersonTransactionNullDate() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = null;
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new NullPointerException("text"));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage("text");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null Category")
    void createPersonTransactionNullCategory() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = null;
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null Description")
    void createPersonTransactionNullDescription() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = null;
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null AccountFrom")
    void createPersonTransactionNullAccountFrom() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = null;
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null AccountTo")
    void createPersonTransactionNullAccountTO() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = null;
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null type")
    void createPersonTransactionNullType() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = null;
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new NullPointerException());

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }

    @Test
    @DisplayName("Test Person Transaction creation - Invalid Email")
    void createPersonTransactionInvalidEmail() throws Exception {

        //Arrange
        String personId = "marge@@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException("The email is not valid."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The email is not valid.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Invalid Amount")
    void createPersonTransactionInvalidAmount() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = -10.2;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException("The monetary value cannot be negative."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The monetary value cannot be negative.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Invalid currency")
    void createPersonTransactionInvalidCurrency() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "XPTO";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException());

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(null);
    }

    @Test
    @DisplayName("Test Person Transaction creation - Empty Currency")
    void createPersonTransactionEmptyCurrency() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException());

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(null);
    }

    @Test
    @DisplayName("Test Person Transaction creation - Empty Category")
    void createPersonTransactionEmptyCategory() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Empty Description")
    void createPersonTransactionEmptyDescription() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException("The description can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The description can't be null or empty.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Empty AccountFrom")
    void createPersonTransactionEmptyAccountFrom() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "";
        final String accountTo = "Homer Snacks";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    @Test
    @DisplayName("Test Person Transaction creation - Empty AccountTo")
    void createPersonTransactionEmptyAccountTO() throws Exception {

        //Arrange
        String personId = "marge@hotmail.com";

        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "";
        final String type = "debit";

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

        Mockito.when(service.addPersonalTransaction(createPersonalTransactionDTO)).thenThrow(new IllegalArgumentException("The denomination can't be null or empty."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(personId, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("The denomination can't be null or empty.");
    }

    /**
     * Test create Group transactions
     */


    /**
     * Test get transactions by id
     */
    @Test
    @DisplayName("test getting a transaction from a person Ledger - Happy Case")
    public void getPersonTransactionTest() {
        //Arrange:
        String personId = "marge@hotmail.com";

        TransactionDTO expectedTransaction = new TransactionDTO(50.0, Currency.getInstance("EUR"), "Grocery for baking cookies", "2020-03-20 13:04", "KWIK E MART", "KWIK E MART", "DEBIT", "2");

        ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(expectedTransaction, HttpStatus.OK);

        //Act:
        Mockito.when(service.getTransactionByID(personId, 2L)).thenReturn(expectedTransaction);

        ResponseEntity<Object> actualResponseEntity = controller.getPersonTransactionByID(personId, 2L);

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(expectedResponseEntity, actualResponseEntity),
                () -> assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode())
        );
    }

    @Test
    @DisplayName("test getting a transaction from a person Ledger - person not found")
    public void getPersonTransactionNoPermissionTest() {
        //Arrange:
        String personId = "test@hotmail.com";

        //Act:
        Mockito.when(service.getTransactionByID(personId, 2L)).thenThrow(new IllegalArgumentException("No permission for this operation."));

        Throwable thrown = catchThrowable(() -> {
            controller.getPersonTransactionByID(personId, 2L);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No permission for this operation.");
    }

    @Test
    @DisplayName("test getting a transaction from a person Ledger - transaction not found")
    public void getPersonTransactionNotFoundTest() {
        //Arrange:
        String personId = "marge@hotmail.com";

        //Act:
        Mockito.when(service.getTransactionByID(personId, 15L)).thenThrow(new IllegalArgumentException("No Transaction found with that ID."));

        Throwable thrown = catchThrowable(() -> {
            controller.getPersonTransactionByID(personId, 15L);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No Transaction found with that ID.");
    }

    @Test
    @DisplayName("test getting a transaction from a Group Ledger - Happy Case")
    public void getGroupTransactionTest() {
        //Arrange:
        String groupId = "SWITCH";

        TransactionDTO expectedTransaction = new TransactionDTO(20.0, Currency.getInstance("EUR"), "SUPERBOCK ROUND 2", "2020-03-04 17:00", "ISEP", "POCKET MONEY", "AE ISEP", "DEBIT");

        ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(expectedTransaction, HttpStatus.OK);

        //Act:
        Mockito.when(service.getTransactionByID(groupId, 7L)).thenReturn(expectedTransaction);

        ResponseEntity<Object> actualResponseEntity = controller.getPersonTransactionByID(groupId, 7L);

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(expectedResponseEntity, actualResponseEntity),
                () -> assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode())
        );
    }


    @Test
    @DisplayName("test getting a transaction from a group Ledger - transaction not found")
    public void getGroupTransactionNotFoundTest() {
        //Arrange:
        String groupId = "SWITCH";

        //Act:
        Mockito.when(service.getTransactionByID(groupId, 15L)).thenThrow(new IllegalArgumentException("No Transaction found with that ID."));

        Throwable thrown = catchThrowable(() -> {
            controller.getPersonTransactionByID(groupId, 15L);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("No Transaction found with that ID.");
    }

    /**
     * Test get transactions by owner id
     */
    @Test
    @DisplayName("Test get all transactions from Personal Ledger - Main scenario")
    public void getPersonalTransactionsByLedgerIdSuccess() {
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
    public void getPersonalTransactionsByLedgerIdNoTransactions() {
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
    public void getPersonalTransactionsByLedgerIdSuccessInvalidId() {
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
    public void getGroupTransactionsByLedgerIdSuccessInvalidId() {
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


    /**
     * Test create Group transaction
     */

    @Test
    @DisplayName("Test create group transactions - Main scenario - Happy Case")
    public void createGroupTransactionHappyCase() {

        String groupDescription = "SWITCH";
        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";
        long id = 9;
        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);

        TransactionShortDTO transactionShortDTOExpected = new TransactionShortDTO(amount, Currency.getInstance(currency), groupDescription, accountFrom, accountTo, id);

        ResponseEntity<TransactionShortDTO> responseEntityExpected = new ResponseEntity<>(transactionShortDTOExpected, HttpStatus.CREATED);

        //Act
        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenReturn(transactionShortDTOExpected);

        ResponseEntity<TransactionShortDTO> responseEntityResult = controller.createGroupTransaction(groupDescription, createTransactionInfoDTO);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(responseEntityExpected, responseEntityResult),
                () -> assertEquals(HttpStatus.CREATED, responseEntityResult.getStatusCode())
        );
    }

    @Test
    @DisplayName("Test Group Transaction creation - Group does not exists on Person Repository")
    void createGroupTransactionPersonDoesNotExists() throws Exception {

        String groupDescription = "Not exists";

        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);
        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new ArgumentNotFoundException("No group found with that description."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No group found with that description.");
    }

    @Test
    @DisplayName("Test Group Transaction creation - Category Does Not Exists")
    void createGroupTransactionCategoryDoesNotExists() throws Exception {

        String groupDescription = "Switch";

        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "Not exists";
        final String accountDescription = "SuperBock round1";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);
        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new ArgumentNotFoundException("No category found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No category found with that ID.");
    }

    @Test
    @DisplayName("Test Group Transaction creation - Account From Does Not Exists")
    void createGroupTransactionAccountFromDoesNotExists() throws Exception {

        String groupDescription = "Switch";

        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Not exists";
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);
        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new ArgumentNotFoundException("No account found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");
    }

    @Test
    @DisplayName("Test Group Transaction creation - Account To Does Not Exists")
    void createGroupTransactionAccountTODoesNotExists() throws Exception {

        String groupDescription = "Switch";

        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accountTo = "Not exists";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);


        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);
        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new ArgumentNotFoundException("No account found with that ID."));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No account found with that ID.");
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null Amount")
    void createGroupTransactionNullAmount() throws Exception {

        String groupDescription = "Switch";

        final Double amount = null;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);


        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);

        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new NullPointerException("text"));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null Currency")
    void createGroupTransactionNullCurrency() throws Exception {

        String groupDescription = "Switch";

        final Double amount = 10.00;
        final String currency = null;
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);


        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);

        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new NullPointerException("text"));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null date")
    void createGroupTransactionNullDate() throws Exception {

        String groupDescription = "Switch";

        final Double amount = null;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);

        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new NullPointerException("text"));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null Category")
    void createGroupTransactionNullCategory() throws Exception {
        String groupDescription = "Switch";

        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = null;
        final String accountDescription = "SuperBock round1";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);

        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new NullPointerException("text"));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null Description")
    void createGroupTransactionNullDescription() throws Exception {
        String groupDescription = "Switch";

        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "null";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);

        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new NullPointerException("text"));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null AccountFrom")
    void createGroupTransactionNullAccountFrom() throws Exception {
        String groupDescription = "Switch";

        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accountTo = "AE ISEP";
        final String accountFrom = null;
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);

        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new NullPointerException("text"));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }


    @Test
    @DisplayName("Test Group Transaction creation - Null Type")
    void createGroupTransactionNullType() throws Exception {
        String groupDescription = "Switch";

        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = null;

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);

        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new NullPointerException("text"));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null AccountTo")
    void createGroupTransactionNullAccountTo() throws Exception {
        String groupDescription = "Switch";

        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accountTo = null;
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);

        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new NullPointerException("text"));

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createPersonTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NullPointerException.class)
                .hasMessage(null);
    }

  

    @Test
    @DisplayName("Test Group Transaction creation - Empty Currency")
    void createGroupTransactionEmptyCurrency() throws Exception {
        String groupDescription = "Switch";

        final Double amount = 10.00;
        final String currency = "";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "null";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        CreateGroupTransactionDTO createGroupTransactionDTO = LedgerDTOAssembler.transformToCreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO);

        Mockito.when(service.addGroupTransaction(createGroupTransactionDTO)).thenThrow(new IllegalArgumentException());

        //Act
        Throwable thrown = catchThrowable(() -> {
            controller.createGroupTransaction(groupDescription, createTransactionInfoDTO);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage(null);
    }
}