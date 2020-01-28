package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.Address;
import switch2019.project.model.GroupsList;
import switch2019.project.model.Person;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminCreatesGroupControllerTest {
    /**
     * As user , I Want to Creat a group and be admin (US002.1)
     */

    @Test
    @DisplayName("Test if Group was Created")
    public void testIfGroupWasCreated() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        AdminCreatesGroupController controller= new AdminCreatesGroupController();
        Person person1 = new Person("John", LocalDate.of(2000, 12,04), new Address("London"),new Address("Rua B","Feira","4520-233"));

        //Act
        boolean wasGroupCreated = controller.createGroup("Test Person", person1,groupsList);

        //Assert
        assertTrue(wasGroupCreated);

    }

    @Test
    @DisplayName("Test if Group is not created when its description is null")
    public void testIfGroupWasNotCreated() {
        //Arrange
        AdminCreatesGroupController controller= new AdminCreatesGroupController();
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Francis",  LocalDate.of(2001, 04,12), new Address("Dublin"),new Address("Rua B","Feira","4520-233"));

        //Act
        boolean wasGroupCreated = controller.createGroup(null, person1,groupsList);

        //Assert
        assertFalse(wasGroupCreated);
    }


    @Test
    @DisplayName("Test if group was not created when it is already contained within a groupsList - Same name and same Members")
    public void testIfGroupWasAlreadyInList() {
        //Arrange
        AdminCreatesGroupController controller= new AdminCreatesGroupController();
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Amy", LocalDate.of(1990, 12,04), new Address("Boston"),new Address("Rua B","Gaia","4520-233"));

        //Act
        groupsList.createGroup("Grupo de Teste", person1);
        boolean wasGroupCreated = controller.createGroup("Grupo de Teste", person1,groupsList);

        //Assert
        assertFalse(wasGroupCreated);
    }


    @Test
    @DisplayName("Test if group is created even it has the same name but different members")
    public void createGroupWithSameDescriptionAndDifferentMembers() {
        //Arrange
        AdminCreatesGroupController controller= new AdminCreatesGroupController();
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Amy", LocalDate.of(1990, 12,04), new Address("Boston"),new Address("Rua B","Gaia","4520-233"));
        Person person2 = new Person("Marshall",LocalDate.of(1990, 12,04), new Address("Boston"),new Address("Rua B","Gaia","4520-233"));

        //Act
        groupsList.createGroup("Grupo de Teste", person1);

        boolean wasGroupCreated = controller.createGroup("Grupo de Teste", person2,groupsList);

        //Assert
        assertTrue(wasGroupCreated);
    }


    @Test
    @DisplayName("Test if group is created with different description but same person")
    public void createGroupWithDifferentDescriptionAndDifferentMembers() {
        //Arrange
        AdminCreatesGroupController controller= new AdminCreatesGroupController();
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Amy",  LocalDate.of(1999, 5,13), new Address("Boston"),new Address("Rua B","Gaia","4520-233"));

        //Act
        groupsList.createGroup("Grupo de Teste", person1);

        boolean wasGroupCreated = controller.createGroup("Grupo Diferente", person1,groupsList);

        //Assert
        assertTrue(wasGroupCreated);
    }

}
