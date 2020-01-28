package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.Address;
import switch2019.project.model.Group;
import switch2019.project.model.GroupsList;
import switch2019.project.model.Person;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AdminCreatesGroupControllerTest {

    /**
     * As user , I Want to Creat a group and be admin (US002.1)
     */

    @Test
    @DisplayName("Test if Group was Created by the admin-Main Scenario")
    public void testIfGroupWasCreated() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        AdminCreatesGroupController controller = new AdminCreatesGroupController();
        Person person1 = new Person("John", LocalDate.of(2000, 12, 04), new Address("London"), new Address("Rua B", "Feira", "4520-233"));

        //Act
        boolean wasGroupCreated = controller.createGroup("familia", person1, groupsList);
        boolean ifIsAdmin = groupsList.checkIfAPersonIsAdminInAGivenGroup("familia", person1);


        //Assert
        assertTrue(wasGroupCreated && ifIsAdmin);

    }

    @Test
    @DisplayName("Test if Group is not created when its description is null-Excepction")
    public void testIfGroupWasNotCreated() {
        //Arrange
        AdminCreatesGroupController controller = new AdminCreatesGroupController();
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Francis", LocalDate.of(2001, 04, 12), new Address("Dublin"), new Address("Rua B", "Feira", "4520-233"));

        //Act
        try {
             controller.createGroup(null, person1, groupsList);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("There're no groups found with that description.", description.getMessage());
        }
    }


    @Test
    @DisplayName("Test if group was not created when it is already contained within a groupsList - Same name and same Members")
    public void testIfGroupWasAlreadyInList() {
        //Arrange
        AdminCreatesGroupController controller = new AdminCreatesGroupController();
        GroupsList groupsList = new GroupsList();
        Person person1 = new Person("Amy", LocalDate.of(1990, 12, 04), new Address("Boston"), new Address("Rua B", "Gaia", "4520-233"));
        controller.createGroup("Grupo de Teste",person1,groupsList);
        //Act

        boolean wasGroupCreated = controller.createGroup("Grupo de Teste", person1, groupsList);
        boolean ifIsAdmin = groupsList.checkIfAPersonIsAdminInAGivenGroup("Grupo de Teste", person1);
        //Assert
        assertFalse(wasGroupCreated && ifIsAdmin);
    }
}
