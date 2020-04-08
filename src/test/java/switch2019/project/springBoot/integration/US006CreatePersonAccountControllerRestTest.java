package switch2019.project.springBoot.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.DeserializationDTO.CreateGroupAccountInfoDTO;
import switch2019.project.DTO.DeserializationDTO.CreatePersonAccountInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US006CreatePersonAccountControllerRestTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP(){
        super.setUP();
    }

    //ISSUE 814

    @DisplayName("Test If User Account is created - Main Scenario")
    @Test
    void createPersonAccountHappyCase() throws Exception {
        //ARRANGE:
            //URI used to call the controller:
        String uri = "/createPersonAccount";

            //arrangement of the account DTO:
        final String personEmail = "marge@hotmail.com";
        final String accountDenomination = "Food Expenses";
        final String accountDescription = "Money spent on food";

            //setting information for the DTO:
        CreatePersonAccountInfoDTO infoDTO = new CreatePersonAccountInfoDTO();
        infoDTO.setPersonEmail(personEmail);
        infoDTO.setAccountDenomination(accountDenomination);
        infoDTO.setAccountDescription(accountDescription);

            //arrangement of the input:
        String inputJson = super.mapToJson((infoDTO));

            //arrangement of the expected output:
        String expected = "{\"ownerID\":\"" + personEmail.toUpperCase() +"\"" +"," +"\"denomination\":\"" +accountDenomination.toUpperCase() +
                "\"" +"," +"\"description\":\"" +accountDescription.toUpperCase() + "\"}";

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().string(expected))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(expected, result)
        );
    }
}
