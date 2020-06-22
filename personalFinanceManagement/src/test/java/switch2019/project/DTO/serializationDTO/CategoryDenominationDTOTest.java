package switch2019.project.DTO.serializationDTO;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CategoryDenominationDTOTest {

    @Test
    void getGroupDescription() {
        CategoryDenominationDTO categoryDenominationDTO =new CategoryDenominationDTO("compras");
        assertEquals("compras",categoryDenominationDTO.getCategoryDenomination());
    }

    @Test
    void testEquals() {
        CategoryDenominationDTO categoryDenominationDTO =new CategoryDenominationDTO("compras");
        CategoryDenominationDTO categoryDenominationDTO2 =new CategoryDenominationDTO("compras");
        CategoryDenominationDTO categoryDenominationDTO3 =new CategoryDenominationDTO("shop");
        PersonID personID = new PersonID(new Email("email@mail.com"));
        PersonIDDTO personIDDTO = new PersonIDDTO("email@email.com");

        assertEquals(categoryDenominationDTO,categoryDenominationDTO2);
        assertEquals(categoryDenominationDTO,categoryDenominationDTO);
        assertNotEquals(categoryDenominationDTO,categoryDenominationDTO3);
        assertNotEquals(categoryDenominationDTO,personID);
        assertNotEquals(categoryDenominationDTO,personIDDTO);
    }

    @Test
    void testHashCode() {
        CategoryDenominationDTO categoryDenominationDTO =new CategoryDenominationDTO("compras");
        CategoryDenominationDTO categoryDenominationDTO2 =new CategoryDenominationDTO("compras");
        CategoryDenominationDTO categoryDenominationDTO3 =new CategoryDenominationDTO("shop");

        assertEquals(categoryDenominationDTO.hashCode(),categoryDenominationDTO2.hashCode());
        assertNotEquals(categoryDenominationDTO.hashCode(),categoryDenominationDTO3.hashCode());
    }
}