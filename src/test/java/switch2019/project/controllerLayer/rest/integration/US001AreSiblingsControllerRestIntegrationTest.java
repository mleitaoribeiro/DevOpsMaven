package switch2019.project.controllerLayer.rest.integration;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import switch2019.project.AbstractTest;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
        String uri = "/persons/antonio@isep.ipp.pt/siblings/manuel@isep.ipp.pt";
        String expected = "{\"siblings\":\"They are siblings.\",\"_links\":{\"self\":{\"href\":\"http://localhost/persons/antonio@isep.ipp.pt/siblings\"}}}";
        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Test if two people are siblings - only same Mother")
    public void checkIfTwoPeopleAreSiblingsTRUESameMother() throws Exception {

        // Arrange
        String uri = "/persons/antonio@isep.ipp.pt/siblings/roberto@isep.ipp.pt";
        String expected = "{\"siblings\":\"They are siblings.\",\"_links\":{\"self\":{\"href\":\"http://localhost/persons/antonio@isep.ipp.pt/siblings\"}}}";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Test if two people are siblings - only same Father")
    public void checkIfTwoPeopleAreSiblingsTRUESameFather() throws Exception {

        // Arrange
        String uri = "/persons/antonio@isep.ipp.pt/siblings/amalia@isep.ipp.pt";
        String expected = "{\"siblings\":\"They are siblings.\",\"_links\":{\"self\":{\"href\":\"http://localhost/persons/antonio@isep.ipp.pt/siblings\"}}}";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Test if two people are siblings - only on each other's siblings list")
    public void checkIfTwoPeopleAreSiblingsTRUEOnSiblingsList() throws Exception {

        // Arrange
        String uri = "/persons/father1@isep.ipp.pt/siblings/father2@isep.ipp.pt";
        String expected = "{\"siblings\":\"They are siblings.\",\"_links\":{\"self\":{\"href\":\"http://localhost/persons/father1@isep.ipp.pt/siblings\"}}}";
        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Test if two people are siblings - false")
    public void checkIfTwoPeopleAreSiblingsFALSE() throws Exception {

        // Arrange
        String uri = "/persons/mother1@isep.ipp.pt/siblings/mother2@isep.ipp.pt";
        String expected = "{\"siblings\":\"They are not siblings.\",\"_links\":{\"self\":{\"href\":\"http://localhost/persons/mother1@isep.ipp.pt/siblings\"}}}";

        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Test if two people are siblings - Person Email not found on Person Repository")
    public void checkIfTwoPeopleAreSiblingsPersonEmailNotFound() throws Exception {

        // Arrange
        String uri = "/persons/404@isep.ipp.pt/siblings/father2@isep.ipp.pt";

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"This resource was not found.\"," +
                "\"errors\":[\"No person found with that email.\"]}";

        String expectedException = "switch2019.project.utils.customExceptions.ArgumentNotFoundException: No person found with that email.";

        //ACT:
        /*
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();


        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        /*
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedException, realException)
        );

         */
    }

    @Test
    @DisplayName("Test if two people are siblings - Null First Person Email")
    public void checkIfTwoPeopleAreSiblingsNullFirstPersonEmail() throws Exception {

        // Arrange
        String uri = "/persons/null/siblings/father2@isep.ipp.pt";

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The email is not valid.\"]}";

        String expectedException = "java.lang.IllegalArgumentException: The email is not valid.";

        //Act:
        /*
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();


        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert:
        /*
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedException, realException)
        );

         */
    }

    @Test
    @DisplayName("Test if two people are siblings - Invalid Person Email")
    public void checkIfTwoPeopleAreSiblingsNullSecondPersonEmail() throws Exception {

        // Arrange
        String uri = "/persons/father1@isep.ipp.pt/siblings/raquelisep.pt";

        String expectedErrorMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The email is not valid.\"]}";

        String expectedException = "java.lang.IllegalArgumentException: The email is not valid.";

        //Act:
        /*
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();


        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //Assert:
        /*
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedException, realException)
        );

         */
    }

    @Test
    @DisplayName("Test getSiblings - Happy case")
    public void getSiblings() throws Exception {
        // Arrange
        String uri = "/persons/hugo.azevedo@gmail.com/siblings";

        String expected = "[{\"personID\":\"margarida_azevedo@gmail.com\",\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/persons/hugo.azevedo@gmail.com\"}]}]";
        // Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        // Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Test getSiblings - Person Not Found")
    public void getSiblingsPersonNotFound() throws Exception {
        // Arrange
        String uri = "/persons/hug.azevedo@gmail.com/siblings";

        String expectedErrorMessage = "{\"timestamp\":\""+ LocalDateTime.now().withSecond(0).withNano(0) +"\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"This resource was not found.\"," +
                "\"message\":\"No person found with that email.\"}";




        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();


        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();


        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result)
        );
    }

    @Test
    @DisplayName("Test getPersonEmail- Happy case")
    public void getPersonEmail() throws Exception {
        //Arrange
        String uri = "/persons/hugo.azevedo@gmail.com";

        String expected = "{\"personID\":\"hugo.azevedo@gmail.com\"}";
        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Test getPersonEmail- Person Not Found")
    public void getPersonEmailNotFound() throws Exception {
        // Arrange
        String uri = "/persons/h.azevedo@gmail.com";

        String expectedErrorMessage = "{\"timestamp\":\""+ LocalDateTime.now().withSecond(0).withNano(0) +"\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"This resource was not found.\"," +
                "\"message\":\"No person found with that email.\"}";

                

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();


        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();


        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result)
        );
    }


}
