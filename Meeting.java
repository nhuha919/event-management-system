/**
 * Class Meeting to model entity Meeting
 * Subclass of Event
 * @author Nhu Ha
 * @version 3.0
 */
public class Meeting extends Event{
    private String host;
    private int guests;
    /**
     * Default constructor
     */
    public Meeting(){
        super();
        host = "";
        guests = 0;
    }

    /**
     * 6-parameter constructor
     * @param description initial value for the description
     * @param location initial value for the location
     * @param date initial value for the date
     * @param time initial value for the time
     * @param host initial value of the host
     * @param guests initial value of the guests
     */
    public Meeting(String description, String location, String date, String time, String host, int guests) throws FormatMismatchException{
        super(description, location, date, time);
        this.host = host;
        this.guests = guests;
    }

    /**
     * Getter for the host
     * @return value of the host
     */
    public String getHost(){return host;}
    /**
     * Getter for the guests
     * @return value of the guests
     */
    public int getGuests(){return guests;}

    /**
     * Setter for the host
     * @param host value of the host
     */
    public void setHost(String host){this.host = host;}
    /**
     * Setter for the guests
     * @param guests value of the guests
     */
    public void setGuests(int guests){this.guests = guests;}

    /**
     * Accessor for the Meeting attributes
     * @return formatted String with the object attributes
     */
    @Override
    public String toString(){
        return String.format("%-10s\t%s\t%-10s\t%-10d", "Meeting", super.toString(), host, guests);
    }

    /**
     * Accessor for the Meeting attributes
     * @return formatted String with the object attributes seperated by ","
     */
    public String fileString(){
        return "Meeting," + super.fileString() + "," + host + "," + guests;
    }

    /**
     * Clone a deep copy of the current object
     * @return a deep copy of the current object
     */
    public Object clone(){
        try {
            return new Meeting(getDescription(), getLocation(), getDate().toString(), getTime().toString(), host, guests);
        } catch (FormatMismatchException e) {
            System.out.println("Error when cloning object type Meeting");
            return null;
        }
    }
}
