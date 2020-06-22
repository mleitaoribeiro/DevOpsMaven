package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;

public interface Repository {


    /**
     * find an object by it´s id
     *
     * @param id for id
     * @return object with that id
     */

    Object getByID(ID id);


    /**
     * Verifies if ID exists on the Repository
     *
     * @param id for id
     * @return true if it is on repository
     */

    boolean isIDOnRepository(ID id);


    /**
     * Method to get the number of Objects in the Repository
     *
     * @return number of objects
     */

    long repositorySize();

}

