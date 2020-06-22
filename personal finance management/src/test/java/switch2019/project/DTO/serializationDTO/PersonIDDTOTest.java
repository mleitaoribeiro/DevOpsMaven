package switch2019.project.DTO.serializationDTO;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PersonIDDTOTest {

    @Test
    void getPersonID() {
        PersonIDDTO personIDDTO = new PersonIDDTO("email@email.com");

        assertEquals("email@email.com", personIDDTO.getPersonID());
    }

    @Test
    void testEquals() {
        PersonIDDTO personIDDTO = new PersonIDDTO("email@email.com");
        PersonIDDTO personIDDTOanother = new PersonIDDTO("email@email.com");
        PersonIDDTO personIDDTOfalse = new PersonIDDTO("otheremail@email.com");
        PersonID personID = new PersonID(new Email("email@mail.com"));

        assertEquals(personIDDTO, personIDDTO);
        assertEquals(personIDDTO, personIDDTOanother);
        assertNotEquals(personIDDTO, personIDDTOfalse);
        assertNotEquals(personIDDTO, personID);

    }

    @Test
    void testHashCode() {
        PersonIDDTO personIDDTO = new PersonIDDTO("email@email.com");
        PersonIDDTO personIDDTOanother = new PersonIDDTO("email@email.com");
        PersonIDDTO personIDDTOfalse = new PersonIDDTO("otheremail@email.com");

        assertEquals(personIDDTO.hashCode(), personIDDTOanother.hashCode());
        assertNotEquals(personIDDTO.hashCode(), personIDDTOfalse.hashCode());
    }
}