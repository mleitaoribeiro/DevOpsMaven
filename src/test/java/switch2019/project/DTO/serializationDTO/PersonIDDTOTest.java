package switch2019.project.DTO.SerializationDTO;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.*;

class PersonIDDTOTest {

    @Test
    void getPersonID() {
        PersonIDDTO personIDDTO = new PersonIDDTO(new PersonID(new Email("email@email.com")));

        assertEquals("email@email.com", personIDDTO.getPersonID());
    }

    @Test
    void testEquals() {
        PersonIDDTO personIDDTO = new PersonIDDTO(new PersonID(new Email("email@email.com")));
        PersonIDDTO personIDDTOanother = new PersonIDDTO(new PersonID(new Email("email@email.com")));
        PersonIDDTO personIDDTOfalse = new PersonIDDTO(new PersonID(new Email("otheremail@email.com")));
        PersonID personID = new PersonID(new Email("email@mail.com"));

        assertEquals(personIDDTO, personIDDTO);
        assertEquals(personIDDTO, personIDDTOanother);
        assertNotEquals(personIDDTO, personIDDTOfalse);
        assertNotEquals(personIDDTO, personID);

    }

    @Test
    void testHashCode() {
        PersonIDDTO personIDDTO = new PersonIDDTO(new PersonID(new Email("email@email.com")));
        PersonIDDTO personIDDTOanother = new PersonIDDTO(new PersonID(new Email("email@email.com")));
        PersonIDDTO personIDDTOfalse = new PersonIDDTO(new PersonID(new Email("otheremail@email.com")));

        assertEquals(personIDDTO.hashCode(), personIDDTOanother.hashCode());
        assertNotEquals(personIDDTO.hashCode(), personIDDTOfalse.hashCode());
    }
}