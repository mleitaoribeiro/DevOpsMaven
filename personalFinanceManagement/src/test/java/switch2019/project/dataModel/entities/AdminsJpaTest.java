package switch2019.project.dataModel.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AdminsJpaTest {

    @Test
    void test() {
        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa adminsJpa = new AdminsJpa();

        assertNotEquals(adminsJpa, groupJpa);

    }

    @Test
    void test1() {
        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa adminsJpanull = null;

        AdminsJpa adminsJpa1 = new AdminsJpa(groupJpa, "Person");

        assertNotEquals(adminsJpa1, adminsJpanull);

    }

    @Test
    void test2() {
        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa adminsJpa = new AdminsJpa();

        AdminsJpa adminsJpa1 = new AdminsJpa(groupJpa, "Person");

        assertNotEquals(adminsJpa, adminsJpa1);

    }

    @Test
    void test3() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        GroupJpa groupJpa1 = new GroupJpa(" ","s", "s");

        AdminsJpa adminsJpa1 = new AdminsJpa(groupJpa, "Person");

        AdminsJpa adminsJpa3 = new AdminsJpa(groupJpa1, "Person");

        assertNotEquals(adminsJpa1, adminsJpa3);

    }

    @Test
    void test4() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa adminsJpa1 = new AdminsJpa(groupJpa, "Person");

        AdminsJpa adminsJpa2 = new AdminsJpa(groupJpa, "Person");

        assertEquals(adminsJpa1, adminsJpa2);

    }

    @Test
    void test5() {

        AdminsJpa adminsJpa = new AdminsJpa();

        assertEquals(adminsJpa, adminsJpa);

    }

    @Test
    void test6() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa.AdminsIdJpa adminsIdJpaX = new AdminsJpa.AdminsIdJpa(groupJpa, "Person");

        AdminsJpa adminsJpa1 = new AdminsJpa(groupJpa, "Person");

        assertEquals(adminsJpa1.getId(), adminsIdJpaX);

    }


    @Test
    void test7() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa.AdminsIdJpa adminsIdJpa = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");

        AdminsJpa.AdminsIdJpa adminsIdJpa2 = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");

        assertEquals(adminsIdJpa, adminsIdJpa2);
    }

    @Test
    void test8() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa.AdminsIdJpa adminsIdJpa = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");

        AdminsJpa.AdminsIdJpa adminsIdJpa1 = new AdminsJpa.AdminsIdJpa(groupJpa, "Other person");

        assertNotEquals(adminsIdJpa, adminsIdJpa1);

    }

    @Test
    void test9() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa.AdminsIdJpa adminsIdJpa = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");

        assertEquals(adminsIdJpa, adminsIdJpa);

    }

    @Test
    void test10() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa.AdminsIdJpa adminsIdJpa = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");

        assertNotEquals(adminsIdJpa, groupJpa);
    }

    @Test
    void test11() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa adminsJpa1 = new AdminsJpa(groupJpa, "Person");

        AdminsJpa adminsJpa2 = new AdminsJpa(groupJpa, adminsJpa1.getPersonID());

        assertEquals(adminsJpa1.hashCode(), adminsJpa2.hashCode());
    }


    @Test
    void test12() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        GroupJpa groupJpa1 = new GroupJpa(" ","s", "s");

        AdminsJpa adminsJpa1 = new AdminsJpa(groupJpa, "Person");

        AdminsJpa adminsJpa3 = new AdminsJpa(groupJpa1, "Person");

        assertNotEquals(adminsJpa1.hashCode(), adminsJpa3.hashCode());
    }

    @Test
    void test13() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa adminsJpa1 = new AdminsJpa(groupJpa, "Person");

        AdminsJpa adminsJpa2 = new AdminsJpa(groupJpa, adminsJpa1.getPersonID());

        String a = adminsJpa1.toString();

        String b = adminsJpa2.toString();

        assertEquals(a, b);
    }

    @Test
    void test135() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa adminsJpa1 = new AdminsJpa(groupJpa, "Person");

        AdminsJpa adminsJpa2 = new AdminsJpa(groupJpa, "Other");

        String a = adminsJpa1.toString();

        String b = adminsJpa2.toString();

        assertNotEquals(a, b);
    }

    @Test
    void test14() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa.AdminsIdJpa adminsIdJpa = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");

        AdminsJpa.AdminsIdJpa adminsIdJpa2 = new AdminsJpa.AdminsIdJpa(adminsIdJpa.getGroupID(), adminsIdJpa.getPersonID());

        assertEquals(adminsIdJpa.hashCode(), adminsIdJpa2.hashCode());
    }

    @Test
    void test15() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa.AdminsIdJpa adminsIdJpa = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");

        AdminsJpa.AdminsIdJpa adminsIdJpa1 = new AdminsJpa.AdminsIdJpa(groupJpa, "Other person");

        AdminsJpa.AdminsIdJpa adminsIdJpa2 = new AdminsJpa.AdminsIdJpa(adminsIdJpa.getGroupID(), adminsIdJpa.getPersonID());

        assertNotEquals(adminsIdJpa1.hashCode(), adminsIdJpa2.hashCode());

    }

    @Test
    void test16() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa.AdminsIdJpa adminsIdJpa = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");

        AdminsJpa.AdminsIdJpa adminsIdJpa2 = new AdminsJpa.AdminsIdJpa(adminsIdJpa.getGroupID(), adminsIdJpa.getPersonID());

        String a = adminsIdJpa.toString();

        String b = adminsIdJpa2.toString();

        assertEquals(a, b);
    }

    @Test
    void test165() {

        GroupJpa groupJpa = new GroupJpa("","", "");

        AdminsJpa.AdminsIdJpa adminsIdJpa = new AdminsJpa.AdminsIdJpa(groupJpa, "Someone");

        AdminsJpa.AdminsIdJpa adminsIdJpa2 = new AdminsJpa.AdminsIdJpa(adminsIdJpa.getGroupID(), "Other");

        String a = adminsIdJpa.toString();

        String b = adminsIdJpa2.toString();

        assertNotEquals(a, b);
    }



}