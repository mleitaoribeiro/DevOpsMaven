package switch2019.project.dataModel.dataAssemblers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.dataModel.entities.CategoryJpa;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.GroupID;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoryDomainDataAssemblerTest {

    /**
     * Test if the toData method can transform an object Category to a CategoryJPA
     */

    @Test
    @DisplayName("Test if a Category is transform in a CategoryJPA | OwnerID = PersonID | True")
    public void toDataWithPersonID() {

        //Arrange

        String categoryDenomination = "SPORTS";
        String email = "1110120@isep.ipp.pt";

        Category someCategory = new Category(new Denomination(categoryDenomination), new PersonID(new Email(email)));

        CategoryJpa expectedCategoryJPA = new CategoryJpa(email, categoryDenomination);

        //Act
        CategoryJpa realCategoryJPA = CategoryDomainDataAssembler.toData(someCategory);

        //Assert
        assertEquals(expectedCategoryJPA, realCategoryJPA);
    }

    @Test
    @DisplayName("Test if a Category is transform in a CategoryJPA | OwnerID = GroupID | True")
    public void toDataWithGroupID() {

        //Arrange

        String categoryDenomination = "SPORTS";
        String groupDescription = "SWITCH";

        Category someCategory = new Category(new Denomination(categoryDenomination), new GroupID(new Description(groupDescription)));

        CategoryJpa expectedCategoryJPA = new CategoryJpa(groupDescription, categoryDenomination);

        //Act
        CategoryJpa realCategoryJPA = CategoryDomainDataAssembler.toData(someCategory);

        //Assert
        assertEquals(expectedCategoryJPA, realCategoryJPA);
    }

    /**
     * Test if the toDomain method can transform an object CategoryJPA to a Category
     */

    @Test
    @DisplayName("Test if a CategoryJPA is transform in a Category | OwnerID = PersonID | True")
    public void toDomainWithPersonID() {

        //Arrange

        String categoryDenomination = "Sports";
        String email = "1110120@isep.ipp.pt";

        CategoryJpa categoryJPA = new CategoryJpa(email, categoryDenomination);

        Category expectedCategory = new Category(new Denomination(categoryDenomination), new PersonID(new Email(email)));

        //Act
        Category realCategory = CategoryDomainDataAssembler.toDomain(categoryJPA);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedCategory, realCategory),
                () -> assertTrue(realCategory.getOwnerID() instanceof PersonID)
        );
    }

    @Test
    @DisplayName("Test if a CategoryJPA is transform in a Category | OwnerID = GroupID | True")
    public void toDomainWithGroupID() {

        //Arrange

        String categoryDenomination = "Sports";
        String groupDescription = "SWitCH";

        CategoryJpa categoryJPA = new CategoryJpa(groupDescription, categoryDenomination);

        Category expectedCategory = new Category(new Denomination(categoryDenomination), new GroupID(new Description(groupDescription)));

        //Act
        Category realCategory = CategoryDomainDataAssembler.toDomain(categoryJPA);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedCategory, realCategory),
                () -> assertTrue(realCategory.getOwnerID() instanceof GroupID)
        );
    }



}