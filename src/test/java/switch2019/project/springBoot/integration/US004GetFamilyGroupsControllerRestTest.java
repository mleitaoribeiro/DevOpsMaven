package switch2019.project.springBoot.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US004GetFamilyGroupsControllerRestTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP(){
        super.setUP();
    }

    @Test
    @DisplayName("Get all the groups who are families in the groups repository")
    void getFamilyGroups() throws Exception {

        //Status Request
        String uri = "/getFamilyGroups";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Status Response
        int status = mvcResult.getResponse().getStatus();

        //Assert
        assertEquals(201, status);

        //outputDTO
        String result = mvcResult.getResponse().getContentAsString();

        String cardoso = "FAMILY CARDOSO";
        String azevedo = "FAMILIA AZEVEDO";
        String simpson = "FAMILY SIMPSON";

        String expected = "[{\"groupDescription\":\"" + cardoso +"\"},{\"groupDescription\":\"" + azevedo + "\"" +
                "},{\"groupDescription\":\"" + simpson + "\"}]";

        //Assert
        assertEquals(expected, result);
    }
}