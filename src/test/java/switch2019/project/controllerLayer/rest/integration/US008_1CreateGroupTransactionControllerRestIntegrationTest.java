package switch2019.project.controllerLayer.rest.integration;


import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreateTransactionInfoDTO;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US008_1CreateGroupTransactionControllerRestIntegrationTest extends AbstractTest {

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
}