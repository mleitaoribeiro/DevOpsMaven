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

    public static CreatePersonalTransactionDTO transformToCreatePersonalTransactionDTO(String personEmail, CreateTransactionInfoDTO infoDTO) {
        return new CreatePersonalTransactionDTO(personEmail, infoDTO.getAmount(), infoDTO.getCurrency(),
                infoDTO.getDescription(), infoDTO.getDate(), infoDTO.getCategory(),
                infoDTO.getAccountFrom(), infoDTO.getAccountTo(), infoDTO.getType());
    }

    public static CreateGroupTransactionDTO transformToCreateGroupTransactionDTO(String groupDescription,
                                                                                 CreateTransactionInfoDTO createTransactionInfoDTO) {

        return new CreateGroupTransactionDTO(groupDescription, createTransactionInfoDTO.getPersonEmail(),
                createTransactionInfoDTO.getAmount(), createTransactionInfoDTO.getCurrency(),
                createTransactionInfoDTO.getDate(), createTransactionInfoDTO.getDescription(),
                createTransactionInfoDTO.getCategory(), createTransactionInfoDTO.getAccountFrom(),
                createTransactionInfoDTO.getAccountTo(), createTransactionInfoDTO.getType());
    }

    public static TransactionDTO createTransactionDTOFromDomain(Transaction transaction) {
        return new TransactionDTO(transaction.getAmount(), transaction.getCurrency(), transaction.getDescription().toString(),
                transaction.dateToString(), transaction.getCategoryID().getDenominationString(), transaction.getAccountFrom().getDenominationToString(),
                transaction.getAccountTo().getDenominationToString(), transaction.typeToString());
    }

    public static TransactionShortDTO createTransactionShortDTOFromDomain(Transaction transaction) {

        return new TransactionShortDTO(transaction.getAmount(), transaction.getCurrency(),
                transaction.getAccountFrom().getDenominationToString(),
                transaction.getAccountTo().getDenominationToString(),
                transaction.typeToString(), transaction.getId());
    }

    public static GetPersonalTransactionInDateRangeDTO transformIntoGetPersonalTransactionInDateRangeDTO(String personEmail,
                                                                                                String initialDate, String finalDate) {
        return new GetPersonalTransactionInDateRangeDTO (personEmail, initialDate, finalDate);
    }

    public static GetGroupTransactionInDateRangeDTO transformIntoGetGroupTransactionInDateRangeDTO(String groupDescription,
                                                                   MemberInfoDTO memberInfoDTO, String initialDate, String finalDate) {
        return new GetGroupTransactionInDateRangeDTO (groupDescription, memberInfoDTO.getPersonEmail(), initialDate, finalDate);
    }

}
