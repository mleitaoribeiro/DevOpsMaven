package switch2019.project.assemblers;

import switch2019.project.DTO.deserializationDTO.CreateGroupAccountInfoDTO;
import switch2019.project.DTO.deserializationDTO.CreatePersonAccountInfoDTO;
import switch2019.project.DTO.serializationDTO.AccountDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupAccountDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonAccountDTO;
import switch2019.project.domain.domainEntities.account.Account;


public class AccountDTOAssembler {


    private AccountDTOAssembler() {
    }

    public static CreateGroupAccountDTO createGroupAccountDTOFromPrimitiveTypes(String personEmail, String groupDescription,
                                                                                String accountDenomination, String accountDescription) {
        return new CreateGroupAccountDTO(personEmail, groupDescription, accountDenomination, accountDescription);
    }

    public static CreatePersonAccountDTO createPersonAccountDTOFromPrimitiveTypes(String personEmail, String accountDenomination, String accountDescription) {
        return new CreatePersonAccountDTO(personEmail, accountDenomination, accountDescription);
    }

    public static CreatePersonAccountDTO transformIntoCreatePersonAccountDTO(String personEmail, CreatePersonAccountInfoDTO createPersonAccountInfoDTO) {
        return new CreatePersonAccountDTO(personEmail, createPersonAccountInfoDTO.getAccountDenomination(), createPersonAccountInfoDTO.getAccountDescription());
    }


    public static AccountDTO createAccountDTOFromDomainObject(Account account) {
        return new AccountDTO(account.getOwnerID().toString(), account.getID().getDenominationToString(), account.descriptionToString());
    }

    public static CreateGroupAccountDTO transformToCreateGroupAccountDTO(String groupDescription, CreateGroupAccountInfoDTO dto) {
        return new CreateGroupAccountDTO(dto.getPersonEmail(), groupDescription, dto.getAccountDenomination(), dto.getAccountDescription());
    }

}
