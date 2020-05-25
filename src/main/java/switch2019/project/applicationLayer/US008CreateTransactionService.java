package switch2019.project.applicationLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import switch2019.project.DTO.serializationDTO.TransactionShortDTO;
import switch2019.project.DTO.serviceDTO.CreateGroupTransactionDTO;
import switch2019.project.DTO.serviceDTO.CreatePersonalTransactionDTO;
import switch2019.project.assemblers.LedgerDTOAssembler;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.ledger.Ledger;
import switch2019.project.domain.domainEntities.ledger.Transaction;
import switch2019.project.domain.domainEntities.ledger.Type;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.*;
import switch2019.project.utils.StringUtils;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.NoPermissionException;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;


@Service
public class US008CreateTransactionService {

    @Autowired
    @Qualifier("LedgerDbRepository")
    private LedgerRepository ledgerRepository;

    @Autowired
    @Qualifier("PersonDbRepository")
    private PersonRepository personRepository;

    @Autowired
    @Qualifier("GroupDbRepository")
    private GroupRepository groupsRepository;

    @Autowired
    @Qualifier("CategoryDbRepository")
    private CategoryRepository categoryRepository;

    @Autowired
    @Qualifier("AccountDbRepository")
    private AccountRepository accountRepository;

    /**
     * US008.1 - As a group member, I want to create a group transaction.
     *
     * @param createGroupTransactionDTO
     * @return transactionShortDTO
     */

    public TransactionShortDTO addGroupTransaction(CreateGroupTransactionDTO createGroupTransactionDTO) {

        PersonID personID = personRepository.findPersonByEmail(new Email(createGroupTransactionDTO.getPersonEmail())).getID();

        Group group = groupsRepository.findGroupByDescription(new Description(createGroupTransactionDTO.getGroupDescription()));
        GroupID groupID = group.getID();

        MonetaryValue amount = new MonetaryValue(createGroupTransactionDTO.getAmount(),
                Currency.getInstance(createGroupTransactionDTO.getCurrency()));
        Description description = new Description(createGroupTransactionDTO.getDescription());
        DateAndTime date = StringUtils.toDateHourMinute(createGroupTransactionDTO.getDate());
        CategoryID categoryID = new CategoryID(new Denomination(createGroupTransactionDTO.getCategory()), groupID);
        AccountID accountFrom = new AccountID(new Denomination(createGroupTransactionDTO.getAccountFrom()), groupID);
        AccountID accountTo = new AccountID(new Denomination(createGroupTransactionDTO.getAccountTo()), groupID);
        Type type = new Type(createGroupTransactionDTO.getType());

        if (!group.isGroupMember(personID)) {
            throw new NoPermissionException("This person is not member of this group.");
        } else if (!categoryRepository.isIDOnRepository(categoryID))  {
            throw new ArgumentNotFoundException("No category found with that ID.");
        } else if (!accountRepository.isIDOnRepository(accountFrom) || !accountRepository.isIDOnRepository(accountTo)) {
            throw new ArgumentNotFoundException("No account found with that ID.");
        }
            Ledger ledger = ledgerRepository.getByID(groupID);

            Transaction transaction = ledgerRepository.addTransactionToLedger(ledger.getID(), amount,
                    description, date, categoryID, accountFrom, accountTo, type);

            return LedgerDTOAssembler.createTransactionShortDTOFromDomain(transaction);
     }

    /**
     * US008 - I want to create a personal transaction.
     *
     * @param createPersonalTransactionDTO
     * @return TransactionShortDTO
     */
    public TransactionShortDTO addPersonalTransaction(CreatePersonalTransactionDTO createPersonalTransactionDTO) {

        PersonID personID = personRepository.findPersonByEmail(new Email(createPersonalTransactionDTO.getPersonEmail())).getID();
        Ledger ledger = ledgerRepository.getByID(personID);

        MonetaryValue amount = new MonetaryValue(createPersonalTransactionDTO.getAmount(),
                Currency.getInstance(createPersonalTransactionDTO.getCurrency()));
        Description description = new Description(createPersonalTransactionDTO.getDescription());
        DateAndTime date = StringUtils.toDateHourMinute(createPersonalTransactionDTO.getDate());
        CategoryID category = new CategoryID(new Denomination(createPersonalTransactionDTO.getCategory()), personID);
        AccountID accountFrom = new AccountID(new Denomination(createPersonalTransactionDTO.getAccountFrom()), personID);
        AccountID accountTo = new AccountID(new Denomination(createPersonalTransactionDTO.getAccountTo()), personID);
        Type type = new Type(createPersonalTransactionDTO.getType());

        // TODO change return variable
        ledgerRepository.addTransactionToLedger(ledger.getID(), amount, description, date, category, accountFrom, accountTo, type);
        // temporary
        Transaction transaction = new Transaction(amount, description, date, category, accountFrom, accountTo, type);

        return LedgerDTOAssembler.createTransactionShortDTOFromDomain(transaction);
    }

    /**
     * Method that finds a transaction by it's ID
     *
     * @return TransactionShortDTO
     */

    public TransactionShortDTO getTransactionByID(String ownerID, Long id) {

        String finalOwnerID;

        if (StringUtils.isEmail(ownerID)) {
            finalOwnerID = personRepository.findPersonByEmail(new Email(ownerID)).getID().toString();
        } else
            finalOwnerID = groupsRepository.findGroupByDescription(new Description(ownerID)).getID().toString();

        Transaction transaction = ledgerRepository.getByTransactionID(finalOwnerID, id);

        return LedgerDTOAssembler.createTransactionShortDTOFromDomain(transaction);
    }


    /**
     * Method that finds a transaction by it's Legder ID
     *
     * @return TransactionDTO
     */

    public List<TransactionShortDTO> getTransactionsByLedgerId(String ledgerID) {

        List<Transaction> list = ledgerRepository.findAllTransactionsByLedgerID(ledgerID);

        List<TransactionShortDTO> output = new ArrayList<>();

        for (Transaction transaction : list) {
            output.add(LedgerDTOAssembler.createTransactionShortDTOFromDomain(transaction));
        }

        return output;
    }
}

