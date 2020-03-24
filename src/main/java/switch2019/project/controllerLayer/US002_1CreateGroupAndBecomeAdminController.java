package switch2019.project.controllerLayer;

import switch2019.project.applicationLayer.US002_1CreateGroupAndBecomeAdminService;

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
     * @param personEmail
     * @return true the group was created and person is now Admin
     */

    public boolean createGroupAndBecomeAdmin(String groupDescription, String personEmail) {
        return service.createGroupAndBecomeAdmin(groupDescription, personEmail);
    }


}

