package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupJpaTest {

    @Test
    void testEquals() {
        //Arrange
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        GroupJpa groupJpa2 = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        //Act
        boolean result = groupJpa.equals(groupJpa2);
        boolean result1 = groupJpa.equals(groupJpa);

        //Assert
        assertTrue(result && result1);
    }

    @Test
    void testEqualsNull() {
        //Arrange
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        GroupJpa groupJpa2 = null;

        //Act
        boolean result = groupJpa.equals(groupJpa2);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsDifferentObjects() {
        //Arrange
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        Account oneAccount = new Account(new Denomination("xpto"), new Description("xpto account"), new PersonID(new Email("marco@gmail.com")));

        //Act
        boolean result = groupJpa.equals(oneAccount);

        //Assert
        assertFalse(result);
    }

    @Test
    void testHashCode() {
        //Arrange
        GroupJpa groupJpa = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        GroupJpa groupJpa1 = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        GroupJpa groupJpa2 = new GroupJpa("!Switch", "mail@msil.com", "2002-09-10");
        //Assert
        assertEquals(groupJpa.hashCode(), groupJpa1.hashCode());
        assertNotEquals(groupJpa.hashCode(), groupJpa2.hashCode());
    }

    @Test
    @DisplayName("Add member to memberList- main scenario")
    void addMemberTrue() {
        //Arrange
        GroupJpa groupJpa = new GroupJpa("COOL KIDS", "marta@isep.ipp.pt", "2002-09-10");
        String memberId = "marta@gmail.com";
        MembersJpa memberJpa = new MembersJpa(groupJpa, memberId);
        MembersJpa adminAndMemberJpa = new MembersJpa(groupJpa, groupJpa.getGroupCreator());

        List<MembersJpa> expectedList = Arrays.asList(adminAndMemberJpa, memberJpa);

        //Act
        boolean result = groupJpa.addMember(memberId);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedList, groupJpa.getMembers()),
                () -> assertTrue(result)
        );
    }

    @Test
    @DisplayName("Add member to memberList - in case it already exists")
    void addMemberFalse() {
        //Arrange
        GroupJpa groupJpa = new GroupJpa("COOL KIDS", "marta@isep.ipp.pt", "2002-09-10");
        String memberId = "marta@gmail.com";
        groupJpa.addMember(memberId);

        MembersJpa memberJpa = new MembersJpa(groupJpa, memberId);
        MembersJpa adminAndMemberJpa = new MembersJpa(groupJpa, groupJpa.getGroupCreator());

        List<MembersJpa> expectedList = Arrays.asList(adminAndMemberJpa, memberJpa);

        //Act
        boolean result = groupJpa.addMember(memberId);


        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedList, groupJpa.getMembers()),
                () -> assertFalse(result)
        );
    }

    @Test
    @DisplayName("Add member to adminList- main scenario")
    void addAdminTrue() {

        //Arrange
        GroupJpa groupJpa = new GroupJpa("Elefantes Azuis", "1191743@isep.ipp.pt", "2002-09-10");
        String adminId = "morty@gmail.com";
        AdminsJpa adminJpa = new AdminsJpa(groupJpa, adminId);
        AdminsJpa adminAndMemberJpa = new AdminsJpa(groupJpa, groupJpa.getGroupCreator());

        List<AdminsJpa> expectedList = Arrays.asList(adminAndMemberJpa, adminJpa);

        //Act
        groupJpa.addMember(adminId);
        boolean result = groupJpa.addAdmin(adminId);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedList, groupJpa.getAdministrators()),
                () -> assertTrue(result)
        );
    }

    @Test
    @DisplayName("Add member to adminList- person not member")
    void addAdminFalseNotMember() {

        //Arrange
        GroupJpa groupJpa = new GroupJpa("Elefantes Azuis", "1191743@isep.ipp.pt", "2002-09-10");
        String adminId = "morty@gmail.com";
        AdminsJpa adminAndMemberJpa = new AdminsJpa(groupJpa, groupJpa.getGroupCreator());

        List<AdminsJpa> expectedList = Collections.singletonList(adminAndMemberJpa);

        //Act
        boolean result = groupJpa.addAdmin(adminId);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedList, groupJpa.getAdministrators()),
                () -> assertFalse(result)
        );
    }

    @Test
    @DisplayName("Add member to adminList- already Admin")
    void addAdminAlreadyAdmin() {

        //Arrange
        GroupJpa groupJpa = new GroupJpa("Elefantes Azuis", "1191743@isep.ipp.pt", "2002-09-10");
        String adminId = "morty@gmail.com";
        AdminsJpa adminJpa = new AdminsJpa(groupJpa, adminId);
        AdminsJpa adminAndMemberJpa = new AdminsJpa(groupJpa, groupJpa.getGroupCreator());

        List<AdminsJpa> expectedList = Arrays.asList(adminAndMemberJpa, adminJpa);

        //Act
        groupJpa.addMember(adminId);
        groupJpa.addAdmin(adminId);
        boolean result = groupJpa.addAdmin(adminId);

        //Assert
        Assertions.assertAll(
                () -> assertEquals(expectedList, groupJpa.getAdministrators()),
                () -> assertFalse(result)
        );
    }
}
