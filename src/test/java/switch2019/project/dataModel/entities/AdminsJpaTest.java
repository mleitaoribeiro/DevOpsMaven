package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminsJpaTest {

    @Test
    void testEquals() {
        GroupJpa groupJpa = new GroupJpa("","", "");
        GroupJpa groupJpa1 = new GroupJpa(" ","s", "s");
        AdminsJpa.AdminsIdJpa adminsIdJpaX = new AdminsJpa.AdminsIdJpa(groupJpa, "Person");
        AdminsJpa adminsJpa = new AdminsJpa();
        AdminsJpa adminsJpanull = null;
        AdminsJpa adminsJpa1 = new AdminsJpa(groupJpa, "Person");
        AdminsJpa adminsJpa2 = new AdminsJpa(groupJpa, "Person");
        AdminsJpa adminsJpa3 = new AdminsJpa(groupJpa1, "Person");

        assertNotEquals(adminsJpa, groupJpa);
        assertNotEquals(adminsJpa1, adminsJpanull);
        assertNotEquals(adminsJpa, adminsJpa1);
        assertNotEquals(adminsJpa1, adminsJpa3);
        assertEquals(adminsJpa1, adminsJpa2);
        assertEquals(adminsJpa, adminsJpa);
        assertEquals(adminsJpa1.getId(), adminsIdJpaX);

    }

    @Test
    void testEqualsAdminsIdJpa() {
        GroupJpa groupJpa = new GroupJpa("","", "");
        AdminsJpa.AdminsIdJpa adminsIdJpa = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");

        AdminsJpa.AdminsIdJpa adminsIdJpa1 = new AdminsJpa.AdminsIdJpa(groupJpa, "Other person");
        AdminsJpa.AdminsIdJpa adminsIdJpa2 = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");

        assertEquals(adminsIdJpa, adminsIdJpa2);
        assertNotEquals(adminsIdJpa, adminsIdJpa1);
        assertEquals(adminsIdJpa, adminsIdJpa);
        assertNotEquals(adminsIdJpa, groupJpa);
    }

    @Test
    void testHashCode() {
        GroupJpa groupJpa = new GroupJpa("","", "");
        GroupJpa groupJpa1 = new GroupJpa(" ","s", "s");
        AdminsJpa adminsJpa1 = new AdminsJpa(groupJpa, "Person");
        AdminsJpa adminsJpa2 = new AdminsJpa(groupJpa, adminsJpa1.getPersonID());
        AdminsJpa adminsJpa3 = new AdminsJpa(groupJpa1, "Person");

        assertEquals(adminsJpa1.hashCode(), adminsJpa2.hashCode());
        assertNotEquals(adminsJpa1.hashCode(), adminsJpa3.hashCode());
        assertEquals(adminsJpa1.toString(), adminsJpa2.toString());
    }

    @Test
    void testHashCodeAdminsIdJpa() {
        GroupJpa groupJpa = new GroupJpa("","", "");
        AdminsJpa.AdminsIdJpa adminsIdJpa = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");
        AdminsJpa.AdminsIdJpa adminsIdJpa1 = new AdminsJpa.AdminsIdJpa(groupJpa, "Other person");
        AdminsJpa.AdminsIdJpa adminsIdJpa2 = new AdminsJpa.AdminsIdJpa(adminsIdJpa.getGroupID(), adminsIdJpa.getPersonID());

        assertEquals(adminsIdJpa.hashCode(), adminsIdJpa2.hashCode());
        assertNotEquals(adminsIdJpa1.hashCode(), adminsIdJpa2.hashCode());
        assertEquals(adminsIdJpa.toString(), adminsIdJpa2.toString());
    }



}