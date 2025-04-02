/**
 * Class to model entity Date
 * implements Comparable<Date>
 * @author Nhu Ha
 * @version 3.0
 */
public class Date implements Comparable<Date>{
    private int month, day, year;

    /**
     * Default constrcutor
     */
    public Date(){
        month = day = year = 0;
    }

    /**
     * 1-parameter constructor
     * @param date value of the date
     * @throws FormatMismatchException exception when the format of date is invalid
     */
    public Date(String date) throws FormatMismatchException{
        if (date.matches("\\d{2}/\\d{2}/\\d{4}")){
            String[] parts = date.split("/");
            month = Integer.parseInt(parts[0]);
            day = Integer.parseInt(parts[1]);
            year = Integer.parseInt(parts[2]);

            if (month < 1 || month > 12)
                throw new FormatMismatchException("Invalid Month (expected: 1 to 12): " + month);
            if (day < 1 || day > 31)
                throw new FormatMismatchException("Invalid Day (expected: 1 to 31): " + day);
            if (year < 2010 || year > 2030)
                throw new FormatMismatchException("Invalid Year (expected: 2010 to 2030): " + year);
        } 
        else throw new FormatMismatchException("Invalid date format (expected: mm/dd/yyyy): " + date);
    }

    /**
     * Getter for the month
     * @return value of the month
     */
    public int getMonth(){return month;}
    /**
     * Getter for the day
     * @return value of the day
     */
    public int getDay(){return day;}
    /**
     * Getter for the year
     * @return value of the year
     */
    public int getYear(){return year;}

    /**
     * Setter for the month
     * @param month value of the month
     */
    public void setMonth(int month){this.month = month;}
    /**
     * Setter for the day
     * @param day value of the day
     */
    public void setDay(int day){this.day = day;}
    /**
     * Setter for the year
     * @param year value of the year
     */
    public void setYear(int year){this.year = year;}

    /**
     * Accessor to the Date attributes
     * @return formatted String of the Date attributes
     */
    @Override
    public String toString(){
        return String.format("%02d/%02d/%04d", month, day, year);
    }

    /**
     * Compare object Date date with the current object Date
     * @param date date to compare with
     * @return 0 if current object Date equals date
     *         1 if current object Date is larger 
     *         -1 if current object Date is smaller
     */
    public int compareTo(Date date){
        if (this.getYear() != date.getYear()) 
            return Integer.compare(this.getYear(), date.getYear());
        else if (this.getMonth() != date.getMonth()) // this.getYear() == date.getYear()
            return Integer.compare(this.getMonth(), date.getMonth());
        else if (this.getDay() != date.getDay()) // this.getMonth() == date.getMonth()
            return Integer.compare(this.getDay(), date.getDay());
        else return 0; // this.getDay() == date.getDay()
    }

    /**
     * Check if current Date equals Object obj
     * @param obj object to compare with
     * @return true if equals, false if not
     */
    public boolean equals(Object obj){
        if (obj instanceof Date){
            Date date = (Date)obj;
            if (this.getMonth() == date.getMonth() && this.getDay() == date.getDay() && this.getYear() == date.getYear())
                return true;
            else return false;
        } 
        return false;
    }
}