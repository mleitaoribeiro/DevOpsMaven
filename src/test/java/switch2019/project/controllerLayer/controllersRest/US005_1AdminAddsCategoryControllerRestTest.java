package switch2019.project.controllerLayer.controllersRest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.DeserializationDTO.CreateGroupCategoryInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US005_1AdminAddsCategoryControllerRestTest extends AbstractTest {

    @Override
    @BeforeAll
    public void setUP(){
        super.setUP();
    }

    @Test
    @DisplayName("Test if the outputDTO is the expected")
    void addCategoryToGroupServiceTestEqual() throws Exception {

        String uri = "/addCategoryToGroup";

        final String groupDescriptionStr = "Smith Family";
        final String personEmail = "rick@gmail.com";
        final String categoryDenomination = "shopping";

        CreateGroupCategoryInfoDTO createGroupCategoryInputDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInputDTO.setGroupDescription(groupDescriptionStr);
        createGroupCategoryInputDTO.setPersonEmail(personEmail);
        createGroupCategoryInputDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInputDTO));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        String expected = mvcResult.getResponse().getContentAsString();

        assertEquals(expected, "{\"denomination\":\"" +categoryDenomination.toUpperCase()+"\"" +"," +"\"ownerID\":\"" +groupDescriptionStr.toUpperCase() + "\"}");

    }

}