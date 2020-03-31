package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;

public interface Repository {

    /**
     * Verifies if ID exists on the Repository
     *
     * @param id
     * @return
     */

    boolean isIDOnRepository (ID id);



}
