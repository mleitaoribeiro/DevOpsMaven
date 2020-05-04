package switch2019.project.controllerLayer.rest.integration;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.AddMemberInfoDTO;

import java.time.LocalDateTime;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US003AddMemberToGroupControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP() {
        super.setUP();
    }

    @Test
    @DisplayName("Test if an existing person is added to a Group - Main Scenario")
    void addMemberToGroup() throws Exception {

        //Status Request
        String uri = "/groups/switch/members";

        final String personEmail = "rick@gmail.com";
        final String groupDescription = "switch";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);


        String inputJson = super.mapToJson((addMemberInfoDTO));
        String expected = "{\"memberAdded\":\"" + personEmail + " was added to group " + groupDescription +
                "\",\"_links\":{\"self\":{\"href\":\"http://localhost/groups/switch/members/rick@gmail.com\"}}}";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        String result = mvcResult.getResponse().getContentAsString();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test if a person is added to a Group - person is already in the group")
    void addMemberToGroupAlreadyIn() throws Exception {

        //Status Request
        String uri = "/groups/switch/members";

        final String personEmail = "1191743@isep.ipp.pt";
        final String groupDescription = "switch";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);


        String inputJson = super.mapToJson((addMemberInfoDTO));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);


        //Act
        String result = mvcResult.getResponse().getContentAsString();
        String expected = "{\"memberAdded\":\"" + personEmail + " is already on group " + groupDescription +
                "\",\"_links\":{\"self\":{\"href\":\"http://localhost/groups/switch/members/1191743@isep.ipp.pt\"}}}";
        //Assert
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Test if a person is added to a Group - person don't not exist")
    void addMemberToGroupNotExist() throws Exception {

        //Status Request
        String uri = "/groups/SWITCH/members";

        final String personEmail = "asdfg@gmail.com";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);


        String inputJson = super.mapToJson((addMemberInfoDTO));

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String expectedErrorMessage = "{\"timestamp\":\""+ LocalDateTime.now().withSecond(0).withNano(0) +"\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"This resource was not found.\"," +
                "\"message\":\"No person found with that email.\"}";

        String expectedException = "switch2019.project.utils.customExceptions.ArgumentNotFoundException: No person found with that email.";

        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedException, realException)
        );


    }

    @Test
    @DisplayName("Test if a person is added to a Group - group don't not exist")
    void addMemberToGroupThatDoesNotExist() throws Exception {

        //Status Request
        String uri = "/groups/amigos/members";

        final String personEmail = "rick@gmail.com";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);


        String inputJson = super.mapToJson((addMemberInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\""+LocalDateTime.now().withSecond(0).withNano(0)+"\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"This resource was not found.\"," +
                "\"message\":\"No group found with that description.\"}";

        String expectedException = "switch2019.project.utils.customExceptions.ArgumentNotFoundException: No group found with that description.";

        //ACT

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedException, realException)
        );
    }


    @Test
    @DisplayName("Test if a person is added to a Group - personEmail is null")
    void addMemberToGroupNullEmail() throws Exception {

        //Status Request
        String uri = "/groups/SWITCH/members";

        final String personEmail = null;

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);



        String inputJson = super.mapToJson((addMemberInfoDTO));

        //Act
        String expectedErrorMessage = "{\"timestamp\":\""+ LocalDateTime.now().withSecond(0).withNano(0) +"\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"The email can't be null.\"}";

        String expectedException = "java.lang.IllegalArgumentException: The email can't be null.";


        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedException, realException)
        );


    }

    @Test
    @DisplayName("Test for get person - not member of group")
    void getPersonByIDHappyCase() throws Exception {
        //Status Request
        String personEmail = "1110120@isep.ipp.pt";
        String groupDescription = "SWitCH";
        String uri = "/groups/" + groupDescription + "/members/" + personEmail;
        String expected = "{\"personID\":\"1110120@isep.ipp.pt\"}";

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
    @DisplayName("Test for get person - not member of group")
    void getPersonByID() throws Exception {
        //Status Request
        String personEmail = "1110120@isep.ipp.pt";
        String groupDescription = "Rick and Morty";
        String uri = "/groups/" + groupDescription + "/members/" + personEmail;

        String expectedErrorMessage = "{\"timestamp\":\""+ LocalDateTime.now().withSecond(0).withNano(0) +"\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"One of the parameters is invalid or is missing.\"," +
                "\"message\":\"That person is not a member of this group.\"}";


        String expectedException = "java.lang.IllegalArgumentException: That person is not a member of this group.";

        //Act

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedException, realException)
        );


    }

    @Test
    @DisplayName("Test for getMembersByGroupDescription - Main Scenario")
    void getMembersByGroupDescriptionException() throws Exception {

        //Status Request
        String groupDescription = "Rick And Morty";
        String uri = "/groups/" + groupDescription + "/members/";
        String expected = "[{\"personID\":\"morty@gmail.com\",\"links\":" +
                "[{\"rel\":\"self\",\"href\":\"http://localhost/groups/Rick%20And%20Morty/members/morty@gmail.com\"}]}," +
                "{\"personID\":\"rick@gmail.com\",\"links\":" +
                "[{\"rel\":\"self\",\"href\":\"http://localhost/groups/Rick%20And%20Morty/members/rick@gmail.com\"}]}]";

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
    @DisplayName("Test for getMembersByGroupDescription - Exception - No group found with that description")
    void getAdminsByGroupDescriptionException() throws Exception {

        //Status Request
        String groupDescription = "High School buddies";
        String uri = "/groups/" + groupDescription + "/members/";

        String expectedErrorMessage = "{\"timestamp\":\""+LocalDateTime.now().withSecond(0).withNano(0)+"\",\"statusCode\":422,\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"error\":\"This resource was not found.\"," +
                "\"message\":\"No group found with that description.\"}";;

        String expectedException = "switch2019.project.utils.customExceptions." +
                "ArgumentNotFoundException: No group found with that description.";

        //Act

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedException, realException)
        );


    }

}