package switch2019.project.assemblers;

import switch2019.project.DTO.SiblingsDTO;

public class PersonDTOAssembler {

    private PersonDTOAssembler () {}

    /**
     * Method to create a siblingDTO from two Strings of Person Email
     * @param emailPersonOne
     * @param emailPersonTwo
     * @return SiblingsDTO
     */
    public static SiblingsDTO creatSiblingsDTOSiblingsDTO(String emailPersonOne, String emailPersonTwo) {
        return new SiblingsDTO(emailPersonOne, emailPersonTwo);
    }
}
