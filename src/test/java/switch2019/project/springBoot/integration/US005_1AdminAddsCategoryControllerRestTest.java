package switch2019.project.springBoot.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import switch2019.project.AbstractTest;
import switch2019.project.DTO.DeserializationDTO.CreateGroupCategoryInfoDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class US005_1AdminAddsCategoryControllerRestTest extends AbstractTest {

    @Override
    @BeforeEach
    public void setUP() {
        super.setUP();
    }

    @Test
    @DisplayName("Test if the outputDTO is the expected")
    void addCategoryToGroupServiceTestEqual() throws Exception {

        String uri = "/addCategoryToGroup";

        final String groupDescriptionStr = "Smith Family";
        final String personEmail = "rick@gmail.com";
        final String categoryDenomination = "shopping";

        CreateGroupCategoryInfoDTO createGroupCategoryInfoDTO = new CreateGroupCategoryInfoDTO();
        createGroupCategoryInfoDTO.setGroupDescription(groupDescriptionStr);
        createGroupCategoryInfoDTO.setPersonEmail(personEmail);
        createGroupCategoryInfoDTO.setCategoryDenomination(categoryDenomination);

        String inputJson = super.mapToJson((createGroupCategoryInfoDTO));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);

        String result = mvcResult.getResponse().getContentAsString();
        String expected = "{\"denomination\":\"" + categoryDenomination.toUpperCase() + "\"" + "," + "\"ownerID\":\"" + groupDescriptionStr.toUpperCase() + "\"}";
        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Category is not added to Group categories - Person does't exists on the repository")
    void adminAddsCategoryToCategoryListNotExistsOnRepository() throws JsonProcessingException {
        //Arrange:
        String uri = "/addCategoryToGroup";

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
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("No person found with that email."))
                .isExactlyInstanceOf(NestedServletException.class);
    }


    @Test
    @DisplayName("Category is not added to Group categories - Person does't is member of the group")
    void adminAddsCategoryToCategoryListNotAMember() throws JsonProcessingException {
        //Arrange:

        String uri = "/addCategoryToGroup";

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
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("This person is not a group admin or member and could not add the category."))
                .isExactlyInstanceOf(NestedServletException.class);
    }

    @Test
    @DisplayName("Category is not added to Group categories - Group is not on the repository")
    void adminAddsCategoryToCategoryListGroupIsNotInRepository() throws JsonProcessingException {
        //Arrange:

        String uri = "/addCategoryToGroup";

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
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("There're no groups found with that description."))
                .isExactlyInstanceOf(NestedServletException.class);
    }

    @Test
    @DisplayName("Category is not added to Group categories - Category Already Exists")
    void adminAddsDuplicateCategoryToCategoryListTest() throws JsonProcessingException {
        //Arrange:

        String uri = "/addCategoryToGroup";

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
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("This category already exists and it could not be created."))
                .isExactlyInstanceOf(NestedServletException.class);
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Category Denomination-Null")
    void adminAddsCategoryToCategoryListCategoryDenominationNull() throws JsonProcessingException {
        //Arrange:

        String uri = "/addCategoryToGroup";

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
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The denomination can´t be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);
    }

    @Test
    @DisplayName("Category is not added to Group categories - invalid Category Denomination-Empty")
    void adminAddsCategoryToCategoryListCategoryDenominationEmpty() throws JsonProcessingException {
        //Arrange:

        String uri = "/addCategoryToGroup";

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
        assertThat(thrown)
                .hasCause(new IllegalArgumentException("The denomination can´t be null or empty."))
                .isExactlyInstanceOf(NestedServletException.class);
    }
}