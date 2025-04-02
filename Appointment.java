/**
 * Class to model entity Appointments
 * extends Event
 * @author Nhu Ha
 * @version 3.0
 */
public class Appointment extends Event{
    private String contact;

    /**
     * Default constrcutor
     */
    public Appointment(){
        super();
        contact = "";
    }

    /**
     * 5-parameter constructor
     * @param description value of the description
     * @param location value of the location
     * @param date value of the date
     * @param time value of the time
     * @param contact value of the contact
     * @throws FormatMismatchException exception being thrown when the format of date or time is invalid
     */
    public Appointment(String description, String location, String date, String time, String contact) throws FormatMismatchException{
        super(description, location, date, time);
        this.contact = contact;
    }

    /**
     * Getter for the contact
     * @return value of the contact
     */
    public String getContact(){return contact;}
    
    /**
     * Setter for the contact
     * @param contact value of the contact
     */
    public void setContact(String contact){this.contact = contact;}

    /**
     * Accessor to the Appointment attributes
     * @return formatted String of the Appointment attributes
     */
    @Override
    public String toString(){
        return String.format("%-10s\t%s\t%-10s", "Appointment", super.toString(), contact);
    }

    /**
     * Accessor to the Appointment attributes
     * @return formatted String of the Appointment attributes separated by ","
     */
    public String fileString(){
        return "Appointment," + super.fileString() + "," + contact;
    }

    /**
     * Clone a deep copy of the current object
     * @return a deep copy of the current object
     */
    public Object clone(){
        try {
            return new Appointment(getDescription(), getLocation(), getDate().toString(), getTime().toString(), contact);
        } catch (FormatMismatchException e) {
            System.out.println("Error when cloning object type Appointment");
            return null;
        }
    }
}
