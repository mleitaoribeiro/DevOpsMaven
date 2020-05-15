package switch2019.project.controllerLayer.rest.integration;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreateGroupCategoryInfoDTO;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class US005_1AdminAddsCategoryControllerRestIntegrationTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP() {
        super.setUP();
    }

    /**
     * US005.1
     * As a Group Administrator, I want to create a category and add it to the group.
     *
     * @throws Exception
     */
    @Test
    @DisplayName("Test if the outputDTO is the expected")
    void addCategoryToGroupServiceTestEqual() throws Exception {

        String uri = "/groups/Smith Family/categories";

        final String groupDescriptionStr = "Smith Family";
        final String personEmail = "rick@gmail.com";
        final String categoryDenomination = "shopping";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expected = "{\"self\":" +
                "{\"href\":\"http:\\/\\/localhost\\/groups\\/SMITH%20FAMILY\\/categories\\/SHOPPING\"}}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();

        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(categoryDenomination.toUpperCase(), result.getString("denomination")),
                () -> assertEquals(groupDescriptionStr.toUpperCase(),result.getString("ownerID")),
                () -> assertEquals (expected, result.getString("_links"))
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person doesn't exist on Person repository")
    void adminAddsCategoryToCategoryListNotExistsOnRepository() throws Exception {

        //Arrange:
        String uri = "/groups/FRIENDS/categories";

        String personEmail = "Ana@hotmail.com"; // Not a Group member.
        String categoryDenomination = "COMPRAS";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        String expectedException = new ArgumentNotFoundException("No person found with that email.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No person found with that email.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }


    @Test
    @DisplayName("Category is not added to Group categories - Person does't is member of the group")
    void adminAddsCategoryToCategoryListNotAMember() throws Exception {

        //Arrange:
        String uri = "/groups/FRIENDS/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        String expectedException = new NoPermissionException("This person is not member of this group.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //
        //Assert
        Assertions.assertAll(
                () -> assertEquals(403, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("403", result.getString("statusCode")),
                () -> assertEquals("FORBIDDEN", result.getString("status")),
                () -> assertEquals ("No permission for this operation.", result.getString("error")),
                () -> assertEquals ("This person is not member of this group.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is member but not admin group")
    void adminAddsCategoryToCategoryListPersonIsNotAdmin() throws Exception {

        //Arrange:
        String uri = "/groups/Family Azevedo/categories";

        String personEmail = "roberto@gmail.com";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        String expectedException = new NoPermissionException("This person is not admin of this group.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(403, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("403", result.getString("statusCode")),
                () -> assertEquals("FORBIDDEN", result.getString("status")),
                () -> assertEquals ("No permission for this operation.", result.getString("error")),
                () -> assertEquals ("This person is not admin of this group.", result.getString("message")),
                () -> assertEquals(expectedException, realException)

        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is admin but not of this group")
    void adminAddsCategoryToCategoryListPersonIsNotAdminOfThisGroup() throws Exception {

        //Arrange:
        String uri = "/groups/Family Azevedo/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedException = new NoPermissionException("This person is not member of this group.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(403, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("403", result.getString("statusCode")),
                () -> assertEquals("FORBIDDEN", result.getString("status")),
                () -> assertEquals ("No permission for this operation.", result.getString("error")),
                () -> assertEquals ("This person is not member of this group.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - Group doesn't exist on Group repository")
    void adminAddsCategoryToCategoryListGroupIsNotInRepository() throws Exception {

        //Arrange:
        String uri = "/groups/Mariquinha/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedException = new ArgumentNotFoundException("No group found with that description.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No group found with that description.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - Category Already Exists")
    void adminAddsDuplicateCategoryToCategoryListTest() throws Exception {

        //Arrange:
        String uri = "/groups/Switch/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = "ISEP";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedErrorMessage = "{\"timestamp\":\"" + LocalDateTime.now().withNano(0).withSecond(0) +
                "\",\"statusCode\":409,\"status\":\"CONFLICT\"," +
                "\"error\":\"This resource already exists.\"," +
                "\"message\":\"This category already exists.\"}";

        String expectedResolvedException = new ResourceAlreadyExistsException("This category already exists.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isConflict())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();


        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(409, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("409", result.getString("statusCode")),
                () -> assertEquals("CONFLICT", result.getString("status")),
                () -> assertEquals ("This resource already exists.", result.getString("error")),
                () -> assertEquals ("This category already exists.", result.getString("message")),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Email-Null")
    void adminAddsCategoryToCategoryListEmailNull() throws Exception {

        //Arrange:
        String uri = "/groups/friends/categories";

        String personEmail = null;
        String categoryDenomination = "NightOut";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        String expectedException = new IllegalArgumentException("The email can't be null.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:

        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The email can't be null.", result.getString("message")),
                () -> assertEquals(expectedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Email-Empty")
    void adminAddsCategoryToCategoryListEmailEmpty() throws Exception {

        //Arrange:
        String uri = "/groups/friends/categories";

        String personEmail = "";
        String categoryDenomination = "NightOut";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        String expectedException = new IllegalArgumentException("The email is not valid.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The email is not valid.", result.getString("message")),
                () -> assertEquals(expectedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Email-Invalid Format")
    void adminAddsCategoryToCategoryListEmailInvalidFormat() throws Exception {

        //Arrange:
        String uri = "/groups/friends/categories";

        String personEmail = "111917.dfkd";
        String categoryDenomination = "NightOut";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        String expectedException = new IllegalArgumentException("The email is not valid.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The email is not valid.", result.getString("message")),
                () -> assertEquals(expectedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Category Denomination-Null")
    void adminAddsCategoryToCategoryListCategoryDenominationNull() throws Exception {

        //Arrange:
        String uri = "/groups/Switch/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = null;

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        String expectedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Category Denomination-Empty")
    void adminAddsCategoryToCategoryListCategoryDenominationEmpty() throws Exception {

        //Arrange:
        String uri = "/groups/Switch/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = "";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        String expectedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("One of the parameters is invalid or is missing.", result.getString("error")),
                () -> assertEquals ("The denomination can't be null or empty.", result.getString("message")),
                () -> assertEquals(expectedException, realResolvedException)
        );
    }

    /**
     * Test if a category can be found by the ID
     */
    @Test
    @DisplayName("Test if a category can be found by the ID - Happy Case")
    void getCategoryByCategoryID() throws Exception {

        //Arrange
        String uri = "/groups/SWITCH/categories/Online";

        String categoryDenomination = "ONLINE";
        String ownerID = "SWITCH";


        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        //Assert
        Assertions.assertAll(
               () -> assertEquals(200, status),
                () -> assertEquals(categoryDenomination.toUpperCase(), result.getString("denomination")),
               () -> assertEquals(ownerID.toUpperCase(),result.getString("ownerID"))
        );
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - group not found")
    void getCategoryByCategoryIDGroupNotFound() throws Exception {

        //Arrange
        String uri = "/groups/Just4Fun/categories/Online";

        String expectedException = new ArgumentNotFoundException("No group found with that description.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No group found with that description.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - category not found")
    void getCategoryByCategoryIDCategoryNotFound() throws Exception {

        //Arrange
        String uri = "/groups/SMITH FAMILY/categories/Dispenses";

        String expectedException = new ArgumentNotFoundException("No category found with that ID.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No category found with that ID.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Test for getCategoriesByGroupID - Main Scenario")
    void getCategoriesByGroupDescriptionException() throws Exception {

        //Status Request
        String uri = "/groups/Switch/categories/";

        String expectedLink1 = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/groups\\/ISEP\\/categories\\/Switch\"}]";
        String expectedLink2 = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/groups\\/ONLINE\\/categories\\/Switch\"}]";
        String expectedLink3 = "[{\"rel\":\"self\",\"href\":\"http:\\/\\/localhost\\/groups\\/GYM\\/categories\\/Switch\"}]";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        JSONArray jArray = (JSONArray) new JSONTokener(result).nextValue();


        //Assert
       Assertions.assertAll(
                () -> assertEquals(200, status),

                () -> assertEquals("ISEP",jArray.getJSONObject(0).getString("categoryDenomination")),
                () -> assertEquals(expectedLink1, jArray.getJSONObject(0).getString("links")),

                () -> assertEquals("ONLINE",jArray.getJSONObject(1).getString("categoryDenomination")),
                () -> assertEquals(expectedLink2, jArray.getJSONObject(1).getString("links")),

                () -> assertEquals("GYM",jArray.getJSONObject(2).getString("categoryDenomination")),
                () -> assertEquals(expectedLink3, jArray.getJSONObject(2).getString("links"))

                );
    }

    @Test
    @DisplayName("Test for getCategoriesByGroupID - Not Found")
    void getCategoriesByGroupDescriptionNotFound() throws Exception {

        //Status Request

        String uri = "/groups/SpiceGirls/categories/";

        String expectedException = new ArgumentNotFoundException("No category found with that ID.").toString();
        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());

        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());
        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(LocalDateTime.now().withNano(0).withSecond(0).toString(),result.getString("timestamp")),
                () -> assertEquals("422", result.getString("statusCode")),
                () -> assertEquals("UNPROCESSABLE_ENTITY", result.getString("status")),
                () -> assertEquals ("This resource was not found.", result.getString("error")),
                () -> assertEquals ("No category found with that ID.", result.getString("message")),
                () -> assertEquals(expectedException, realException)
        );
    }

}