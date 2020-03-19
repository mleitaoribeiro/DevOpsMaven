package switch2019.project.controllers;

import switch2019.project.services.US003AddMemberToGroupService;


public class US003AddMemberToGroupController {

    private US003AddMemberToGroupService service;

    public US003AddMemberToGroupController(US003AddMemberToGroupService service ){
        this.service=service;
    }

    /***
     * US003
     * Add member to Group
     *
     * @param emailperson
     * @param groupDescription
     * @return
     */
    public boolean addMemberToGroup( String emailperson, String groupDescription) {
        return service.addMemberToGroup(emailperson, groupDescription);
    }
}
