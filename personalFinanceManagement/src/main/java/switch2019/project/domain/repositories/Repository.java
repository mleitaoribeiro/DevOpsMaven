package switch2019.project.domain.repositories;

import switch2019.project.domain.domainEntities.frameworks.ID;

public interface Repository {

    Object getByID(ID id);

    boolean isIDOnRepository(ID id);

    long repositorySize();

}

