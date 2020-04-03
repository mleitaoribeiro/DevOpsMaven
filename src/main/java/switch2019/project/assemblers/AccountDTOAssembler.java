package switch2019.project.assemblers;

import switch2019.project.DTO.DeserializationDTO.CreateGroupAccountInfoDTO;
import switch2019.project.DTO.DeserializationDTO.CreatePersonAccountInfoDTO;
import switch2019.project.DTO.SerializationDTO.AccountDTO;
import switch2019.project.DTO.ServiceDTO.CreatePersonAccountDTO;
import switch2019.project.DTO.ServiceDTO.CreateGroupAccountDTO;
import switch2019.project.domain.domainEntities.account.Account;


public class AccountDTOAssembler {


    private AccountDTOAssembler() {
    }

    /**
     * Method for creating the DTO for a Group Account
     * <p>
     * param ownerID
     * param groupDescription
     * param accountDenomination
     * param accountDescription
     *
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
     * This method transformes a CreatePersonAccountnfoDTO into a CreatePersonAccountDTO
     *
     * @param createPersonAccountInfoDTO
     * @return
     */
    public static CreatePersonAccountDTO transformIntoCreatePersonAccountDTO(CreatePersonAccountInfoDTO createPersonAccountInfoDTO) {
        return new CreatePersonAccountDTO(createPersonAccountInfoDTO.getPersonEmail(), createPersonAccountInfoDTO.getAccountDenomination(), createPersonAccountInfoDTO.getAccountDescription());
    }

    /**
     * Method for creating the DTO from Domain Object
     *
     * @param account
     * @return
     */
    public static AccountDTO createAccountDTOFromDomainObject(Account account) {
        return new AccountDTO(account.getOwnerID().toString(), account.getID().getDenomination(), account.descriptionToString());
    }

    /**
     * This method transformes a input DTO to the CreateAccountInGroupDTO.
     *
     * @param dto
     * @return CreateAccountInGroupDTO
     */
    public static CreateGroupAccountDTO transformToCreateGroupCategoryDTO(CreateGroupAccountInfoDTO dto) {
        return new CreateGroupAccountDTO(dto.getPersonEmail(), dto.getGroupDescription(), dto.getAccountDenomination(), dto.getAccountDescription());
    }

}
