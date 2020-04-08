package switch2019.project.springBoot.integration;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.DeserializationDTO.CreateGroupInfoDTO;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US002_1CreateGroupControllerRestTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP(){
        super.setUP();
    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - Main Scenario")
    void createGroupAndBecomeAdmin_HappyCase() throws Exception {

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
    void createGroupAndBecomeAdmin_personDoesNotExists() throws Exception {

        //Arrange
        String uri = "/createGroup";

        final String groupDescriptionStr = "Expenses";
        final String personEmail = "qwerty@gmail.com";

        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        //Act & Assert
        assertThatThrownBy(() -> mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)))
                .hasCause(new IllegalArgumentException("No person found with that email."))
                .isExactlyInstanceOf(NestedServletException.class);

    }


    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - group Already Exists")
    void createGroupAndBecomeAdmin_groupAlreadyExists() throws Exception {

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
                .hasCause(new IllegalArgumentException("This Group Description already exists."))
                .isExactlyInstanceOf(NestedServletException.class);
    }





}