package switch2019.project.dataModel.dataAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.dataModel.entities.GroupJpa;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GroupDomainDataAssemblerTest {
    /**
     * Test if the toData method can transform an object Group to a GroupJpa
     */
/*    @Test
    @DisplayName("Test if a Group is transform in a GroupJpa - true")
    public void toData() {
        //Arrange
        Group group = new Group(new Description("SWITCH"), new PersonID(new Email("1191762@isep.ipp.pt")), new DateAndTime(2002, 9, 10));

        GroupJpa grouJpaExpected = new GroupJpa("SWITCH", "1191762@isep.ipp.pt", "2002-09-10");

        //Act
        GroupJpa groupJpaResult = GroupDomainDataAssembler.toData(group);

        //Assert
        assertEquals(grouJpaExpected, groupJpaResult);
    }

    @Test
    @DisplayName("Test if a Group is transform in a GroupJpa - false")
    public void toDataFalse() {
        //Arrange
        Description description = new Description("Switch");
        Group group = new Group(description, new PersonID(new Email("1191762@isep.ipp.pt")), new DateAndTime(2002, 9, 10));

        GroupJpa grouJpaExpected = new GroupJpa("Switch", "11917@isep.ipp.pt", "2020-9-10");

        //Act
        GroupJpa groupJpaResult = GroupDomainDataAssembler.toData(group);

        //Assert
        assertNotEquals(grouJpaExpected, groupJpaResult);
    }

    *//**
     * Test if the toDomain method can transform an object GroupJpa to a Group
     *//*
    @Test
    @DisplayName("Test if a GroupJpa is transform in a Group - true")
    public void toDomain() {

        //Arrange
        Group groupExpected = new Group(new Description("Switch"), new PersonID(new Email("1191762@isep.ipp.pt")), new DateAndTime(2002, 9, 10));

        GroupJpa grouJpa = new GroupJpa("Switch", "1191762@isep.ipp.pt", "2002-9-10");

        //Act
        Group groupResult = GroupDomainDataAssembler.toDomain(grouJpa);

        //Assert
        assertEquals(groupExpected, groupResult);
    }
    @Test
    @DisplayName("Test if a GroupJpa is transform in a Group - false")
    public void toDomainFalse() {

        //Arrange
        Group groupExpected = new Group(new Description("Switch"), new PersonID(new Email("1191762@isep.ipp.pt")), new DateAndTime(2002, 9, 10));

        GroupJpa grouJpa = new GroupJpa("Smith", "1191762@isep.ipp.pt", "2002-9-10");

        //Act
        Group groupResult = GroupDomainDataAssembler.toDomain(grouJpa);

        //Assert
        assertNotEquals(groupExpected, groupResult);
    }*/
}

