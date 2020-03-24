package switch2019.project.controllerLayer;

import switch2019.project.applicationLayer.US003AddMemberToGroupService;


public class US003AddMemberToGroupController {

    private US003AddMemberToGroupService service;

    public US003AddMemberToGroupController(US003AddMemberToGroupService service ){
        this.service=service;
    }

    /***
     * US003
     * Add member to Group
     *
     * @param personEmail
     * @param groupDescription
     * @return
     */
    public boolean addMemberToGroup( String personEmail, String groupDescription) {
        return service.addMemberToGroup(personEmail, groupDescription);
    }
}
