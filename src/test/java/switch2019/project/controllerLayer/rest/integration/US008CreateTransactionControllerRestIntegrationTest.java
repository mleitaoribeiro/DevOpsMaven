package switch2019.project.controllerLayer.rest.integration;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreateTransactionInfoDTO;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US008CreateTransactionControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP() {
        super.setUP();
    }

   /*

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

*/

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




    /**
     * Test getTransactionsByLedgerID - Personal Ledger
     */

 /*   @Test
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

                () -> assertEquals("20.0",jArray.getJSONObject(0).getString("amount")),
                () -> assertEquals("EUR",jArray.getJSONObject(0).getString("currency")),
                () -> assertEquals("MOEY",jArray.getJSONObject(0).getString("accountFrom")),
                () -> assertEquals("FITNESSUP",jArray.getJSONObject(0).getString("accountTo")),
                () -> assertEquals("DEBIT",jArray.getJSONObject(0).getString("type")),
                () -> assertEquals("3",jArray.getJSONObject(0).getString("id")),

                () -> assertEquals(expectedLink1, jArray.getJSONObject(0).getString("links")),

                () -> assertEquals("150.0",jArray.getJSONObject(1).getString("amount")),
                () -> assertEquals("EUR",jArray.getJSONObject(1).getString("currency")),
                () -> assertEquals("MOEY",jArray.getJSONObject(1).getString("accountFrom")),
                () -> assertEquals("DECATLHON",jArray.getJSONObject(1).getString("accountTo")),
                () -> assertEquals("DEBIT",jArray.getJSONObject(1).getString("type")),
                () -> assertEquals("4",jArray.getJSONObject(1).getString("id")),
                () -> assertEquals(expectedLink2, jArray.getJSONObject(1).getString("links"))
        );
    }

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - No Transactions")
    public void getPersonalTransactionsByLedgerIdNoTransactions() throws Exception  {
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
    public void getPersonalTransactionsByLedgerIdInvalidId() throws Exception  {
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
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No Ledger found with that ID.", result.getString("message"))
        );
    }*/


    /**
     * Test getTransactionsByLedgerID - Group Ledger
     */

    /*@Test
    @DisplayName("Test get all transactions from Personal Ledger - Main scenario")
    public void getGroupTransactionsByLedgerIdSuccess() throws Exception  {
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

                () -> assertEquals("50.0",jArray.getJSONObject(0).getString("amount")),
                () -> assertEquals("EUR",jArray.getJSONObject(0).getString("currency")),
                () -> assertEquals("REVOLUT",jArray.getJSONObject(0).getString("accountFrom")),
                () -> assertEquals("NETFLIX",jArray.getJSONObject(0).getString("accountTo")),
                () -> assertEquals("DEBIT",jArray.getJSONObject(0).getString("type")),
                () -> assertEquals("5",jArray.getJSONObject(0).getString("id")),

                () -> assertEquals(expectedLink, jArray.getJSONObject(0).getString("links"))
        );
    }

    @Test
    @DisplayName("Test get all transactions from Personal Ledger - No Transactions")
    public void getGroupTransactionsByLedgerIdNoTransactions() throws Exception  {
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
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No Ledger found with that ID.", result.getString("message"))
        );
    }*/
}