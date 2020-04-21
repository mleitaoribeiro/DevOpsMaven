package switch2019.project.controllerLayer.rest.integration;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String uri = "/areSiblings?personOneEmail=antonio@isep.ipp.pt&personTwoEmail=manuel@isep.ipp.pt";
        String expected = "{\"siblings\":\"They are siblings.\"}";

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
        String uri = "/areSiblings?personOneEmail=antonio@isep.ipp.pt&personTwoEmail=roberto@isep.ipp.pt";
        String expected = "{\"siblings\":\"They are siblings.\"}";

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
        String uri = "/areSiblings?personOneEmail=antonio@isep.ipp.pt&personTwoEmail=amalia@isep.ipp.pt";
        String expected = "{\"siblings\":\"They are siblings.\"}";

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
        String uri = "/areSiblings?personOneEmail=father1@isep.ipp.pt&personTwoEmail=father2@isep.ipp.pt";
        String expected = "{\"siblings\":\"They are siblings.\"}";

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
        String uri = "/areSiblings?personOneEmail=mother1@isep.ipp.pt&personTwoEmail=mother2@isep.ipp.pt";
        String expected = "{\"siblings\":\"They are not siblings.\"}";

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
        String uri = "/areSiblings?personOneEmail=404@isep.ipp.pt&personTwoEmail=father2@isep.ipp.pt";

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NestedServletException.class)
                .hasMessage("Request processing failed; nested exception is " +
                        "java.lang.IllegalArgumentException: No person found with that email.");
    }

    @Test
    @DisplayName("Test if two people are siblings - Null First Person Email")
    public void checkIfTwoPeopleAreSiblingsNullFirstPersonEmail() throws Exception {

        // Arrange
        String uri = "/areSiblings?personOneEmail=&personTwoEmail=father2@isep.ipp.pt";

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NestedServletException.class)
                .hasMessage("Request processing failed; nested exception is " +
                        "java.lang.IllegalArgumentException: The email is not valid.");
    }

    @Test
    @DisplayName("Test if two people are siblings - Null Second Person Email")
    public void checkIfTwoPeopleAreSiblingsNullSecondPersonEmail() throws Exception {

        // Arrange
        String uri = "/areSiblings?personOneEmail=father1@isep.ipp.pt&personTwoEmail=";

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(NestedServletException.class)
                .hasMessage("Request processing failed; nested exception is " +
                        "java.lang.IllegalArgumentException: The email is not valid.");
    }

}