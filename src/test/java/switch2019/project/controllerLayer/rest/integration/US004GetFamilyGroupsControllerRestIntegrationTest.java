package switch2019.project.controllerLayer.rest.integration;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2019.project.AbstractTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US004GetFamilyGroupsControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP(){
        super.setUP();
    }

    /**
     * US004
     * As system manager I want to know which groups are families
     *
     * @throws Exception
     */
    @Test
    @DisplayName("Get all the groups who are families in the groups repository")
    void getFamilyGroups() throws Exception {

        //Status Request
        String uri = "/groups?type=family";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        //Status Response
        int status = mvcResult.getResponse().getStatus();

        //outputDTO
        String result = mvcResult.getResponse().getContentAsString();


        String expected = "[{\"groupDescription\":\"" + "FAMILY SIMPSON\",\"links\":" +
                "[{\"rel\":\"self\",\"href\":\"http://localhost/groups/FAMILY%20SIMPSON\"}]}," +
                "{\"groupDescription\":\"FAMILY CARDOSO\",\"links\":" +
                "[{\"rel\":\"self\",\"href\":\"http://localhost/groups/FAMILY%20CARDOSO\"}]}," +
                "{\"groupDescription\":\"FAMILY AZEVEDO\",\"links\":" +
                "[{\"rel\":\"self\",\"href\":\"http://localhost/groups/FAMILY%20AZEVEDO\"}]}]";


        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }
}