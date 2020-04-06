package switch2019.project.springBoot.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.DeserializationDTO.CreateGroupInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US002_1CreateGroupControllerRestTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP(){
        super.setUP();
    }

    @Test
    @DisplayName("Test if an existing person creates a Group and becomes Admin - Main Scenario")
    void addCategoryToGroupServiceTestEqual() throws Exception {

        String uri = "/createGroup";

        final String groupDescriptionStr = "Online Shopping";
        final String personEmail = "1110120@isep.ipp.pt";


        CreateGroupInfoDTO createGroupInfoDTO = new CreateGroupInfoDTO ();
        createGroupInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupInfoDTO.setPersonEmail(personEmail);

        String inputJson = super.mapToJson((createGroupInfoDTO));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        String result = mvcResult.getResponse().getContentAsString();
        String expected = "{\"groupDescription\":\"" +groupDescriptionStr.toUpperCase() +"\"}";
        assertEquals(expected, result);

    }



}