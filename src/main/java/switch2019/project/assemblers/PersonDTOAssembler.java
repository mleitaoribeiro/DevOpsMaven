package switch2019.project.assemblers;

import switch2019.project.DTO.SiblingsDTO;

public class PersonDTOAssembler {

    public static SiblingsDTO creatSiblingsDTOSiblingsDTO(String emailPersonOne, String emailPersonTwo) {
        return new SiblingsDTO(emailPersonOne, emailPersonTwo);
    }
}
