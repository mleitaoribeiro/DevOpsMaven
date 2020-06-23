package switch2019.project.assemblers;

import switch2019.project.DTO.serializationDTO.PersonIDDTO;
import switch2019.project.DTO.serializationDTO.SiblingsDTO;
import switch2019.project.DTO.serviceDTO.AreSiblingsDTO;
import switch2019.project.domain.domainEntities.shared.PersonID;

public class PersonDTOAssembler {

    private PersonDTOAssembler () {}

    public static AreSiblingsDTO createAreSiblingsDTO(String emailPersonOne, String emailPersonTwo) {
        return new AreSiblingsDTO(emailPersonOne, emailPersonTwo);
    }

    public static SiblingsDTO createSiblingsDTO(boolean result) {
        return new SiblingsDTO(result);
    }

    public static PersonIDDTO createPersonIDDTO(PersonID personID) {
        return new PersonIDDTO(personID.getEmail());
    }

}
