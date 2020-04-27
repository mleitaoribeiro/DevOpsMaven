package switch2019.project.DTO.serviceDTO;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.shared.DateAndTime;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupCategoryDTOTest {

    @Test
    void testEquals() {
        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(
                "groupDesc", "person@mail.com", "nome");
        CreateGroupCategoryDTO createGroupCategoryDTO1 = new CreateGroupCategoryDTO(
                "groupDesc", "person@mail.com", "nome");
        CreateGroupCategoryDTO createGroupCategoryDTO2 = new CreateGroupCategoryDTO(
                "groupDescription", "person@mail.com", "nome");
        CreateGroupCategoryDTO createGroupCategoryDTO3 = new CreateGroupCategoryDTO(
                "groupDesc", "person1@mail.com", "nome");
        CreateGroupCategoryDTO createGroupCategoryDTO4 = new CreateGroupCategoryDTO(
                "groupDesc", "person@mail.com", "nomeX");


        DateAndTime dateAndTime = new DateAndTime(1996, 4, 3);

        assertEquals(createGroupCategoryDTO, createGroupCategoryDTO);
        assertNotEquals(createGroupCategoryDTO, dateAndTime);
        assertNotEquals(createGroupCategoryDTO, createGroupCategoryDTO3);
        assertNotEquals(createGroupCategoryDTO, createGroupCategoryDTO4);
        assertEquals(createGroupCategoryDTO.hashCode(), createGroupCategoryDTO1.hashCode());
        assertNotEquals(createGroupCategoryDTO.hashCode(), createGroupCategoryDTO2.hashCode());
    }


}