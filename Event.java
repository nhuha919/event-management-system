/**
 * Class to model entity Event
 * implements Comparable<Event>, Cloneable
 * @author Nhu Ha
 * @version 3.0
 */
public abstract class Event implements Comparable<Event>, Cloneable{
    private String description, location;
    private Date date;
    private Time time;

    /**
     * Default constructor
     */
    protected Event(){
        description = location = "";
        date = new Date();
        time = new Time();
    }

    /**
     * 4-parameter constructor
     * @param description value of the description
     * @param location value of the location
     * @param date value of the date
     * @param time value of the time
     * @throws FormatMismatchException exeption when the format of the time or date is invalid
     */
    protected Event(String description, String location, String date, String time) throws FormatMismatchException{
        this.description = description;
        this.location = location;
        this.date = new Date(date);
        this.time = new Time(time);
    }

    /**
     * Getter for the description
     * @return value of the description
     */
    public String getDescription(){return description;}
    /**
     * Getter for the location
     * @return value of the location
     */
    public String getLocation(){return location;}
    /**
     * Getter for the date
     * @return value of the date
     */
    public Date getDate(){return date;}
    /**
     * Getter for the time
     * @return value of the time
     */
    public Time getTime(){return time;}

    /**
     * Setter for the description
     * @param description value of the description
     */
    public void setDescription(String description){this.description = description;}
    /**
     * Setter for the location
     * @param location value of the location
     */
    public void setLocation(String location){this.location = location;}
    /**
     * Setter for the date
     * @param date value of the date
     * @throws FormatMismatchException exeption when the format of the date is invalid
     */
    public void setDate(String date) throws FormatMismatchException{
        this.date = new Date(date);
    }
    /**
     * Setter for the time
     * @param time value of the time
     * @throws FormatMismatchException exeption when the format of the time is invalid
     */
    public void setTime(String time) throws FormatMismatchException{
        this.time = new Time(time);
    }

    /**
     * Accessor to the Event attributes
     * @return formatted String of the Event attributes
     */
    @Override
    public String toString(){
        return String.format("%-20s\t%-20s\t%-10s\t%-5s", description, location, date.toString(), time.toString());
    }

    /**
     * Accessor to the Event attributes
     * @return formatted String of the Event attributes separated by ","
     */
    public String fileString(){
        return description + "," + location + "," + date.toString() + "," + time.toString();
    }

    /**
     * Compare the date and time of 2 events
     * @param event event to compare with
     * @return 0 if they are equal
     *         1 if the date and time of current object is larger 
     *         -1 if the date and time of current object is smaller
     */
    public int compareTo(Event event){
        if (!this.getDate().equals(event.getDate())) 
            return this.getDate().compareTo(event.getDate());
        else if (!this.getTime().equals(event.getTime())) // dates are equal
            return this.getTime().compareTo(event.getTime());
        return 0; // times are equal
    }

    /**
     * Abstract method to clone a deep copy of the current object
     * @return a deep copy of the current object
     */
    public abstract Object clone();

    /**
     * Check if current Event equals Object obj
     * @param obj object to compare with
     * @return true if equals, false if not
     */
    public boolean equals(Object obj){
        if (obj instanceof Event) {
            Event event = (Event) obj;
            if (this.getDate().equals(event.getDate()) && this.getTime().equals(event.getTime())
                && this.getDescription().equals(event.getDescription()) && this.getLocation().equals(event.getLocation())) 
                return true;
        }
        return false;
    }
}
