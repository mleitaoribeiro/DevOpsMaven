package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;

import java.util.Set;

public interface AccountRepository extends Repository {

    /**
     * Find an Account by it´s id
     *
     * @param accountID for accountId
     *
     */

    Account getByID (ID accountID);

    /**
     * Method to add one account to the repository with an Owner
     *
     * @param accountDenomination for accountDenomination
     * @param accountDescription for accountDescription
     * @param ownerID for ownerID
     * @return account
     */

    Account createAccount (Denomination accountDenomination, Description accountDescription, OwnerID ownerID);

    /**
     * Method to remove one account from the Repository
     *
     * @param accountToBeRemoved for accountToBeRemoved
     * @return true if account removed
     */

    boolean removeAccount (Account accountToBeRemoved);

    /**
     * Get list of Accounts By Owner ID
     *
     * @param ownerID for ownerID
     * @return list of accounts by ownerID
     */

    Set <Account> returnAccountsByOwnerID (OwnerID ownerID);


}
