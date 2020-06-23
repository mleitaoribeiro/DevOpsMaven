package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.account.Account;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.Denomination;
import switch2019.project.domain.domainEntities.shared.Description;

import java.util.Set;

public interface AccountRepository extends Repository {

    Account getByID (ID accountID);

    Account createAccount (Denomination accountDenomination, Description accountDescription, OwnerID ownerID);

    boolean removeAccount (Account accountToBeRemoved);

    Set <Account> returnAccountsByOwnerID (OwnerID ownerID);


}
