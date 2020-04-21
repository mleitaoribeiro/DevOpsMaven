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
     * @param id
     */

    Account getByID (ID id);


    /**
     * Method to add one account to the repository with an Owner
     *
     * @param accountDenomination
     * @param accountDescription
     * @param ownerID
     */

    Account createAccount (Denomination accountDenomination, Description accountDescription, OwnerID ownerID);

    /**
     * Get list of Accounts By Owner ID
     *
     * @param ownerID
     */

    Set<Account> returnAccountsByOwnerID (OwnerID ownerID);

    /**
     * method to remove one account from the Repository
     *
     * @param accountToBeRemoved
     */

    boolean removeOneAccountFromRepository (Account accountToBeRemoved);

}
