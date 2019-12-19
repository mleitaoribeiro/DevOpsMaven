package switch2019.project.controllers;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.Group;
import switch2019.project.model.Person;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MembersControllerTest {

    /**
     * User Story - Test if a member was added to a group
     * Setup for testing
     */
    @BeforeClass
    public void setUp () {
        // Members to be included in the groups
        Person p1 = new Person ("Marie", 2000, 10, 3);
        Person p2 = new Person("John",1996,12,9);
        Person p3 = new Person("Anna",1993,2,23);
        Person p4 = new Person("Susan",1993,3,9);
        Person p5 = new Person("Frank",1996,12,5);
        Person p6 = new Person("Jessica",2002,12,3);
        Person p7 = new Person("Jack", 1990,1,3);

        // Groups created by the system manager
        Group g1 = new Group("Just4Fun", 2019, 11, 10);
        Group g2 = new Group("SWitCH", 2019, 10, 7);
        Group g3 = new Group("Movies", 2015, 5, 18);

        // Setting of the groups

        //Group g1 - add just one member to the group
        g1.addMember(p7);

        //Group g1 - add several members to the group
        HashSet<Person> finalGroup1= new HashSet<>(Arrays.asList(p2,p4,p5));
        g1.addMultipleMembers(finalGroup1);

        //Group g2 - add just one member to the group
        g2.addMember(p1);

        //Group g2 - add several members to the group
        HashSet<Person> finalGroup2= new HashSet<>(Arrays.asList(p3,p6));
        g2.addMultipleMembers(finalGroup2);

        //Group g3 - add just one member to the group
        g3.addMember(p4);

        //Group g3 - add several members to the group
        HashSet<Person> finalGroup3= new HashSet<>(Arrays.asList(p7,p3,p5));
        g2.addMultipleMembers(finalGroup3);
    }

    /**
     * Test if a member was added to a group
     */




}