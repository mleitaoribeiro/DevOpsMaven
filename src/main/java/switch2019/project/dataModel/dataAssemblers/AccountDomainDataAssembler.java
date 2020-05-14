package switch2019.project.dataModel.dataAssemblers;

import switch2019.project.dataModel.entities.AccountJpa;
import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.shared.*;

import java.util.Currency;
import java.util.regex.Pattern;

//Assembler used to transform Accounts into accountsJpa and vice-versa:
public class AccountDomainDataAssembler {

    private AccountDomainDataAssembler() {}

    /**
     * Method that transforms an Account into AccountJpa
     *
     * @param account
     * @return
     */
    public static AccountJpa toData(Account account) {
        return new AccountJpa(
                account.getOwnerID().toString(),
                account.denominationToString(),
                account.descriptionToString(),
                account.getBalance().toString());
    }

    /**
     * Method that transforms an AccountJpa into an Account (Domain Object)
     *
     * @param accountJpa
     * @return
     */
    public static Account toDomain(AccountJpa accountJpa) {

        //Assembling OwnerID:
        String owner = accountJpa.getAccountIDJpa().getOwner();
        OwnerID ownerId;

        //Checking if owner is a Group or Person:
        if (isEmail(owner))
            ownerId = new PersonID(new Email(owner));
        else
            ownerId = new GroupID(new Description(owner));

        //Assembling Denomination:
        Denomination denomination = new Denomination(accountJpa.getAccountIDJpa().getDenomination());

        //Assembling Description:
        Description description = new Description(accountJpa.getDescription());

        //Assembling MonetaryValue: (A second constructor for the account was used here to replicate an account).
        double balance = Double.parseDouble(accountJpa.getAmount());

        if (balance != 0.0) {
            MonetaryValue monetaryValue = new MonetaryValue(balance, Currency.getInstance("EUR"));

            //Assembling Account with MonetaryValue:
            return new Account(denomination, description, ownerId, monetaryValue);
        }
        //Assembling Account without MonetaryValue:
        else return new Account(denomination, description, ownerId);
    }

    /**
     * Method used to check if an Account is owned by a Group or a Person
     *
     * @param string
     * @return
     */
    private static boolean isEmail(String string) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(string).matches();
    }
}
