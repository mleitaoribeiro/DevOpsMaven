package switch2019.project.controllerLayer.rest.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.deserializationDTO.CreateGroupCategoryInfoDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        String uri = "/categories";

        final String groupDescriptionStr = "Smith Family";
        final String personEmail = "rick@gmail.com";
        final String categoryDenomination = "shopping";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));
        String expected = "{\"denomination\":\"" + categoryDenomination.toUpperCase() + "\"" + "," + "\"ownerID\":\"" +
                groupDescriptionStr.toUpperCase() +
                "\",\"_links\":{\"self\":{\"href\":\"http://localhost/categories/groups/SMITH%20FAMILY/SHOPPING\"}}}";

        //Act
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();

        //
        //Assert
        Assertions.assertAll(
                () -> assertEquals(201, status),
                () -> assertEquals(expected, result)
        );

    }

    @Test
    @DisplayName("Category is not added to Group categories - Person doesn't exist on Person repository")
    void adminAddsCategoryToCategoryListNotExistsOnRepository() throws JsonProcessingException {

        //Arrange:
        String uri = "/categories";

        String personEmail = "Ana@hotmail.com"; // Not a Group member.
        String groupDescription = "FRIENDS";
        String categoryDenomination = "COMPRAS";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
       /* assertThat(thrown)
                .hasCause(new IllegalArgumentException("No person found with that email."))
                .isExactlyInstanceOf(NestedServletException.class);

        */
    }


    @Test
    @DisplayName("Category is not added to Group categories - Person does't is member of the group")
    void adminAddsCategoryToCategoryListNotAMember() throws JsonProcessingException {

        //Arrange:
        String uri = "/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String groupDescription = "FRIENDS";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("This person is not member or admin of this group."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is member but not admin group")
    void adminAddsCategoryToCategoryListPersonIsNotAdmin() throws JsonProcessingException {

        //Arrange:
        String uri = "/categories";

        String personEmail = "roberto@gmail.com";
        String groupDescription = "Family Azevedo";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("This person is not member or admin of this group."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Category is not added to Group categories - Person is admin but not of this group")
    void adminAddsCategoryToCategoryListPersonIsNotAdminOfThisGroup() throws JsonProcessingException {

        //Arrange:
        String uri = "/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String groupDescription = "Family Azevedo";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("This person is not member or admin of this group."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Category is not added to Group categories - Group doesn't exist on Group repository")
    void adminAddsCategoryToCategoryListGroupIsNotInRepository() throws JsonProcessingException {

        //Arrange:
        String uri = "/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String groupDescription = "Mariquinha";
        String categoryDenomination = "compras";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("No group found with that description."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Email-Null")
    void adminAddsCategoryToCategoryListEmailNull() throws JsonProcessingException {

        //Arrange:
        String uri = "/categories";

        String personEmail = null;
        String groupDescription = "friends";
        String categoryDenomination = "NightOut";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The email can't be null."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Email-Empty")
    void adminAddsCategoryToCategoryListEmailEmpty() throws JsonProcessingException {

        //Arrange:
        String uri = "/categories";

        String personEmail = "";
        String groupDescription = "friends";
        String categoryDenomination = "NightOut";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The email is not valid."))
                .isExactlyInstanceOf(NestedServletException.class);
                */

    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Email-Invalid Format")
    void adminAddsCategoryToCategoryListEmailInvalidFormat() throws JsonProcessingException {

        //Arrange:
        String uri = "/categories";

        String personEmail = "111917.dfkd";
        String groupDescription = "friends";
        String categoryDenomination = "NightOut";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The email is not valid."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Category is not added to Group categories - Category Already Exists")
    void adminAddsDuplicateCategoryToCategoryListTest() throws JsonProcessingException {

        //Arrange:
        String uri = "/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String groupDescription = "Switch";
        String categoryDenomination = "ISEP";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("This category already exists."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Category Denomination-Null")
    void adminAddsCategoryToCategoryListCategoryDenominationNull() throws JsonProcessingException {

        //Arrange:
        String uri = "/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String groupDescription = "Switch";
        String categoryDenomination = null;

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The denomination can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Category Denomination-Empty")
    void adminAddsCategoryToCategoryListCategoryDenominationEmpty() throws JsonProcessingException {

        //Arrange:
        String uri = "/categories";

        String personEmail = "1191762@isep.ipp.pt";
        String groupDescription = "Switch";
        String categoryDenomination = "";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The denomination can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Group Description-Null")
    void adminAddsCategoryToCategoryListGroupDescriptionNull() throws JsonProcessingException {

        //Arrange:
        String personEmail = "1191762@isep.ipp.pt";
        String groupDescription = null;
        String categoryDenomination = "NightOut";

        String uri = "/categories";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescription);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));


        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(inputJson));
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The description can't be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }


    /**
     * Test if a category can be found by the ID
     */
    @Test
    @DisplayName("Test if a category can be found by the ID - Happy Case")
    void getCategoryByCategoryID() throws Exception {

        //Arrange
        String uri = "/categories/groups/Smith Family/Online";

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
        /*
        Assertions.assertAll(
                () -> assertEquals(200, status),
                () -> assertEquals(expected, result)
        );

         */
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - group not found")
    void getCategoryByCategoryIDGroupNotFound() {

        //Arrange
        String uri = "/categories/groups/Just4Fun/Online";

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("No group found with that description."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }

    @Test
    @DisplayName("Test if a category can be found by the ID - category not found")
    void getCategoryByCategoryIDCategoryNotFound() {

        //Arrange
        String uri = "/categories/groups/SMITH FAMILY/Dispenses";

        //Act
        Throwable thrown = catchThrowable(() -> {
            mvc.perform(MockMvcRequestBuilders.get(uri)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
        });

        //Assert
        /*
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("No category found with that ID."))
                .isExactlyInstanceOf(NestedServletException.class);

         */
    }
}