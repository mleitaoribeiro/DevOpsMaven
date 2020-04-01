package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;

public interface Repository {


    /**
     * find an object by it´s id
     *
     * @param id
     * @return
     */

    Object getByID (ID id);


    /**
     * Verifies if ID exists on the Repository
     *
     * @param id
     * @return
     */

    boolean isIDOnRepository (ID id);


    /**
     * Method to get the numbers of Objects in the Repository
     *
     * @return
     */

    int repositorySize();



}
