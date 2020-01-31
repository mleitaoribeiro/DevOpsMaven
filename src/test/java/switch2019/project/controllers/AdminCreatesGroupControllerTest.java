package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.Address;
import switch2019.project.model.GroupsList;
import switch2019.project.model.Person;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AdminCreatesGroupControllerTest {

    /**
     * US002.1 - As a user I want to create a group becoming a group administrator.
     */

    @Test
    @DisplayName("Test if Group was Created by the admin-Main Scenario")
    public void testIfGroupWasCreated() {
        //Arrange
        GroupsList groupsList = new GroupsList();
        AdminCreatesGroupController controller = new AdminCreatesGroupController();
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("John", LocalDate.of(2000, 12, 04), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"),mom,dad);

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
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("Francis", LocalDate.of(2001, 04, 12), new Address("Dublin"),
                new Address("Rua B", "Feira", "4520-233"),mom,dad);

        //Act

             boolean ifWasCreated= controller.createGroup(null, person1, groupsList);

        //Assert

            assertFalse(ifWasCreated);
        }



    @Test
    @DisplayName("Test if group was not created when it is already contained within a groupsList - Same name and same Members")
    public void testIfGroupWasAlreadyInList() {
        //Arrange
        AdminCreatesGroupController controller = new AdminCreatesGroupController();
        GroupsList groupsList = new GroupsList();
        Person dad = new Person("António", LocalDate.of(1970, 12, 23), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person mom = new Person("Margarida", LocalDate.of(1975, 10, 10), new Address("Porto"),
                new Address("Avenida António Domingues Guimarães", "Porto", "4520-266"));
        Person person1 = new Person("Amy", LocalDate.of(1990, 12, 04), new Address("Boston"),
                new Address("Rua B", "Gaia", "4520-233"),mom,dad);
        controller.createGroup("Grupo de Teste",person1,groupsList);
        //Act

        boolean wasGroupCreated = controller.createGroup("Grupo de Teste", person1, groupsList);
        boolean ifIsAdmin = groupsList.checkIfAPersonIsAdminInAGivenGroup("Grupo de Teste", person1);

        //Assert
        assertFalse(wasGroupCreated && ifIsAdmin);
    }
}
