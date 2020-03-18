package switch2019.project.controllers;

import switch2019.project.model.shared.PersonID;
import switch2019.project.model.shared.Description;
import switch2019.project.services.US002_1CreateGroupAndBecomeAdminService;

public class US002_1CreateGroupAndBecomeAdminController {

    private US002_1CreateGroupAndBecomeAdminService service;

    public US002_1CreateGroupAndBecomeAdminController(US002_1CreateGroupAndBecomeAdminService service) {
        this.service = service;
    }

    /**
     * US002.1
     * I want to create a group and become an Admin
     *
     * @param groupDescription
     * @param personID
     * @return true the group was created and person is now Admin
     */

    public boolean createGroupAndBecomeAdmin(Description groupDescription, PersonID personID) {
        return service.createGroupAndBecomeAdmin(groupDescription, personID);
    }


}

