package switch2019.project.infrastructure.dataBaseRepositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.Assert;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CategoryDbRepositoryTest {

    @Autowired
    private CategoryDbRepository repository;

    private Denomination gym;
    private Denomination savings;
    private Category notPersistedCategory;
    private PersonID personID;


    @BeforeEach
    public void setup() {
        personID = new PersonID(new Email("mika@gmail.com"));
        notPersistedCategory = new Category(new Denomination("LOL"), new PersonID(new Email("lol@gmail.com")));
    }

    @Test
    @DisplayName("Get category in jpaRepository - true")
    void getByIDTrue() throws Exception {
        //Arrange
        Category category = repository.createCategory(new Denomination("HOME"), personID);

        //Act
        Category result = repository.getByID(category.getID());

        //Assert
        assertEquals(category, result);

    }

    @Test
    @DisplayName("Get category in jpaRepository - exception")
    void getByIDException() throws Exception {
        //Act
        Throwable thrown = catchThrowable(() -> {
            repository.getByID(notPersistedCategory.getID());
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No category found with that ID.");
    }


    @Test
    @DisplayName("Verify is Category Id is on jpaRepository - true")
    void isIDOnRepositoryTrue() throws Exception {
        //Arrange
        Category category = repository.createCategory(new Denomination("GARDEN"), personID);

        //Assert
        assertTrue(repository.isIDOnRepository(category.getID()));
    }

    @Test
    @DisplayName("Verify is Category Id is on jpaRepository - false")
    void isIDOnRepositoryFalse() throws Exception {
        assertFalse(repository.isIDOnRepository(notPersistedCategory.getID()));
    }

    @Test
    @DisplayName("Verify is Category is saved in jpaRepository - true")
    void createCategory() throws Exception {
        //Arrange
        Category category = new Category(new Denomination("ONLINE"), personID);
        Long expectedSize = repository.repositorySize() + 1;

        //Act
        Category result = repository.createCategory(new Denomination("ONLINE"), personID);

        //Assert
        Assertions.assertAll(
                () -> Assert.notNull(repository.getByID(result.getID()), "Category saved is found"),
                () -> assertEquals(expectedSize, repository.repositorySize()),
                () -> assertEquals(category, result)
        );

    }

    @Test
    @DisplayName("Verify is Category is saved in jpaRepository - exception")
    void createCategoryException() throws Exception {
        //Arrange
        repository.createCategory(new Denomination("ONLINE"), personID);

        //Act
        Throwable thrown = catchThrowable(() -> {
            repository.createCategory(new Denomination("ONLINE"), personID);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ResourceAlreadyExistsException.class)
                .hasMessage("This category already exists.");

    }

    @Test
    @DisplayName("Verify if multiple categories are added - true")
    void addMultipleCategories() throws Exception {
        //Arrange
        gym = new Denomination("GYM");
        savings = new Denomination("SAVINGS");
        Set<Denomination> setOfCategories = new HashSet<>(Arrays.asList(gym, savings));
        Long sizeBefore = repository.repositorySize();
        long expectedSize = sizeBefore + setOfCategories.size();

        //Act
        boolean result = repository.addMultipleCategories(setOfCategories, personID);

        //Assert
        Assertions.assertAll(
                () -> assertTrue(result),
                () -> assertEquals(expectedSize, repository.repositorySize())
        );
    }


    @Test
    @DisplayName("Return categories by Owner ID")
    void returnCategoriesByOwnerID() throws Exception {
        //Arrange
        Category category0 = repository.createCategory(new Denomination("SPORTS"), personID);
        Category category1 = repository.createCategory(new Denomination("TECH"), personID);
        Category category2 = repository.createCategory(new Denomination("CINEMA"), personID);

        Set<Category> expected = new HashSet<>(Arrays.asList(category0, category1, category2));

        //Act
        Set<Category> result = repository.returnCategoriesByOwnerID(personID);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Return categories by Owner ID - no categories")
    void returnCategoriesByOwnerIDENoCategoriesxcetpion() throws Exception {
        //Act
        Throwable thrown = catchThrowable(() -> {
            repository.returnCategoriesByOwnerID(personID);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(ArgumentNotFoundException.class)
                .hasMessage("No category found with that ID.");
    }

    @Test
    @DisplayName("Return categories by Owner ID - owner id null")
    void returnCategoriesByOwnerIDNullOwnerIDExcetpion() throws Exception {
        //Act
        Throwable thrown = catchThrowable(() -> {
            repository.returnCategoriesByOwnerID(null);
        });

        //Assert
        assertThat(thrown)
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Owner ID can't be null.");
    }
}