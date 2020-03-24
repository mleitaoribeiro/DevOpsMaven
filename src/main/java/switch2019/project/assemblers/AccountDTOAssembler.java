package switch2019.project.assemblers;

import switch2019.project.DTO.AccountDTO;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;

public class AccountDTOAssembler {

    /**
     * method for creating the DTO for an Account
     * param ownerID
     * param denomination
     * param description
     * @return AccountDTO
     */
    public static AccountDTO createAccountDTO(OwnerID ownerID, Denomination denomination, Description description){
        return new AccountDTO(ownerID.toString(), denomination.toString(), description.toString());
    }
}
