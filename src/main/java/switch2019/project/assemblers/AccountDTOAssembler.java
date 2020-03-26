package switch2019.project.assemblers;

import switch2019.project.DTO.AccountDTO;
import switch2019.project.DTO.CreateGroupAccountDTO;
import switch2019.project.DTO.CreatePersonAccountDTO;
import switch2019.project.domain.domainEntities.account.Account;


public class AccountDTOAssembler {


    private AccountDTOAssembler () {}

    /**
     * Method for creating the DTO for a Group Account
     *
     * param ownerID
     * param groupDescription
     * param accountDenomination
     * param accountDescription
     * @return CreateGroupAccountDTO
     */

    public static CreateGroupAccountDTO createGroupAccountDTOFromPrimitiveTypes(String personEmail, String groupDescription,
                                                                                 String accountDenomination, String accountDescription) {
        return new CreateGroupAccountDTO(personEmail, groupDescription, accountDenomination, accountDescription);
    }


    /**
     * Method for creating the DTO for a Person Account
     *
     * @param personEmail
     * @param accountDenomination
     * @param accountDescription
     * @return CreatePersonAccountDTO
     */
    public static CreatePersonAccountDTO createPersonAccountDTOFromPrimitiveTypes(String personEmail, String accountDenomination, String accountDescription) {
        return new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);
    }


    /**
     * Method for creating the DTO for a Account
     * @param account
     * @return
     */
    public static AccountDTO createAccountDTOFromDomainObject(Account account) {
        return new AccountDTO(account.getOwnerID().toString(), account.getID().getDenomination(), account.descriptionToString());
    }

}
