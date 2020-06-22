package switch2019.project.assemblers;

import switch2019.project.DTO.serializationDTO.PersonIDDTO;
import switch2019.project.DTO.serializationDTO.SiblingsDTO;
import switch2019.project.DTO.serviceDTO.AreSiblingsDTO;
import switch2019.project.domain.domainEntities.shared.PersonID;

public class PersonDTOAssembler {

    private PersonDTOAssembler () {}

    /**
     * Method to create a siblingDTO from two Strings of Person Email
     * @param emailPersonOne
     * @param emailPersonTwo
     * @return AreSiblingsDTO
     */
    public static AreSiblingsDTO createAreSiblingsDTO(String emailPersonOne, String emailPersonTwo) {
        return new AreSiblingsDTO(emailPersonOne, emailPersonTwo);
    }

    /**
     * Method to create a siblingDTO from boolean from Service
     * @param result
     * @return SiblingsDTO
     */
    public static SiblingsDTO createSiblingsDTO(boolean result) {
        return new SiblingsDTO(result);
    }

    /**
     * Method to create a PersonIDDTO from domain
     * @param personID
     * @return PersonIDDTO
     */

    public static PersonIDDTO createPersonIDDTO(PersonID personID) {
        return new PersonIDDTO(personID.getEmail());
    }

}
