package switch2019.project.assemblers;

import switch2019.project.DTO.deserializationDTO.CreateTransactionInfoDTO;
import switch2019.project.DTO.deserializationDTO.MemberInfoDTO;
import switch2019.project.DTO.serializationDTO.TransactionDTO;
import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupTransactionDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonalTransactionDTO;
import switch2019.project.DTO.serviceDTO.GetGroupTransactionInDateRangeDTO;
import switch2019.project.DTO.serviceDTO.GetPersonalTransactionInDateRangeDTO;
import switch2019.project.domain.domainEntities.ledger.Transaction;

public class LedgerDTOAssembler {

    public LedgerDTOAssembler() {
    }

    /**
     * This method transforms a CreatePersonalTransactionInfoDTO into a CreatePersonalTransactionDTO
     *
     * @param personEmail
     * @param infoDTO
     * @return CreatePersonalTransactionDTO
     */
    public static CreatePersonalTransactionDTO transformToCreatePersonalTransactionDTO(String personEmail, CreateTransactionInfoDTO infoDTO) {
        return new CreatePersonalTransactionDTO(personEmail, infoDTO.getAmount(), infoDTO.getCurrency(),
                infoDTO.getDescription(), infoDTO.getDate(), infoDTO.getCategory(),
                infoDTO.getAccountFrom(), infoDTO.getAccountTo(), infoDTO.getType());
    }

    /**
     * This method transforms a CreateGroupTransactionInfoDTO into a CreateGroupTransactionDTO
     */

    public static CreateGroupTransactionDTO transformToCreateGroupTransactionDTO(String groupDescription,
                                                                                 CreateTransactionInfoDTO createTransactionInfoDTO) {

        return new CreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO.getPersonEmail(),
                createTransactionInfoDTO.getAmount(), createTransactionInfoDTO.getCurrency(),
                createTransactionInfoDTO.getDate(), createTransactionInfoDTO.getDescription(),
                createTransactionInfoDTO.getCategory(), createTransactionInfoDTO.getAccountFrom(),
                createTransactionInfoDTO.getAccountTo(), createTransactionInfoDTO.getType());
    }

    /**
     * Method to create the TransactionDTO from Domain Object
     *
     * @param transaction
     * @return TransactionDTO
     */
    public static TransactionDTO createTransactionDTOFromDomain(Transaction transaction) {
        return new TransactionDTO(transaction.getAmount(), transaction.getCurrency(), transaction.getDescription().toString(),
                transaction.dateToString(), transaction.getCategoryID().getDenominationString(), transaction.getAccountFrom().getDenominationToString(),
                transaction.getAccountTo().getDenominationToString(), transaction.typeToString());
    }

    /**
     * Method that creates the TransactionShortDTO from the Domain Object (Transaction)
     *
     * @param transaction
     * @return TransactionShortDTO
     */
    public static TransactionShortDTO createTransactionShortDTOFromDomain(Transaction transaction) {

        return new TransactionShortDTO(transaction.getAmount(), transaction.getCurrency(),
                transaction.getAccountFrom().getDenominationToString(),
                transaction.getAccountTo().getDenominationToString(),
                transaction.typeToString(), transaction.getId());
    }


    /**
     * This method transforms a MemberInfoDTO into a GetPersonalTransactionInDateRangeDTO
     *
     * @param personEmail
     * @param initialDate
     * @param finalDate
     * @return GetPersonalTransactionInDateRangeDTO
     */
    public static GetPersonalTransactionInDateRangeDTO transformIntoGetPersonalTransactionInDateRangeDTO(String personEmail,
                                                                                                String initialDate, String finalDate) {
        return new GetPersonalTransactionInDateRangeDTO (personEmail, initialDate, finalDate);
    }


    /**
     * This method transforms a MemberInfoDTO into a GetGroupTransactionInDateRangeDTO
     *
     * @param memberInfoDTO
     * @param groupDescription
     * @param initialDate
     * @param finalDate
     * @return GetPersonalTransactionInDateRangeDTO
     */
    public static GetGroupTransactionInDateRangeDTO transformIntoGetGroupTransactionInDateRangeDTO(String groupDescription,
                                                                   MemberInfoDTO memberInfoDTO, String initialDate, String finalDate) {
        return new GetGroupTransactionInDateRangeDTO (groupDescription, memberInfoDTO.getPersonEmail(), initialDate, finalDate);
    }

}
