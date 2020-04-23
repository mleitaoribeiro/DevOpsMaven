package switch2019.project.DTO.ServiceDTO;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.shared.DateAndTime;
import switch2019.project.domain.domainEntities.shared.Description;
import switch2019.project.domain.domainEntities.shared.PersonID;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupCategoryDTOTest {

    @Test
    void testEquals() {
        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(
                "groupDesc", "person@mail.com", "nome");
        CreateGroupCategoryDTO createGroupCategoryDTO1 = new CreateGroupCategoryDTO(
                "groupDesc", "person@mail.com", "nome");
        DateAndTime dateAndTime = new DateAndTime(1996, 4, 3);

        assertEquals(createGroupCategoryDTO, createGroupCategoryDTO);
        assertNotEquals(createGroupCategoryDTO, dateAndTime);
        assertEquals(createGroupCategoryDTO.hashCode(), createGroupCategoryDTO1.hashCode());
    }

    @Test
    void testHashCode() {
    }
}