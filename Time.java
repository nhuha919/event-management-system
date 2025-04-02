/**
 * Class to model entity Time 
 * impelments Comparable<Time>
 * @author Nhu Ha
 * @version 3.0
 */
public class Time implements Comparable<Time>{
    private int hours, minutes;
    
    /**
     * Default constructor
     */
    public Time(){
        hours = minutes = 0;
    }

    /**
     * 1-parameter constructor 
     * @param time value of the time
     * @throws FormatMismatchException exception when the time format is invalid
     */
    public Time(String time) throws FormatMismatchException{
        if (time.matches("\\d{1}:\\d{2}"))
            time = "0" + time;
        if (!time.matches("\\d{2}:\\d{2}"))
            throw new FormatMismatchException("Invalid time format (expected: hh:mm): ");
         
        String[] parts = time.split(":");
        hours = Integer.parseInt(parts[0]);
        minutes = Integer.parseInt(parts[1]);

        if (hours < 0 || hours > 23)
            throw new FormatMismatchException("Invalid value for hours (0 to 23): " + hours);
        if (minutes < 0|| minutes > 59)
            throw new FormatMismatchException("Invalid value for minutes (0 to 59): " + minutes);
    }
 
    /**
     * Getter for the hours
     * @return value of the hours
     */
    public int getHours(){return hours;}
    /**
     * Getter for the minutes
     * @return value of the minutes
     */
    public int getMinutes(){return minutes;}

    /**
     * Setter for the hours
     * @param hours value of the hours
     */
    public void setHours(int hours){this.hours = hours;}
    /**
     * Setter for the minutes
     * @param minutes value of the minutes
     */
    public void setMinutes(int minutes){this.minutes = minutes;}

    /**
     * Accessor to the Time attributes
     * @return formatted String of the Time attributes
     */
    public String toString(){
        return String.format("%02d:%02d", hours, minutes);
    }

    /**
     * Compare object Time time with the current object Time
     * @param time time to compare with
     * @return 0 if current object Time equals date
     *         1 if current object Time is larger 
     *         -1 if current object Time is smaller
     */
    public int compareTo(Time time){
        if (this.getHours() != time.getHours()) 
            return Integer.compare(this.getHours(), time.getHours());
        else if (this.getMinutes() != time.getMinutes()) // this.getHours() = time.getHours()
            return Integer.compare(this.getMinutes(), time.getMinutes());
        else return 0; // this.getMinutes() == time.getMinutes()
    }

    /**
     * Check if current Date equals Object obj
     * @param obj object to compare with
     * @return true if equals, false if not
     */
    public boolean equals(Object obj){
        if (obj instanceof Time){
            Time time = (Time) obj;
            if (this.getHours() == time.getHours() && this.getMinutes() == time.getMinutes())
                return true;
            else return false;
        }
        else return false;
    }
}
