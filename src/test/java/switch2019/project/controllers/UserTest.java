package switch2019.project.controllers;

import org.junit.jupiter.api.Test;
import switch2019.project.model.Address;
import switch2019.project.model.Category;
import switch2019.project.model.CategoryList;
import switch2019.project.model.Person;


import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void addCategoryToList() {
        //Arrange
        Person person1 = new Person("Alexandre", 4, 3, 1996, new Address("Porto"));
        User user1 = new User(person1);
        Category category1 = new Category("School expenses");

        //Act
        user1.addCategoryToList(category1);

        //Assert

    }

    @Test
    void removeCategoryFromList() {
        //Arrange
        Person person1 = new Person("Alexandre", 4, 3, 1996, new Address("Porto"));
        User user1 = new User(person1);
        Category category1 = new Category("School expenses");
        Category category2 = new Category("Health expenses");

        //Act
        user1.addCategoryToList(category1);
        user1.addCategoryToList(category2);
        user1.removeCategoryFromList(category1);

        //Assert
    }


}