package switch2019.project.assemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.CategoryDTO;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CategoryDTOAssemblerTest {

    /**
     * Tests for the createCategoryDTO method:
     */
    @DisplayName("Test categoryDescription Getter")
    @Test
    void testIfStringsAreTheExpected() {
        //Arrange:

            //Arrange description:
        Denomination denomination = new Denomination("Movies");

            //Arrange categoryOwner:
        Person categoryOwner = new Person("Francisco", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

            //Arrange categoryID
        CategoryID categoryID = new CategoryID(denomination, new PersonID(new Email("Francisco@gmail.com")));

        //Act:
        CategoryDTO dto = CategoryDTOAssembler.createCategoryDTO(denomination.toString(),categoryID.toString());
        CategoryDTO toCompare = new CategoryDTO("MOVIES", "MOVIES, francisco@gmail.com");

        //Assert:
        assertEquals(dto,toCompare);
    }

    @DisplayName("Test categoryDescription Getter")
    @Test
    void testIfStringsAreTheNotTheExpected() {
        //Arrange:

        //Arrange description:
        Denomination denomination = new Denomination("Movies");

        //Arrange categoryOwner:
        Person categoryOwner = new Person("Francisco", new DateAndTime(1994, 04, 16), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("Francisco@gmail.com"));

        //Arrange categoryID
        CategoryID categoryID = new CategoryID(denomination, new PersonID(new Email("Francisco@gmail.com")));

        //Act:
        CategoryDTO dto = CategoryDTOAssembler.createCategoryDTO(denomination.toString(),categoryID.toString());
        CategoryDTO toCompare = new CategoryDTO("FILMS", "FILMS, francisco@gmail.com");

        //Assert:
        assertNotEquals(dto,toCompare);
    }

    /**
     * Tests for the createCategoryDTOFromCategory method.
     */
    @DisplayName("DTO has the expected information about the category")
    @Test
    void testCreateCategoryDTOFromCategoryHappyCase() {
        //Arrange:
        Category categoryToDto = new Category(new Denomination("VIDEOGAMES"),new PersonID(new Email("Francisca@hotmail.com")));
        CategoryDTO expectedDto = new CategoryDTO("VIDEOGAMES", "francisca@hotmail.com");

        //Act:
        CategoryDTO actualDto = CategoryDTOAssembler.createCategoryDTOFromCategory(categoryToDto);

        //Assert:
        assertEquals(expectedDto, actualDto);
    }

    @DisplayName("DTO has the expected information about the category")
    @Test
    void testCreateCategoryDTOFromCategoryFail() {
        //Arrange:
        Category categoryToDto = new Category(new Denomination("VIDEOGAMES"),new PersonID(new Email("Francisca@hotmail.com")));
        CategoryDTO expectedDto = new CategoryDTO("GAMES", "francisca@hotmail.com");

        //Act:
        CategoryDTO actualDto = CategoryDTOAssembler.createCategoryDTOFromCategory(categoryToDto);

        //Assert:
        assertNotEquals(expectedDto, actualDto);
    }
}
