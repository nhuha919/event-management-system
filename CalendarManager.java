public class CalendarManager {
    public static void main(String[] args){
        
        System.out.println("\nTest case 1: reading events from the file to the calendar (ignoring badly formatted events)");
        Calendar calendar = new Calendar();
        calendar.readEvents("events.txt");
        System.out.println(calendar.toString());

        System.out.println("\nTest case 2: find an event (successful)");
        Event e = calendar.find("Tutoring");
        if(e == null){
            System.out.println("Event \"Tutoring\" not found");
        }
        else{
            System.out.println("Event \"Tutoring\" found: \n" + e.toString());
        }

        System.out.println("\nTest case 3: find an event (fail)");
        e = calendar.find("Department meeting");
        if(e == null){
            System.out.println("Event \"Department Meeting\" not found");
        }
        else{
            System.out.println("Event \"Department Meeting\" found: \n" + e.toString());
        }

        System.out.println("\nTest case 4: remove an event (successful)");
        boolean found = calendar.remove("Tutoring");
        if(found){
            System.out.println("Event \"Tutoring\" found and removed successfully");
            System.out.println(calendar.toString());
        }
        else{
            System.out.println("Event \"Tutoring\" not found");
        }

        System.out.println("\nTest case 5: remove an event (fail)");
        found = calendar.remove("Department meeting");
        if(found){
            System.out.println("Event \"Department meeting\" found and removed successfully");
        }
        else{
            System.out.println("Event \"Department meeting\" not found");
        }

        System.out.println("\nTest case 6: find events by date (no events)");
        Event[] events = calendar.findByDate("01/01/0000");
        if(events == null){
            System.out.println("0 events found with the date \"01/01/0000\"");
        }
        else{
            System.out.println(events.length + " events found with the date \"01/01/0000\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }
        System.out.println("\nTest case 7: find events by date (one event)");
        events = calendar.findByDate("01/20/2025");
        if(events == null){
            System.out.println("0 events found with the date \"01/20/2025\"");
        }
        else{
            System.out.println(events.length + " events found with the date \"01/20/2025\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }
        System.out.println("\nTest case 8: find events by date (more than one event)");
        events = calendar.findByDate("01/23/2025");
        if(events == null){
            System.out.println("0 events found with the date \"01/23/2025\"");
        }
        else{
            System.out.println(events.length + " events found with the date \"01/23/2025\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }

        System.out.println("\nTest case 9: find events by location (no events)");
        events = calendar.findByLocation("PA-112");
        if(events == null){
            System.out.println("0 events found with the location \"PA-112\"");
        }
        else{
            System.out.println(events.length + " events found with the location \"PA-112\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }
        System.out.println("\nTest case 10: find events by location (one event)");
        events = calendar.findByLocation("BC-307");
        if(events == null){
            System.out.println("0 events found with the location \"BC-307\"");
        }
        else{
            System.out.println(events.length + " events found with the location \"BC-307\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }
        System.out.println("\nTest case 11: find events by location (more than one event)");
        events = calendar.findByLocation("RB-184");
        if(events == null){
            System.out.println("0 events found with the location \"RB-184\"");
        }
        else{
            System.out.println(events.length + " events found with the location \"RB-184\":");
            for(Event ev: events){
                System.out.println(ev);
            }
        }

        System.out.println("\nTest case 12: sort the events by location");
        calendar.sort(true);
        System.out.println(calendar.toString());

        System.out.println("\nTest case 13: sort the events by date and time");
        calendar.sort(false);
        System.out.println(calendar.toString());

        System.out.println("\nTest case 14: save the events to the file");
        calendar.saveEvents("events.txt");
        System.out.println(calendar.toString());

        System.out.println("\nTest case 15: read the updated list of events from the file");
        calendar.readEvents("events.txt");
        System.out.println(calendar.toString());

        System.out.println("\nTest case 16: get the list of meetings");
        Event[] meetings = calendar.getMeetings();
        if(meetings == null){
            System.out.println("There are no meetings in the calendar");
        }
        else{
            System.out.println("There are " + meetings.length + " meetings in the calendar:");
            for(Event ev: meetings){
                System.out.println(ev);
            }
        }

        System.out.println("\nTest case 17: get the list of appointments");
        Event[] appointments = calendar.getAppointments();
        if(appointments == null){
            System.out.println("There are no appointments in the calendar");
        }
        else{
            System.out.println("There are " + appointments.length + " appointments in the calendar:");
            for(Event ev: appointments){
                System.out.println(ev);
            }
        }

        System.out.println("\nTest case 18: clone the calendar");
        Calendar c = (Calendar) (calendar.clone());
        System.out.println(calendar.toString());

        System.out.println("\nTest case 19: compare calendars using equals (true)");
        System.out.println("Calendar 1:\n" + calendar.toString());
        System.out.println("Calendar 2:\n" + c.toString());
        System.out.println("The two calendars are equals? " + calendar.equals(c));

        System.out.println("\nTest case 20: compare calendars using equals (false)");
        e = c.find("Research Seminar");
        e.setDescription("Research Symposium");
        System.out.println("Calendar 1:\n" + calendar.toString());
        System.out.println("Calendar 2:\n" + c.toString());
        System.out.println("The two calendars are equals? " + calendar.equals(c));
    }
}
