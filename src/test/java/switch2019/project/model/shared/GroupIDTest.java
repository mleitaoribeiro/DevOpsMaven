package switch2019.project.model.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.group.Group;

import static org.junit.jupiter.api.Assertions.*;

class GroupIDTest {

    @Test
    @DisplayName("Test if two groupID are the same - true case")
    void testEqualGroupIDTrueCase() {
        //Arrange:
        Description groupID1description = new Description("Running Team");
        GroupID groupID1 = new GroupID(groupID1description);

        Description groupID2description = new Description("Running Team");
        GroupID groupID2 = new GroupID(groupID2description);

        //Act:
        boolean result = groupID1.equals(groupID2);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if two groupID are the same - false case")
    void testEqualsGroupIDFalseCase() {
        //Arrange:
        Description groupID1description = new Description("Gym Buddies");
        GroupID groupID1 = new GroupID(groupID1description);

        Description groupID2description = new Description("Running Team");
        GroupID groupID2 = new GroupID(groupID2description);

        //Act:
        boolean result = groupID1.equals(groupID2);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test if two groupID are the same - one groupID is null")
    void testEqualsGroupIDNullCase() {
        //Arrange & Act
        try {
            Description groupID1description = new Description(null);
            GroupID groupID1 = new GroupID(groupID1description);
        }

        //Assert
        catch (IllegalArgumentException nullOrEmptyGroupID) {
            assertEquals("The description can't be null or empty.", nullOrEmptyGroupID.getMessage());
        }
    }


    @Test
    @DisplayName("Test if two groupID are the same - different type of objects")
    void testEqualsGroupIDDifferentType() {
        //Arrange:
        Description groupID1description = new Description("Linkin Park Fans");
        GroupID groupID1 = new GroupID(groupID1description);

        Group group1 = new Group("Linkin Park Fans");

        //Act:
        boolean result = groupID1.equals(group1);

        //Assert:
        assertFalse(result);
    }


}