package switch2019.project.springBoot.integration;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.DeserializationDTO.CreateGroupInfoDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US002_1CreateGroupControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP(){
        super.setUP();
    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - Main Scenario")
    void createGroupAndBecomeAdminHappyCase() throws Exception {

        //Arrange
        String uri = "/createGroup";

        final String groupDescriptionStr = "Online Shopping";
        final String personEmail = "1110120@isep.ipp.pt";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO ();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        String expected = "{\"groupDescription\":\"" +groupDescriptionStr.toUpperCase() +"\"}";

       //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().string(expected))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(expected, result)
        );
    }


    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - person doesn't exist")
    void createGroupAndBecomeAdminPersonDoesNotExists() throws Exception {

        //Arrange
        String uri = "/createGroup";

        final String groupDescriptionStr = "Expenses";
        final String personEmail = "qwerty@gmail.com";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("No person found with that email."))
                .isExactlyInstanceOf(NestedServletException.class);

    }


    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - group Already Exists")
    void createGroupAndBecomeAdminGroupAlreadyExists() throws Exception {

        //Arrange
        String uri = "/createGroup";

        final String groupDescriptionStr = "SWitCH";
        final String personEmail = "morty@gmail.com";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("This group description already exists."))
                .isExactlyInstanceOf(NestedServletException.class);
    }


    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid email - null")
    void createGroupAndBecomeAdminInvalidEmailNull() throws Exception {

        //Arrange
        String uri = "/createGroup";

        final String groupDescriptionStr = "SWitCH";
        final String personEmail = null;

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The email can't be null."))
                .isExactlyInstanceOf(NestedServletException.class);
    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid email - empty")
    void createGroupAndBecomeAdminInvalidEmailEmpty() throws Exception {

        //Arrange
        String uri = "/createGroup";

        final String groupDescriptionStr = "SWitCH";
        final String personEmail = "";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The email is not valid."))
                .isExactlyInstanceOf(NestedServletException.class);
    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid email - invalid format")
    void createGroupAndBecomeAdminInvalidEmailFormat() throws Exception {

        //Arrange
        String uri = "/createGroup";

        final String groupDescriptionStr = "SWitCH";
        final String personEmail = "morty@@gmail.com";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The email is not valid."))
                .isExactlyInstanceOf(NestedServletException.class);
    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid groupDescription - null")
    void createGroupAndBecomeAdminGroupDescriptionNull() throws Exception {

        //Arrange
        String uri = "/createGroup";

        final String groupDescriptionStr = null;
        final String personEmail = "morty@gmail.com";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The description can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);

    }


    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid groupDescription - Empty")
    void createGroupAndBecomeAdminGroupDescriptionEmpty() throws Exception {

        //Arrange
        String uri = "/createGroup";

        final String groupDescriptionStr = "";
        final String personEmail = "morty@gmail.com";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The description can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);
    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - invalid groupDescription - Empty")
    void createGroupAndBecomeAdminNullJsonInput() throws Exception {

        //Arrange
        String uri = "/createGroup";

        String inputJson = super.mapToJson((null));

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();

        //Assert
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(400, status),
                () -> assertEquals("", result)
        );

    }

}