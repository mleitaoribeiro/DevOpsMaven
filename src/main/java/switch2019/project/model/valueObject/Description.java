package switch2019.project.model.valueObject;

public class Description {

    private String description;

    /**
     * Constructor
     * @param description
     */


    public Description(String description){
        setDescription(description);
    }
    /**
     * setter Description
     *
     * @param description
     */
    private void setDescription(String description) {
        if (description != null || description.length()>0){
            this.description = description.toUpperCase();
        }
        else{
            throw new IllegalArgumentException("The description can't be null or empty. ");
        }
    }


}