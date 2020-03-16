package switch2019.project.controllers;

import org.junit.jupiter.api.Test;
import switch2019.project.model.shared.Description;
import switch2019.project.model.shared.PersonID;
import switch2019.project.services.US002_1createGroupAndBecomeAdminService;

import static org.junit.jupiter.api.Assertions.*;

class US002_1CreateGroupAndBecomeAdminControllerTest {

    @Test
    void createGroupAndBecomeAdmin() {

        //Arrange
        US002_1CreateGroupAndBecomeAdminController us002_1C = new US002_1CreateGroupAndBecomeAdminController();
        US002_1createGroupAndBecomeAdminService us002_1S = new US002_1createGroupAndBecomeAdminService();
        Description groupDescription = new Description("Bashtards");
        PersonID personID = new PersonID("Nome");

        //Act
        boolean result = us002_1C.createGroupAndBecomeAdmin(us002_1S, groupDescription, personID);

        //Assert
        assertTrue(result);
    }
}