package switch2019.project.springBoot.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.DeserializationDTO.AddMemberInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US003AddMemberToGroupControllerRestTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP() {
        super.setUP();
    }

    @Test
    @DisplayName("Test if an existing person is added to a Group - Main Scenario")
    void addMemberToGroup () throws Exception{

        //Status Request
        String uri = "/addMemberToGroup";

        final String personEmail = "rick@gmail.com";
        final String groupDescription = "switch";

        AddMemberInfoDTO addMemberInfoDTO = new AddMemberInfoDTO();
        addMemberInfoDTO.setPersonEmail(personEmail);
        addMemberInfoDTO.setGroupDescription(groupDescription);


        String inputJson = super.mapToJson((addMemberInfoDTO));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        String result = mvcResult.getResponse().getContentAsString();
        String expected = "{\"memberAdded\":\"" + personEmail + " was added to group " + groupDescription + "\"}";
        assertEquals(expected, result);
    }
}
