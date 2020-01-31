package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.Address;
import switch2019.project.model.Category;
import switch2019.project.model.Group;
import switch2019.project.model.Person;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminCreatesCategoryControllerTest {
    /**
     * US005.1 - As a Group Administrator, I want to create a category and add it to a group.
     *
     * Check if a Admin createad and added Category to the groups Category list
     */

    @Test
    @DisplayName("Check if a category was added to Category List by admin- Main Scenario")
    void addCategoryToListMainScenario() {
        //Arrange
        Group group1 = new Group("Grupo dos amigos");
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"),mom,dad);
        AdminCreatesCategoryController controller = new AdminCreatesCategoryController();

        group1.addMember(person1);


        //Act
        boolean realResult = controller.createCategory("School expenses", group1, person1);

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Check if Admin created and added category  - False Scenario")
    void addCategoryToListFalseScenario() {
        //Arrange

        Group group1 = new Group("Grupo dos amigos");
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"),mom,dad);
        Person person2 = new Person("Filipa", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"),mom,dad);
        AdminCreatesCategoryController controller = new AdminCreatesCategoryController();

        group1.addMember(person1);

        //Act
        boolean realResult = controller.createCategory("School expenses", group1, person2);

        //Assert
        assertFalse(realResult);
    }

    @Test
    @DisplayName("Check if null category is not added by the admin")
    void addCategoryToListWithANullCase() {
        //Arrange
        //Initialize Group
        Group group1 = new Group("Grupo dos amigos");
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"),mom,dad);
        AdminCreatesCategoryController controller = new AdminCreatesCategoryController();
        group1.addMember(person1);

        //Act
        boolean realResult = controller.createCategory(null, group1, person1);

        //Assert
        assertFalse(realResult);

    }

    @Test
    @DisplayName("Check if the same Category is not added simultaneously by the admin")
    void addTwoCategoriesToListWithTwoCategoriesThatAreTheSame() {
        //Arrange
        Group group1 = new Group("Groupo dos amigos");
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"),mom,dad);
        group1.addMember(person1);
        AdminCreatesCategoryController controller = new AdminCreatesCategoryController();

        //Act
        boolean categoryAdded = controller.createCategory("Cinema", group1, person1);
        boolean sameCategoryAdded = controller.createCategory("Cinema", group1, person1);
        boolean isSameCategoryAdded = categoryAdded && sameCategoryAdded;

        //Assert
        assertFalse(isSameCategoryAdded);

    }

    @Test
    @DisplayName("Check if the same Category is not added simultaneously - Ignore letter capitalization and special characters ")
    void addTwoCategoriesToListWithTwoCategoriesCaseInsensitive() {
        //Arrange
        Group group1 = new Group("Groupo dos amigos");
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("João", LocalDate.of(2000, 12, 12), new Address("Porto"),
                new Address("Rua dos Flores", "Porto", "4450-852"),mom,dad);
        AdminCreatesCategoryController controller = new AdminCreatesCategoryController();
        group1.addMember(person1);
        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("SCHOóL expenses");

        //Act
        boolean realResult = controller.createCategory("School expenses", group1, person1) && !group1.createAndAddCategoryToCategoryList("SCHOóL expenses", person1);

        //Assert
        assertTrue(realResult);
    }
}