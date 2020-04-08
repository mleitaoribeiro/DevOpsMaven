package switch2019.project.springBoot.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.DeserializationDTO.CreateGroupAccountInfoDTO;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US007CreateGroupAccountControllerRestTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP(){
        super.setUP();
    }

    @Test
    @DisplayName("Test Group Account creation - test if outputDTO and HTTP response are expected")
    void addGroupAccountServiceTestEqual() throws Exception {

        String uri = "/addGroupAccount";

        final String groupDescription = "Family Simpson";
        final String personEmail = "homer@hotmail.com";
        final String accountDenomination = "Kwik E Mart";
        final String accountDescription = "Duff Beer Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        //Status Response
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        //outputDTO
        String result = mvcResult.getResponse().getContentAsString();

        String expected = "{\"ownerID\":\"" +groupDescription.toUpperCase() +"\"" +"," +"\"denomination\":\"" +accountDenomination.toUpperCase() +
                "\"" +"," +"\"description\":\"" +accountDescription.toUpperCase() + "\"}";

        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Test Group Account creation - person is not Admin")
    void addGroupAccount_personIsNotAdmin() throws Exception {

        //Arrange
        String uri = "/addGroupAccount";

        final String groupDescription = "Smith Family";
        final String personEmail = "jerry.smith@gmail.com";
        final String accountDenomination = "House";
        final String accountDescription = "General Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        //Act & Assert
        assertThatThrownBy(() -> mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)))
                .hasCause(new IllegalArgumentException("This person is not Admin of this group"))
                .isExactlyInstanceOf(NestedServletException.class);
    }

    @Test
    @DisplayName("Test Group Account creation -  person does not exists on Person Repository")
    void addGroupAccountPersonDoesNotExists() throws Exception {

        //Arrange
        String uri = "/addGroupAccount";

        final String groupDescription = "Switch";
        final String personEmail = "raquel@hotmail.com";
        final String accountDenomination = "Gym";
        final String accountDescription = "Fitness Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        //Act & Assert
        assertThatThrownBy(() -> mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)))
                .hasCause(new IllegalArgumentException("No person found with that email."))
                .isExactlyInstanceOf(NestedServletException.class);
    }



    @Test
    @DisplayName("Test Group Account creation -  group category already exists - test if output and HTTP response are expected ")
    void addGroupAccountServiceTestAccountCategoryException() throws Exception {

        //Arrange
        String uri = "/addGroupAccount";

        final String groupDescription = "Family Cardoso";
        final String personEmail = "1191780@isep.ipp.pt";
        final String accountDenomination = "Revolut";
        final String accountDescription = "Online Expenses";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        //Act & Assert
        assertThatThrownBy(() -> mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)))
                .hasCause(new IllegalArgumentException("This Account already exists for that ID."))
                .isExactlyInstanceOf(NestedServletException.class);
    }

    @Test
    @DisplayName("Test Group Account creation -  group does not exists - test if output and HTTP response are expected ")
    void addGroupAccountServiceTestGroupNotFoundException() throws Exception {

        //Arrange
        String uri = "/addGroupAccount";

        final String groupDescription = "West World";
        final String personEmail = "homer@hotmail.com";
        final String accountDenomination = "Clone AI from park";
        final String accountDescription = "Dolores several copies";

        CreateGroupAccountInfoDTO createGroupAccountInfoDTO = new CreateGroupAccountInfoDTO();

        createGroupAccountInfoDTO.setGroupDescription(groupDescription);
        createGroupAccountInfoDTO.setPersonEmail(personEmail);
        createGroupAccountInfoDTO.setAccountDenomination(accountDenomination);
        createGroupAccountInfoDTO.setAccountDescription(accountDescription);

        String inputJson = super.mapToJson((createGroupAccountInfoDTO));

        //Act & Assert
        assertThatThrownBy(() -> mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)))
                .hasCause(new IllegalArgumentException("No group was found with the given description."))
                .isExactlyInstanceOf(NestedServletException.class);
    }

}
