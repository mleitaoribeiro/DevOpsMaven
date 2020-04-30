package switch2019.project.DTO.serializationDTO;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(categoryDenominationDTO,categoryDenominationDTO2);
        assertNotEquals(categoryDenominationDTO,categoryDenominationDTO3);
        assertNotEquals(categoryDenominationDTO,personID);
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