package switch2019.project.controllerLayer.rest.integration;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreateTransactionInfoDTO;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Currency;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class US008CreateTransactionControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP() {
        super.setUP();
    }


    /**
     * Test Personal Transaction creation
     */

    @Test
    @DisplayName("Test Person Transaction creation - Happy Case - test if outputDTO, HTTP response are expected. Test if transaction was persisted in DB")
    void createPersonTransactionMainScenario() throws Exception {

        //GET - Before Transaction is created
        String uriGet = "/persons/marge@hotmail.com/ledger/transactions/9";

        MvcResult mvcResultGetBefore = mvc.perform(MockMvcRequestBuilders.get(uriGet)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int statusBefore = mvcResultGetBefore.getResponse().getStatus();

        JSONObject getBefore = new JSONObject(mvcResultGetBefore.getResponse().getContentAsString());

        //POST - Create new Transaction
        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        //Expected Links
        String expectedLinks = "{\"self\":{\"href\":\"http:\\/\\/localhost\\/persons\\/marge@hotmail.com\\/ledger\\/transactions\\/9\"}," +
                "\"transactions\":{\"href\":\"http:\\/\\/localhost\\/persons\\/marge@hotmail.com\\/ledger\\/transactions\"}}";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Get - After Transaction is created

        MvcResult mvcResultGetAfter = mvc.perform(MockMvcRequestBuilders.get(uriGet)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int statusAfter = mvcResultGetAfter.getResponse().getStatus();

        JSONObject getAfter = new JSONObject(mvcResultGetAfter.getResponse().getContentAsString());


        Assertions.assertAll(
                //Get before Transaction is created
                () -> assertEquals(422, statusBefore),
                () -> assertEquals("No transaction found with that ID.", getBefore.getString("message")),

                //Create new Transaction
                () -> assertEquals(201, status),
                () -> assertEquals(amount.toString(), result.getString("amount")),
                () -> assertEquals(currency, result.getString("currency")),
                () -> assertEquals(accountFrom.toUpperCase(), result.getString("accountFrom")),
                () -> assertEquals(accountTo.toUpperCase(), result.getString("accountTo")),
                () -> assertEquals(type.toUpperCase(), result.getString("type")),
                () -> assertEquals(expectedLinks, result.getString("_links")),

                //Get after Transaction is created
                () -> assertEquals(200, statusAfter),
                () -> assertEquals(amount.toString(), getAfter.getString("amount")),
                () -> assertEquals(currency, getAfter.getString("currency")),
                () -> assertEquals(accountFrom.toUpperCase(), getAfter.getString("accountFrom")),
                () -> assertEquals(accountTo.toUpperCase(), getAfter.getString("accountTo")),
                () -> assertEquals(type.toUpperCase(), getAfter.getString("type"))
        );
    }


    @Test
    @DisplayName("Test Person Transaction creation - Person does not exists on Person Repository")
    void createPersonTransactionPersonDoesNotExists() throws Exception {

        //Arrange

        String uriPost = "/persons/not_existing_person@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new ArgumentNotFoundException("No person found with that email.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No person found with that email.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Category Does Not Exists")
    void createPersonTransactionCategoryDoesNotExists() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new ArgumentNotFoundException("No category found with that ID.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No category found with that ID.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Account From Does Not Exists")
    void createPersonTransactionAccountFromDoesNotExists() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new ArgumentNotFoundException("No account found with that ID.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No account found with that ID.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - AccountTo Does Not Exists")
    void createPersonTransactionAccountToDoesNotExists() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new ArgumentNotFoundException("No account found with that ID.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No account found with that ID.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null Amount")
    void createPersonTransactionNullAmount() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new NullPointerException().toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().is5xxServerError())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(500, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("500", result.getString("statusCode")),
                () -> assertEquals("INTERNAL_SERVER_ERROR", result.getString("status")),
                () -> assertEquals("error occurred", result.getString("error")),
                () -> assertEquals("null", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null Currency")
    void createPersonTransactionNullCurrency() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new NullPointerException().toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().is5xxServerError())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(500, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("500", result.getString("statusCode")),
                () -> assertEquals("INTERNAL_SERVER_ERROR", result.getString("status")),
                () -> assertEquals("error occurred", result.getString("error")),
                () -> assertEquals("null", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null date")
    void createPersonTransactionNullDate() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new NullPointerException("text").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().is5xxServerError())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(500, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("500", result.getString("statusCode")),
                () -> assertEquals("INTERNAL_SERVER_ERROR", result.getString("status")),
                () -> assertEquals("error occurred", result.getString("error")),
                () -> assertEquals("text", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null Category")
    void createPersonTransactionNullCategory() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = null;
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null Description")
    void createPersonTransactionNullDescription() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The description can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The description can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null AccountFrom")
    void createPersonTransactionNullAccountFrom() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null AccountTo")
    void createPersonTransactionNullAccountTO() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null type")
    void createPersonTransactionNullType() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = "HOUSE";
        final String description = "beers";
        final String accountFrom = "MasterCard";
        final String accountTo = "Homer Snacks";
        final String type = null;

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(category);
        createTransactionInfoDTO.setDescription(description);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new NullPointerException().toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().is5xxServerError())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(500, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("500", result.getString("statusCode")),
                () -> assertEquals("INTERNAL_SERVER_ERROR", result.getString("status")),
                () -> assertEquals("error occurred", result.getString("error")),
                () -> assertEquals("null", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Invalid Email")
    void createPersonTransactionInvalidEmail() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The email is not valid.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The email is not valid.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Invalid Amount")
    void createPersonTransactionInvalidAmount() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The monetary value cannot be negative.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The monetary value cannot be negative.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }


    @Test
    @DisplayName("Test Person Transaction creation - Invalid currency")
    void createPersonTransactionInvalidCurrency() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException().toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("null", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Invalid Date")
    void createPersonTransactionInvalidDate() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
        final Double amount = 10.50;
        final String currency = "EUR";
        final String date = "2020-05-25";
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().is5xxServerError())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(500, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("500", result.getString("statusCode")),
                () -> assertEquals("INTERNAL_SERVER_ERROR", result.getString("status")),
                () -> assertEquals("error occurred", result.getString("error")),
                () -> assertEquals("Text '2020-05-25' could not be parsed at index 10", result.getString("message"))
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Empty Currency")
    void createPersonTransactionEmptyCurrency() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException().toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("null", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Empty Category")
    void createPersonTransactionEmptyCategory() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Empty Description")
    void createPersonTransactionEmptyDescription() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The description can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The description can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Empty AccountFrom")
    void createPersonTransactionEmptyAccountFrom() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }


    @Test
    @DisplayName("Test Person Transaction creation - Empty AccountTo")
    void createPersonTransactionEmptyAccountTO() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }


    @Test
    @DisplayName("Test Person Transaction creation - null input")
    void createPersonTransactionNullInput() throws Exception {

        //POST - Create new Transaction
        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Serialize input Json
        String inputJson = super.mapToJson(null);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        Assertions.assertAll(
                () -> assertEquals(400, status),
                () -> assertEquals("Required request body is missing.", result.getString("message"))
        );
    }


    /**
     * Test Group Transaction creation
     */
    @Test
    @DisplayName("Test Group Transaction creation - test if outputDTO, HTTP response are expected. Test if transaction was persisted in Db")
    void createGroupTransactionMainScenario() throws Exception {
        //GET - Before account is created
        String uriGet = "/groups/SWITCH/ledger/transactions/9";

        MvcResult mvcResultGetBefore = mvc.perform(MockMvcRequestBuilders.get(uriGet)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int statusBefore = mvcResultGetBefore.getResponse().getStatus();
        JSONObject getBefore = new JSONObject(mvcResultGetBefore.getResponse().getContentAsString());

        //POST - Create new account
        String uriPost = "/groups/SWITCH/ledger";

        //Create input DTO
        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accountTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03 18:00";
        final String type = "debit";
        final String personEmail = "1191762@isep.ipp.pt";
        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accountTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);
        createTransactionInfoDTO.setPersonEmail(personEmail);


        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        //Expected Links
        String expectedLinks = "{\"self\":{\"href\":\"http:\\/\\/localhost\\/groups\\/SWITCH\\/ledger\\/transactions\\/9\"}," +
                "\"transactions\":{\"href\":\"http:\\/\\/localhost\\/groups\\/SWITCH\\/ledger\\/transactions\"}}";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());


        //Get - After transaction is created
        MvcResult mvcResultGetAfter = mvc.perform(MockMvcRequestBuilders.get(uriGet)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int statusAfter = mvcResultGetAfter.getResponse().getStatus();
        JSONObject getAfter = new JSONObject(mvcResultGetAfter.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                //Get before Transaction is created
                () -> assertEquals(422, statusBefore),
                () -> assertEquals("No transaction found with that ID.", getBefore.getString("message")),

                //Create new Transaction
                () -> assertEquals(201, status),
                () -> assertEquals(amount.toString(), result.getString("amount")),
                () -> assertEquals(currency, result.getString("currency")),
                () -> assertEquals(accountFrom.toUpperCase(), result.getString("accountFrom")),
                () -> assertEquals(accountTo.toUpperCase(), result.getString("accountTo")),
                () -> assertEquals(type.toUpperCase(), result.getString("type")),
                () -> assertEquals(expectedLinks, result.getString("_links")),

                // Get after Transaction is created
                () -> assertEquals(200, statusAfter),
                () -> assertEquals(amount.toString(), getAfter.getString("amount")),
                () -> assertEquals(currency, getAfter.getString("currency")),
                () -> assertEquals(accountFrom.toUpperCase(), getAfter.getString("accountFrom")),
                () -> assertEquals(accountTo.toUpperCase(), getAfter.getString("accountTo")),
                () -> assertEquals(type.toUpperCase(), getAfter.getString("type"))

        );
    }


    @Test
    @DisplayName("Test Group Account creation -  person does not exists on Person Repository")
    void addGroupTransactionPersonDoesNotExits() throws Exception {

        //Arrange

        String uriPost = "/groups/Switch/ledger";

        //Create input DTO

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(5.00);
        createTransactionInfoDTO.setCurrency("EUR");
        createTransactionInfoDTO.setCategory("ISEP");
        createTransactionInfoDTO.setDescription("SuperBock round1");
        createTransactionInfoDTO.setAccountTo("AE ISEP");
        createTransactionInfoDTO.setAccountFrom("Pocket money");
        createTransactionInfoDTO.setDate("2020-03-03 18:00");
        createTransactionInfoDTO.setType("false");
        createTransactionInfoDTO.setPersonEmail("pedro@hotmail.com");

        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new ArgumentNotFoundException("No person found with that email.").toString();
        //Act

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No person found with that email.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Transaction creation - Category Does Not Exists")
    void createGroupTransactionCategoryDoesNotExists() throws Exception {

       //Arrange
        String uriPost = "/groups/Switch/ledger";

        //Create input DTO
        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(5.00);
        createTransactionInfoDTO.setCurrency("EUR");
        createTransactionInfoDTO.setCategory("Not existing category");
        createTransactionInfoDTO.setDescription("SuperBock round1");
        createTransactionInfoDTO.setAccountTo("AE ISEP");
        createTransactionInfoDTO.setAccountFrom("Pocket money");
        createTransactionInfoDTO.setDate("2020-03-03 18:00");
        createTransactionInfoDTO.setType("false");
        createTransactionInfoDTO.setPersonEmail("1191762@isep.ipp.pt");

        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new ArgumentNotFoundException("No category found with that ID.").toString();
        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No category found with that ID.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Transaction creation - Account From Does Not Exists")
    void createGroupTransactionAccountFromDoesNotExists() throws Exception {
        //Arrange
        String uriPost = "/groups/Switch/ledger";

        //Create input DTO
        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(5.00);
        createTransactionInfoDTO.setCurrency("EUR");
        createTransactionInfoDTO.setCategory("ISEP");
        createTransactionInfoDTO.setDescription("SuperBock round1");
        createTransactionInfoDTO.setAccountTo("AE ISEP");
        createTransactionInfoDTO.setAccountFrom("Not existing account");
        createTransactionInfoDTO.setDate("2020-03-03 18:00");
        createTransactionInfoDTO.setType("false");
        createTransactionInfoDTO.setPersonEmail("1191762@isep.ipp.pt");

        String inputJson = super.mapToJson((createTransactionInfoDTO));
        String expectedResolvedException = new ArgumentNotFoundException("No account found with that ID.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No account found with that ID.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Transaction creation - Account TO Does Not Exists")
    void createGroupTransactionAccountToDoesNotExists() throws Exception {
        //Arrange
        String uriPost = "/groups/Switch/ledger";

        //Create input DTO
        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(5.00);
        createTransactionInfoDTO.setCurrency("EUR");
        createTransactionInfoDTO.setCategory("ISEP");
        createTransactionInfoDTO.setDescription("SuperBock round1");
        createTransactionInfoDTO.setAccountTo("Not existing account");
        createTransactionInfoDTO.setAccountFrom("Pocket Money");
        createTransactionInfoDTO.setDate("2020-03-03 18:00");
        createTransactionInfoDTO.setType("false");
        createTransactionInfoDTO.setPersonEmail("1191762@isep.ipp.pt");

        String inputJson = super.mapToJson((createTransactionInfoDTO));
        String expectedResolvedException = new ArgumentNotFoundException("No account found with that ID.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No account found with that ID.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null Amount")
    void createGroupTransactionNullAmount() throws Exception {
        //Arrange
        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new NullPointerException().toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().is5xxServerError())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(500, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("500", result.getString("statusCode")),
                () -> assertEquals("INTERNAL_SERVER_ERROR", result.getString("status")),
                () -> assertEquals("error occurred", result.getString("error")),
                () -> assertEquals("null", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Person Transaction creation - Null Currency")
    void createGroupTransactionNullCurrency() throws Exception {
        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
        final Double amount = 10.00;
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new NullPointerException().toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().is5xxServerError())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(500, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("500", result.getString("statusCode")),
                () -> assertEquals("INTERNAL_SERVER_ERROR", result.getString("status")),
                () -> assertEquals("error occurred", result.getString("error")),
                () -> assertEquals("null", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null date")
    void createGroupTransactionNullDate() throws Exception {
        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
        final Double amount = 10.00;
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

       //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new NullPointerException("text").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().is5xxServerError())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(500, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("500", result.getString("statusCode")),
                () -> assertEquals("INTERNAL_SERVER_ERROR", result.getString("status")),
                () -> assertEquals("error occurred", result.getString("error")),
                () -> assertEquals("text", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null Category")
    void createGroupTransactionNullCategory() throws Exception {

        //Arrange

        String uriPost = "/persons/marge@hotmail.com/ledger/transactions";

        //Create input DTO
        final Double amount = 10.00;
        final String currency = "EUR";
        final String date = "2020-05-25 15:50";
        final String category = null;
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

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null Description")
    void createGroupTransactionNullDescription() throws Exception {

        //Arrange

        String uriPost = "/groups/Switch/ledger";

        //Create input DTO

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(5.00);
        createTransactionInfoDTO.setCurrency("EUR");
        createTransactionInfoDTO.setCategory("ISEP");
        createTransactionInfoDTO.setDescription(null);
        createTransactionInfoDTO.setAccountTo("AE ISEP");
        createTransactionInfoDTO.setAccountFrom("Pocket money");
        createTransactionInfoDTO.setDate("2020-03-03 18:00");
        createTransactionInfoDTO.setType("false");
        createTransactionInfoDTO.setPersonEmail("1191762@isep.ipp.pt");

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The description can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The description can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null AccountFrom")
    void createGroupTransactionNullAccountFrom() throws Exception {
        //Arrange

        String uriPost = "/groups/Switch/ledger";

        //Create input DTO

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(5.00);
        createTransactionInfoDTO.setCurrency("EUR");
        createTransactionInfoDTO.setCategory("ISEP");
        createTransactionInfoDTO.setDescription("Super bock round1");
        createTransactionInfoDTO.setAccountTo("AE ISEP");
        createTransactionInfoDTO.setAccountFrom(null);
        createTransactionInfoDTO.setDate("2020-03-03 18:00");
        createTransactionInfoDTO.setType("false");
        createTransactionInfoDTO.setPersonEmail("1191762@isep.ipp.pt");

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Transaction creation - Null AccountTo")
    void createGroupTransactionNullAccountTO() throws Exception {
        //Arrange

        String uriPost = "/groups/Switch/ledger";

        //Create input DTO

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(5.00);
        createTransactionInfoDTO.setCurrency("EUR");
        createTransactionInfoDTO.setCategory("ISEP");
        createTransactionInfoDTO.setDescription("Super bock round1");
        createTransactionInfoDTO.setAccountTo(null);
        createTransactionInfoDTO.setAccountFrom("Pocket Money");
        createTransactionInfoDTO.setDate("2020-03-03 18:00");
        createTransactionInfoDTO.setType("false");
        createTransactionInfoDTO.setPersonEmail("1191762@isep.ipp.pt");

        //Serialize input Json
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

//    @Test
//    @DisplayName("Test Group Transaction creation - Null type")
//    void createGroupTransactionNullType() throws Exception {
//        //Arrange
//
//        String uriPost = "/groups/Switch/ledger";
//
//        //Create input DTO
//
//        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();
//
//        createTransactionInfoDTO.setAmount(5.00);
//        createTransactionInfoDTO.setCurrency("EUR");
//        createTransactionInfoDTO.setCategory("ISEP");
//        createTransactionInfoDTO.setDescription("Super bock round1");
//        createTransactionInfoDTO.setAccountTo("ISEP");
//        createTransactionInfoDTO.setAccountFrom("Pocket Money");
//        createTransactionInfoDTO.setDate("2020-03-03 18:00");
//        createTransactionInfoDTO.setType(null);
//        createTransactionInfoDTO.setPersonEmail("1191762@isep.ipp.pt");
//
//        //Serialize input Json
//        String inputJson = super.mapToJson((createTransactionInfoDTO));
//        String expectedResolvedException = new NullPointerException().toString();
//
//        //Act
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(inputJson))
//                .andExpect(status().is5xxServerError())
//                .andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//
//        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());
//
//        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();
//
//        //Assert
//        Assertions.assertAll(
//                () -> assertEquals(500, status),
//                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
//                () -> assertEquals("500", result.getString("statusCode")),
//                () -> assertEquals("INTERNAL_SERVER_ERROR", result.getString("status")),
//                () -> assertEquals("error occurred", result.getString("error")),
//                () -> assertEquals("null", result.getString("message")),
//                () -> assertEquals(expectedResolvedException, realResolvedException)
//        );
//
//    }

    @Test
    @DisplayName("Test Group Transaction creation - null input")
    void createGroupAndBecomeAdminNullJsonInput() throws Exception {

        //Arrange
        String uri = "/groups/Switch/ledger";

        String inputJson = super.mapToJson((null));

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(400, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("400", result.getString("statusCode")),
                () -> assertEquals("BAD_REQUEST", result.getString("status")),
                () -> assertEquals("The request body needed to perform the operation is missing.", result.getString("error")),
                () -> assertEquals("Required request body is missing.", result.getString("message"))
        );

    }


    @Test
    @DisplayName("Test Group Account creation - Email Null")
    void addGroupTransactionNullEmail() throws Exception {

        //Arrange

        String uriPost = "/groups/SWITCH/ledger";

        //Create input DTO

        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accounTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03, 18:00";
        final String type = "false";
        final String personEmail = null;
        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accounTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);
        createTransactionInfoDTO.setPersonEmail(personEmail);

        //Arrangement the input
        String inputJson = super.mapToJson((createTransactionInfoDTO));


        String expectedResolvedException = new IllegalArgumentException("The email can't be null.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The email can't be null.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Test Group Transaction creation - Email Empty")
    void addGroupTransactionEmailEmpty() throws Exception {

        //Arrange
        String uriPost = "/groups/SWITCH/ledger";

        //Create input DTO

        final Double amount = 10.00;
        final String currency = "EUR";
        final String categoryDenomination = "ISEP";
        final String accountDescription = "SuperBock round1";
        final String accounTo = "AE ISEP";
        final String accountFrom = "Pocket Money";
        final String date = "2020-03-03, 18:00";
        final String type = "false";
        final String personEmail = "";
        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(amount);
        createTransactionInfoDTO.setCurrency(currency);
        createTransactionInfoDTO.setCategory(categoryDenomination);
        createTransactionInfoDTO.setDescription(accountDescription);
        createTransactionInfoDTO.setAccountTo(accounTo);
        createTransactionInfoDTO.setAccountFrom(accountFrom);
        createTransactionInfoDTO.setDate(date);
        createTransactionInfoDTO.setType(type);
        createTransactionInfoDTO.setPersonEmail(personEmail);

        //Arrangement the input
        String inputJson = super.mapToJson((createTransactionInfoDTO));


        String expectedResolvedException = new IllegalArgumentException("The email is not valid.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals("The email is not valid.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }


    @Test
    @DisplayName("Test Group Account creation - Invalid POST Method")
    void addGroupTransactionInvalidUri() throws Exception {

        //Arrange

        String uriPost = "/groups/transactions";

        //Create input DTO

        CreateTransactionInfoDTO createTransactionInfoDTO = new CreateTransactionInfoDTO();

        createTransactionInfoDTO.setAmount(5.00);
        createTransactionInfoDTO.setCurrency("EUR");
        createTransactionInfoDTO.setCategory("Shopping");
        createTransactionInfoDTO.setDescription("Shopping Expenses");
        createTransactionInfoDTO.setAccountTo("Querido Account");
        createTransactionInfoDTO.setAccountFrom("Raquel Account");
        createTransactionInfoDTO.setDate("18-05-2020");
        createTransactionInfoDTO.setType("debit");
        createTransactionInfoDTO.setPersonEmail("pedro@hotmail.com");

        //Arrangement the input
        String inputJson = super.mapToJson((createTransactionInfoDTO));

        String expectedResolvedException = new HttpRequestMethodNotSupportedException("POST").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uriPost)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isMethodNotAllowed())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT
        Assertions.assertAll(
                () -> assertEquals(405, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("405", result.getString("statusCode")),
                () -> assertEquals("METHOD_NOT_ALLOWED", result.getString("status")),
                () -> assertEquals("Request method 'POST' not supported", result.getString("error")),
                () -> assertEquals("POST method is not supported for this request. Supported methods are GET ", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }


    /**
     * Test getTransactionsByID
     */
    @Test
    @DisplayName("Get Transaction By ID - Person - happy case")
    void getPersonTransactionOnPersonIdHappyCase() throws Exception {

        //Arrange:

        //Arrange the uri that is going to be posted
        String uriPost = "/persons/marge@hotmail.com/ledger/transactions/2";


        //Act:
        //Assembling the Json Object obtained as response
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriPost)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(200, status),

                () -> assertEquals("50.0", result.getString("amount")),
                () -> assertEquals("EUR", result.getString("currency")),
                () -> assertEquals("MASTERCARD", result.getString("accountFrom")),
                () -> assertEquals("KWIK E MART", result.getString("accountTo")),
                () -> assertEquals("KWIK E MART", result.getString("accountTo")),
                () -> assertEquals("DEBIT", result.getString("type"))
        );
    }

    @Test
    @DisplayName("Get Transaction By ID - Person - No Permission")
    void getPersonTransactionOnPersonIdNoPermission() throws Exception {

        //Arrange:

        //Arrange the uri that is going to be posted
        String uriPost = "/persons/rick@gmail.com/ledger/transactions/2";

        //Act:
        //Assembling the Json Object obtained as response
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriPost)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        Assertions.assertAll(
                () -> assertEquals(403, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("403", result.getString("statusCode")),
                () -> assertEquals("FORBIDDEN", result.getString("status")),
                () -> assertEquals("No permission for this operation.", result.getString("error")),
                () -> assertEquals("No permission.", result.getString("message"))
        );
    }

    @Test
    @DisplayName("Get Transaction By ID - Person - Category Id not found")
    void getTransactionOfPersonIdNotFound() throws Exception {

        //Arrange:

        //Arrange the uri that is going to be posted
        String uriPost = "/persons/rick@gmail.com/ledger/transactions/10";

        //Act:
        //Assembling the Json Object obtained as response
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriPost)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No transaction found with that ID.", result.getString("message"))
        );
    }

    @Test
    @DisplayName("Get Transaction By ID - Person - Person Id not found")
    void getTransactionOnPersonIdNotFound() throws Exception {

        //Arrange:

        //Arrange the uri that is going to be posted
        String uriPost = "/persons/test@gmail.com/ledger/transactions/2";

        //Act:
        //Assembling the Json Object obtained as response
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriPost)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No person found with that email.", result.getString("message"))
        );
    }

    @Test
    @DisplayName("Get Transaction By ID - Group - happy case")
    void getTransactionsByGroupIdHappyCase() throws Exception {

        //Arrange:

        //Arrange the uri that is going to be posted
        String uriPost = "/groups/SWITCH/ledger/transactions/7";


        //Act:
        //Assembling the Json Object obtained as response
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriPost)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(200, status),

                () -> assertEquals("20.0", result.getString("amount")),
                () -> assertEquals("EUR", result.getString("currency")),
                () -> assertEquals("POCKET MONEY", result.getString("accountFrom")),
                () -> assertEquals("AE ISEP", result.getString("accountTo")),
                () -> assertEquals("AE ISEP", result.getString("accountTo")),
                () -> assertEquals("DEBIT", result.getString("type"))
        );
    }

    @Test
    @DisplayName("Get Transaction By ID - Group - No Permission")
    void getTransactionsByGroupIdNoPermission() throws Exception {

        //Arrange:

        //Arrange the uri that is going to be posted
        String uriPost = "/groups/SWITCH/ledger/transactions/2";

        //Act:
        //Assembling the Json Object obtained as response
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriPost)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        Assertions.assertAll(
                () -> assertEquals(403, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("403", result.getString("statusCode")),
                () -> assertEquals("FORBIDDEN", result.getString("status")),
                () -> assertEquals("No permission for this operation.", result.getString("error")),
                () -> assertEquals("No permission.", result.getString("message"))
        );
    }

    @Test
    @DisplayName("Get Transaction By ID - Group - Category Id not found")
    void getTransactionsByGroupIdNotFound() throws Exception {

        //Arrange:

        //Arrange the uri that is going to be posted
        String uriPost = "/groups/SWITCH/ledger/transactions/12";

        //Act:
        //Assembling the Json Object obtained as response
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriPost)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No transaction found with that ID.", result.getString("message"))
        );
    }

    @Test
    @DisplayName("Get Transaction By ID - Group - Group Id not found")
    void getTransactionsByLedgerGroupIdNotFound() throws Exception {

        //Arrange:

        //Arrange the uri that is going to be posted
        String uriPost = "/groups/TEST/ledger/transactions/2";

        //Act:
        //Assembling the Json Object obtained as response
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uriPost)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No group found with that description.", result.getString("message"))
        );
    }

    /**
     * Test getTransactionsByLedgerID - Personal Ledger
     */

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - Main scenario")
    public void getPersonalTransactionsByLedgerIdSuccess() throws Exception {
        //Arrange
        String uri = "/persons/1191780@isep.ipp.pt/ledger/transactions";

        String expectedLink1 = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/persons\\/1191780@isep.ipp.pt\\/ledger\\/transactions\\/3\"}]";
        String expectedLink2 = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/persons\\/1191780@isep.ipp.pt\\/ledger\\/transactions\\/4\"}]";


        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        String result = mvcResult.getResponse().getContentAsString();

        JSONArray jArray = (JSONArray) new JSONTokener(result).nextValue();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),

                () -> assertEquals("20.0", jArray.getJSONObject(0).getString("amount")),
                () -> assertEquals("EUR", jArray.getJSONObject(0).getString("currency")),
                () -> assertEquals("MOEY", jArray.getJSONObject(0).getString("accountFrom")),
                () -> assertEquals("FITNESSUP", jArray.getJSONObject(0).getString("accountTo")),
                () -> assertEquals("DEBIT", jArray.getJSONObject(0).getString("type")),
                () -> assertEquals("3", jArray.getJSONObject(0).getString("id")),

                () -> assertEquals(expectedLink1, jArray.getJSONObject(0).getString("links")),

                () -> assertEquals("150.0", jArray.getJSONObject(1).getString("amount")),
                () -> assertEquals("EUR", jArray.getJSONObject(1).getString("currency")),
                () -> assertEquals("MOEY", jArray.getJSONObject(1).getString("accountFrom")),
                () -> assertEquals("DECATLHON", jArray.getJSONObject(1).getString("accountTo")),
                () -> assertEquals("DEBIT", jArray.getJSONObject(1).getString("type")),
                () -> assertEquals("4", jArray.getJSONObject(1).getString("id")),
                () -> assertEquals(expectedLink2, jArray.getJSONObject(1).getString("links"))
        );
    }

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - No Transactions")
    public void getPersonalTransactionsByLedgerIdNoTransactions() throws Exception {
        //Arrange
        String uri = "/persons/maria@gmail.com/ledger/transactions";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        String result = mvcResult.getResponse().getContentAsString();

        JSONArray jArray = (JSONArray) new JSONTokener(result).nextValue();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertTrue(jArray.length() == 0)
        );
    }

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - Invalid id")
    public void getPersonalTransactionsByLedgerIdInvalidId() throws Exception {
        //Arrange
        String uri = "/persons/nobody@gmail.com/ledger/transactions";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No Ledger found with that ID.", result.getString("message"))
        );
    }


    /**
     * Test getTransactionsByLedgerID - Group Ledger
     */

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - Main scenario")
    public void getGroupTransactionsByLedgerIdSuccess() throws Exception {
        //Arrange
        String uri = "/groups/FAMILY CARDOSO/ledger/transactions";

        String expectedLink = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/groups\\/FAMILY%20CARDOSO\\/ledger\\/transactions\\/5\"}]";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        String result = mvcResult.getResponse().getContentAsString();

        JSONArray jArray = (JSONArray) new JSONTokener(result).nextValue();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),

                () -> assertEquals("50.0", jArray.getJSONObject(0).getString("amount")),
                () -> assertEquals("EUR", jArray.getJSONObject(0).getString("currency")),
                () -> assertEquals("REVOLUT", jArray.getJSONObject(0).getString("accountFrom")),
                () -> assertEquals("NETFLIX", jArray.getJSONObject(0).getString("accountTo")),
                () -> assertEquals("DEBIT", jArray.getJSONObject(0).getString("type")),
                () -> assertEquals("5", jArray.getJSONObject(0).getString("id")),

                () -> assertEquals(expectedLink, jArray.getJSONObject(0).getString("links"))
        );
    }

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - No Transactions")
    public void getGroupTransactionsByLedgerIdNoTransactions() throws Exception {
        //Arrange
        String uri = "/groups/FRIENDS/ledger/transactions";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        String result = mvcResult.getResponse().getContentAsString();

        JSONArray jArray = (JSONArray) new JSONTokener(result).nextValue();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertTrue(jArray.length() == 0)
        );
    }

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - Invalid id")
    public void getGroupTransactionsByLedgerIdInvalidId() throws Exception {
        //Arrange
        String uri = "/groups/VOID/ledger/transactions";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(), result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals("This resource was not found.", result.getString("error")),
                () -> assertEquals("No Ledger found with that ID.", result.getString("message"))
        );
    }
}