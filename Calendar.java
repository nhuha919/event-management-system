import java.util.Arrays;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.Normalizer.Form;

/**
 * Class to model entity Calendar
 * implements Cloneable
 * @author Nhu Ha
 * @version 3.0
 */
public class Calendar implements Cloneable{
    private Event[] events;
    private int count;

    /**
     * Default constructor 
     * creates an Event array with max 10 events
     * and initialized count with 0
     */
    public Calendar(){
        events = new Event[10];
        count = 0;
    }

    /**
     * Add an event to the events array
     * @param event the event to be added
     * @return true if success, false if the array is full
     */
    public boolean add(Event event){
        if (count < events.length){
            events[count] = event;
            count++;
            return true;
        }
        return false;
    }

    /**
     * Find an event in the array by description
     * @param description the description of the event to search for
     * @return the event if found, null if not found
     */
    public Event find(String description){
        for (int i = 0; i < count; i++){
            if (events[i].getDescription().equals(description))
                return events[i];
        }
        return null;
    }

    /**
     * Remove an event in the array by its desription
     * @param description the description to search for
     * @return true if success, false if not found
     */
    public boolean remove(String description){
        for (int i = 0; i < count; i++){
            if (events[i].getDescription().equals(description)){
                for (int j = i; j < count - 1; j++){
                    events[j] = events[j + 1];
                }
                count--;
                return true;
            }
        }
        return false;
    }

    /**
     * Find events by a specific date using linear search
     * @param date the date to search for
     * @return an array of matching events
     */
    public Event[] findByDate(String date){
        Event[] matchingEvents = new Event[count];
        int matchingCount = 0;

        for (int i = 0; i < count; i++){
            if (events[i].getDate().toString().equals(date)){
                matchingEvents[matchingCount] = events[i];
                matchingCount++;
            }
        }

        if (matchingCount == 0) return null;
        return Arrays.copyOf(matchingEvents, matchingCount);
    }

    /**
     * Find events by a specific location using linear search
     * @param loc the location to search for
     * @return an array of matching events
     */
    public Event[] findByLocation(String location){
        Event[] matchingEvents = new Event[count];
        int matchingCount = 0;

        for (int i = 0; i < count; i++){
            if (events[i].getLocation().equals(location)){
                matchingEvents[matchingCount] = events[i];
                matchingCount++;
            }
        }
        
        if (matchingCount == 0) return null;
        return Arrays.copyOf(matchingEvents, matchingCount);
    }

    /**
     * Sort events by their location using insertion sort
     * @param lOrDT if true sort by location, if false sort by date and time
     */
    public void sort(boolean lOrDT){
        if (lOrDT){
            for (int i = 0; i < count; i++){
                int j = i;
                Event current = events[i];
                while (j > 0 && current.getLocation().compareTo(events[j - 1].getLocation()) < 0){
                    events[j] = events[j - 1];
                    j--;
                }
                events[j] = current;
            }
        }
        else {
            java.util.Arrays.sort(events, 0, count);
        }
    }

    /**
     * Accessor to all the Event attributes
     * @return formatted String of all events and their attributes in tabular format
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s\t%-20s\t%-20s\t%-10s\t%-5s\t%-10s\t%-10s\n", 
                "Event Type", "Description", "Location", "Date", "Time", "Contact/Host", "Guests"));
        for (int i = 0; i < count; i++){
            sb.append(events[i].toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Reader to read Event from file and validate it
     * @param filename the name of the file
     */
    public void readEvents(String filename){
        try {
            Scanner fileReader = new Scanner(new File(filename));
            String line, type, description, location, date, time, contact, host;
            int guests;
            count = 0;
            while (fileReader.hasNextLine()){
                line = fileReader.nextLine();
                String[] parts = line.split(",");
                try {
                    if (parts.length < 6 || parts.length > 7)
                        throw new FormatMismatchException("Event Format Error: (Expected number of items: 6 or 7):");

                    type = parts[0];
                    description = parts[1];
                    location = parts[2];
                    date = parts[3];
                    time = parts[4];
                    
                    if (type.equals("Appointment")){
                        if (parts.length != 6)
                            throw new FormatMismatchException("Invalid format for Appointment: ");
                        contact = parts[5];
                        add(new Appointment(description, location, date, time, contact));
                    }
                    else if (type.equals("Meeting")){
                        if (parts.length != 7)
                            throw new FormatMismatchException("Invalid format for Meeting: ");
                        host = parts[5];
                        guests = Integer.parseInt(parts[6]);
                        add(new Meeting(description, location, date, time, host, guests));
                    }
                    else throw new FormatMismatchException("Event Type Error (Expected: Appointment or Meeting): " + type);
                }
                catch (FormatMismatchException e){
                    System.out.println(e.getMessage() + "\nLine: " + line);
                }
            }
            System.out.println(count + " events read from the file \"" + filename + "\"");
            fileReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot read file " + filename);
        }
    }

    /**
     * Save all the events to the file
     * @param filename the name of the file
     */
    public void saveEvents(String filename){
        try {
            PrintWriter fileWriter = new PrintWriter(new File(filename));
            for (int i = 0; i < count ; i++){
                fileWriter.println(events[i].fileString());
            }
            fileWriter.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Cannot write to file " + filename);
        }
    }

    /**
     * Get all the Meeting from the array of Event
     * @return an array of Meeting
     */
    public Event[] getMeetings(){
        Event[] meetings = new Event[count];
        int meetingCount = 0;

        for (int i = 0; i < count; i++){
            if (events[i] instanceof Meeting){
                meetings[meetingCount] = events[i];
                meetingCount++;
            }
        }
        return Arrays.copyOf(meetings, meetingCount);
    }

    /**
     * Get all the Appointment from the array of Event
     * @return an array of Appointment
     */
    public Event[] getAppointments(){
        Event[] appointments = new Event[count];
        int appointmentCount = 0;

        for (int i = 0; i < count; i++){
            if (events[i] instanceof Appointment){
                appointments[appointmentCount] = events[i];
                appointmentCount++;
            }
        }
        return Arrays.copyOf(appointments, appointmentCount);
    }

    /**
     * Check if 2 objects is equal
     * @param obj the object to compare the current object with
     * @return true if equals, false if not
     */
    public boolean equals(Object obj){
        if (obj instanceof Calendar){
            Calendar calendar = (Calendar) obj;
            if (count == calendar.count){
                for (int i = 0; i < count; i++) {
                    if (!events[i].equals(calendar.events[i]))
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Clone a deep copy of Calendar object
     * @return a calendar type Object 
     */
    public Object clone(){
        Calendar calendar = new Calendar();
        for ( int i = 0; i < count; i++){
            calendar.add((Event) events[i].clone());
        }
        return calendar;
    }
}
