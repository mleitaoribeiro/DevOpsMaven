package switch2019.project.assemblers;

import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreateGroupAccountDTO;
import switch2019.project.domain.domainEntities.account.Account;


public class AccountDTOAssembler {


    private AccountDTOAssembler () {}

    /**
     * method for creating the DTO for an Account
     * param ownerID
     * param denomination
     * param description
     * @return AccountDTO
     */

    public static CreateGroupAccountDTO createGroupAccountDTOFromPrimitiveTrypes(String personEmail, String groupDescription,
                                                                                 String accountDenomination, String accountDescription) {
        return new CreateGroupAccountDTO(personEmail, groupDescription, accountDenomination, accountDescription);
    }

    public static AccountDTO createAccountDTOFromDomainObject(Account account) {
        return new AccountDTO(account.getOwnerID().toString(), account.denominationToString(), account.descriptionToString());
    }

}
