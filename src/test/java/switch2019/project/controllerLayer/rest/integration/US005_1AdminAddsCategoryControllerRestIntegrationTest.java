package switch2019.project.controllerLayer.rest.integration;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreateGroupCategoryInfoDTO;
import switch2019.project.customExceptions.ArgumentNotFoundException;
import switch2019.project.customExceptions.NoPermissionException;
import switch2019.project.customExceptions.ResourceAlreadyExistsException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

        String uri = "/group/Smith Family/categories";

        final String groupDescriptionStr = "Smith Family";
        final String personEmail = "rick@gmail.com";
        final String categoryDenomination = "shopping";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expected = "{\"denomination\":\"" + categoryDenomination.toUpperCase() + "\"" + "," + "\"ownerID\":\"" +
                groupDescriptionStr.toUpperCase() +
                "\",\"_links\":{\"self\":{\"href\":\"http://localhost/groups/SMITH%20FAMILY/categories/SHOPPING\"}}}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
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
    @DisplayName("Category is not added to Group categories - Person doesn't exist on Person repository")
    void adminAddsCategoryToCategoryListNotExistsOnRepository() throws Exception {

        //Arrange:
        String uri = "/group/FRIENDS/categories";

        String personEmail = "Ana@hotmail.com"; // Not a Group member.
        String categoryDenomination = "COMPRAS";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedError = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"This resource was not found.\"," +
                "\"errors\":[\"No person found with that email.\"]}";

        String expectedException = new ArgumentNotFoundException("No person found with that email.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //
        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedError, result),
                () -> assertEquals(expectedException, realException)
        );
    }


    @Test
    @DisplayName("Category is not added to Group categories - Person does't is member of the group")
    void adminAddsCategoryToCategoryListNotAMember() throws Exception {

        //Arrange:
        String uri = "/group/FRIENDS/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedError = "{\"status\":\"FORBIDDEN\"," +
                "\"message\":\"No permission for this group operation.\"," +
                "\"errors\":[\"This person is not member of this group.\"]}";

        String expectedException = new NoPermissionException("This person is not member of this group.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //
        //Assert
        Assertions.assertAll(
                () -> assertEquals(403, status),
                () -> assertEquals(expectedError, result),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is member but not admin group")
    void adminAddsCategoryToCategoryListPersonIsNotAdmin() throws Exception {

        //Arrange:
        String uri = "/group/Family Azevedo/categories";

        String personEmail = "roberto@gmail.com";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedError = "{\"status\":\"FORBIDDEN\"," +
                "\"message\":\"No permission for this group operation.\"," +
                "\"errors\":[\"This person is not admin of this group.\"]}";

        String expectedException = new NoPermissionException("This person is not admin of this group.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //
        //Assert
        Assertions.assertAll(
                () -> assertEquals(403, status),
                () -> assertEquals(expectedError, result),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is admin but not of this group")
    void adminAddsCategoryToCategoryListPersonIsNotAdminOfThisGroup() throws Exception {

        //Arrange:
        String uri = "/group/Family Azevedo/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedError = "{\"status\":\"FORBIDDEN\"," +
                "\"message\":\"No permission for this group operation.\"," +
                "\"errors\":[\"This person is not member of this group.\"]}";

        String expectedException = new NoPermissionException("This person is not member of this group.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //
        //Assert
        Assertions.assertAll(
                () -> assertEquals(403, status),
                () -> assertEquals(expectedError, result),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - Group doesn't exist on Group repository")
    void adminAddsCategoryToCategoryListGroupIsNotInRepository() throws Exception {

        //Arrange:
        String uri = "/group/Mariquinha/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedError = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"This resource was not found.\"," +
                "\"errors\":[\"No group found with that description.\"]}";

        String expectedException = new ArgumentNotFoundException("No group found with that description.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //
        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedError, result),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - Category Already Exists")
    void adminAddsDuplicateCategoryToCategoryListTest() throws Exception {

        //Arrange:
        String uri = "/group/Switch/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = "ISEP";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));
        String expectedErrorMessage = "{\"status\":\"CONFLICT\"," +
                "\"message\":\"This resource already exists.\"," +
                "\"errors\":[\"This category already exists.\"]}";

        String expectedResolvedException = new ResourceAlreadyExistsException("This category already exists.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andExpect(status().isConflict())
                .andExpect(content().string(expectedErrorMessage))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(409, status),
                () -> assertEquals(expectedErrorMessage, result),
                () -> assertEquals(expectedResolvedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Email-Null")
    void adminAddsCategoryToCategoryListEmailNull() throws Exception {

        //Arrange:
        String uri = "/group/friends/categories";

        String personEmail = null;
        String categoryDenomination = "NightOut";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The email can't be null.\"]}";

        String expectedException = new IllegalArgumentException("The email can't be null.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedMessage, result),
                () -> assertEquals(expectedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Email-Empty")
    void adminAddsCategoryToCategoryListEmailEmpty() throws Exception {

        //Arrange:
        String uri = "/group/friends/categories";

        String personEmail = "";
        String categoryDenomination = "NightOut";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The email is not valid.\"]}";

        String expectedException = new IllegalArgumentException("The email is not valid.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedMessage, result),
                () -> assertEquals(expectedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Email-Invalid Format")
    void adminAddsCategoryToCategoryListEmailInvalidFormat() throws Exception {

        //Arrange:
        String uri = "/group/friends/categories";

        String personEmail = "111917.dfkd";
        String categoryDenomination = "NightOut";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The email is not valid.\"]}";

        String expectedException = new IllegalArgumentException("The email is not valid.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedMessage, result),
                () -> assertEquals(expectedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Category Denomination-Null")
    void adminAddsCategoryToCategoryListCategoryDenominationNull() throws Exception {

        //Arrange:
        String uri = "/group/Switch/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = null;

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The denomination can't be null or empty.\"]}";

        String expectedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedMessage, result),
                () -> assertEquals(expectedException, realResolvedException)
        );
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Category Denomination-Empty")
    void adminAddsCategoryToCategoryListCategoryDenominationEmpty() throws Exception {

        //Arrange:
        String uri = "/group/Switch/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String categoryDenomination = "";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        String expectedMessage = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"One of the parameters is invalid or is missing.\"," +
                "\"errors\":[\"The denomination can't be null or empty.\"]}";

        String expectedException = new IllegalArgumentException("The denomination can't be null or empty.").toString();

        //ACT:
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        String realResolvedException = Objects.requireNonNull(mvcResult.getResolvedException()).toString();

        //ASSERT:
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedMessage, result),
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
        String uri = "/groups/Smith Family/categories/Online";

        String categoryDescription = "ONLINE";
        String ownerID = "SMITH FAMILY";

        String expected = "{\"denomination\":\"" + categoryDescription + "\"" + "," + "\"ownerID\":\"" +
                ownerID + "\"}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //Assert
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - group not found")
    void getCategoryByCategoryIDGroupNotFound() throws Exception {

        //Arrange
        String uri = "/groups/Just4Fun/categories/Online";

        String expectedError = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"This resource was not found.\"," +
                "\"errors\":[\"No group found with that description.\"]}";

        String expectedException = new ArgumentNotFoundException("No group found with that description.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedError, result),
                () -> assertEquals(expectedException, realException)
        );
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - category not found")
    void getCategoryByCategoryIDCategoryNotFound() throws Exception {

        //Arrange
        String uri = "/groups/SMITH FAMILY/categories/Dispenses";

        String expectedError = "{\"status\":\"UNPROCESSABLE_ENTITY\"," +
                "\"message\":\"This resource was not found.\"," +
                "\"errors\":[\"No category found with that ID.\"]}";

        String expectedException = new ArgumentNotFoundException("No category found with that ID.").toString();

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        String realException = Objects.requireNonNull(mvcResult.getResolvedException().toString());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(422, status),
                () -> assertEquals(expectedError, result),
                () -> assertEquals(expectedException, realException)
        );
    }
}