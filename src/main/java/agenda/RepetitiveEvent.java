package agenda;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

import static java.time.LocalTime.now;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {
    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    protected ChronoUnit frequency;
    protected ArrayList<LocalDate> datesException=new ArrayList<LocalDate>();
    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        datesException.add(date);
    }

    public boolean isInDay(LocalDate aDay){
        if (!datesException.contains(aDay) && myStart.toLocalDate().compareTo(aDay)<=0) {
            LocalDateTime startOccu=myStart;
            while(startOccu.toLocalDate().compareTo(aDay)<=0){
                startOccu=startOccu.plus(frequency.getDuration());
            }
            startOccu=startOccu.minus(frequency.getDuration());
            if (startOccu.toLocalDate().compareTo(aDay)<=0 && startOccu.plus(myDuration).toLocalDate().compareTo(aDay)>=0) return true;
        }
        return false;
    }

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        return frequency;
    }

}
