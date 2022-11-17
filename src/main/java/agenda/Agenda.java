package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    private ArrayList <Event> Events=new ArrayList<Event>();
    public void addEvent(Event e) {
        Events.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        ArrayList <Event> EventsDate=new ArrayList<Event>();
        for(Event e:Events){
            if(e.isInDay(day)){
                EventsDate.add(e);
            }
        }
        return EventsDate;
    }
}
