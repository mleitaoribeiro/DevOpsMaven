package switch2019.project.controllers;

import switch2019.project.model.shared.PersonID;
import switch2019.project.model.shared.Description;
import switch2019.project.services.US002_1createGroupAndBecomeAdminService;

public class US002_1CreateGroupAndBecomeAdminController{

    public boolean createGroupAndBecomeAdmin(US002_1createGroupAndBecomeAdminService service,
                                             Description groupDescription, PersonID personID) {
        return service.createGroupAndBecomeAdmin(groupDescription, personID);
    }


}

