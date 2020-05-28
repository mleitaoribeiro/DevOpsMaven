package switch2019.project.controllerLayer.rest.integration;
import org.hamcrest.CoreMatchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class US001AreSiblingsControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP() {
        super.setUP();
    }

    @Test
    @DisplayName("Test if two people are siblings - same Mother, same Father and on each others Siblings List")
    public void checkIfTwoPeopleAreSiblingsTRUE() throws Exception {

        // Arrange
        String personEmail1 = "antonio@isep.ipp.pt";
        String personEmail2 = "manuel@isep.ipp.pt";
        String uri = "/persons/" + personEmail1 + "/siblings/" + personEmail2;

        //Expected Result
        String expectedResult = "They are siblings.";
        String expectedLinks = "{\"Siblings List:\":" + "{\"href\":\"http:\\/\\/localhost\\/persons\\/" + personEmail1 + "\\/siblings\"}}";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expectedResult,result.getString("siblings")),
                () -> assertEquals (expectedLinks, result.getString("_links"))
        );
    }


    @Test
    @DisplayName("Test if two people are siblings - only same Mother")
    public void checkIfTwoPeopleAreSiblingsTRUESameMother() throws Exception {

        // Arrange
        String personEmail1 = "antonio@isep.ipp.pt";
        String personEmail2 = "roberto@isep.ipp.pt";
        String uri = "/persons/" + personEmail1 + "/siblings/" + personEmail2;

        //Expected Result
        String expectedResult = "They are siblings.";
        String expectedLinks = "{\"Siblings List:\":" + "{\"href\":\"http:\\/\\/localhost\\/persons\\/" + personEmail1 + "\\/siblings\"}}";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expectedResult,result.getString("siblings")),
                () -> assertEquals (expectedLinks, result.getString("_links"))
        );
    }

    @Test
    @DisplayName("Test if two people are siblings - only same Father")
    public void checkIfTwoPeopleAreSiblingsTRUESameFather() throws Exception {

        // Arrange
        String personEmail1 = "antonio@isep.ipp.pt";
        String personEmail2 = "amalia@isep.ipp.pt";
        String uri = "/persons/" + personEmail1 + "/siblings/" + personEmail2;

        //Expected Result
        String expectedResult = "They are siblings.";
        String expectedLinks = "{\"Siblings List:\":" + "{\"href\":\"http:\\/\\/localhost\\/persons\\/" + personEmail1 + "\\/siblings\"}}";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expectedResult,result.getString("siblings")),
                () -> assertEquals (expectedLinks, result.getString("_links"))
        );
    }

    @Test
    @DisplayName("Test if two people are siblings - only on each other's siblings list")
    public void checkIfTwoPeopleAreSiblingsTRUEOnSiblingsList() throws Exception {

        // Arrange
        String personEmail1 = "father1@isep.ipp.pt";
        String personEmail2 = "father2@isep.ipp.pt";
        String uri = "/persons/" + personEmail1 + "/siblings/" + personEmail2;

        //Expected Result
        String expectedResult = "They are siblings.";
        String expectedLinks = "{\"Siblings List:\":" + "{\"href\":\"http:\\/\\/localhost\\/persons\\/" + personEmail1 + "\\/siblings\"}}";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expectedResult, result.getString("siblings")),
                () -> assertEquals(expectedLinks, result.getString("_links"))
        );
    }

    @Test
    @DisplayName("Test if two people are siblings - false")
    public void checkIfTwoPeopleAreSiblingsFALSE() throws Exception {

        // Arrange
        String personEmail1 = "mother1@isep.ipp.pt";
        String personEmail2 = "mother2@isep.ipp.pt";
        String uri = "/persons/" + personEmail1 + "/siblings/" + personEmail2;

        //Expected Result
        String expectedResult = "They are not siblings.";
        String expectedLinks = "{\"Siblings List:\":" + "{\"href\":\"http:\\/\\/localhost\\/persons\\/" + personEmail1 + "\\/siblings\"}}";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expectedResult, result.getString("siblings")),
                () -> assertEquals(expectedLinks, result.getString("_links"))
        );
    }

    @Test
    @DisplayName("Test if two people are siblings - Person Email not found on Person Repository")
    public void checkIfTwoPeopleAreSiblingsPersonEmailNotFound() throws Exception {

        //Arrange
        String personEmail1 = "404@isep.ipp.pt";
        String personEmail2 = "father2@isep.ipp.pt";
        String uri = "/persons/" + personEmail1 + "/siblings/" + personEmail2;

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
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
                () -> assertEquals ("No person found with that email.", result.getString("message"))
        );

    }

    @Test
    @DisplayName("Test if two people are siblings - Invalid Person Email")
    public void checkIfTwoPeopleAreSiblingsNullSecondPersonEmail() throws Exception {

        //Arrange
        String personEmail1 = "father1@isep.ipp.pt";
        String personEmail2 = "/raquelisep.pt";
        String uri = "/persons/" + personEmail1 + "/siblings/" + personEmail2;

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());


        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The email is not valid.", result.getString("message"))
        );


    }

    //Needs update to JsonArray
    @Test
    @DisplayName("Test getSiblings - Happy case")
    public void getSiblings() throws Exception {
        // Arrange
        String personEmail1 = "father1@isep.ipp.pt";
        String uri = "/persons/"+ personEmail1 + "/siblings";

        String expectedEmail = "margarida_azevedo@gmail.com";
        String expectedLinks= "{\\\"rel\\\":\\\"self\\\",\\\"href\\\":\\\"http://localhost/persons/margarida_azevedo@gmail.com\\\"}]}]";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONArray result = new JSONArray(mvcResult.getResponse().getContentAsString());

        // Assert
        assertEquals(200, status);


    }

    @Test
    @DisplayName("Test getSiblings - Person Not Found")
    public void getSiblingsPersonNotFound() throws Exception {
        //Arrange
        String personEmail1 = "hug.azevedo@gmail.com";
        String uri = "/persons/"+ personEmail1 + "/siblings";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No person found with that email.", result.getString("message"))
        );
    }

    @Test
    @DisplayName("Test getPersonEmail- Happy case")
    public void getPersonEmail() throws Exception {
        //Arrange
        String personEmail1 = "rick@gmail.com";
        String uri = "/persons/"+ personEmail1;

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(personEmail1,result.getString("personID"))
        );
    }


    @Test
    @DisplayName("Test getPersonEmail- Person Not Found")
    public void getPersonEmailNotFound() throws Exception {
        // Arrange
        String uri = "/persons/o@isep.ipp.pt";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
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
                () -> assertEquals ("No person found with that email.", result.getString("message"))
        );
    }
}
