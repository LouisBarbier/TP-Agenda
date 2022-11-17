package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive event that terminates after a given date, or after
 * a given number of occurrences
 */
public class FixedTerminationEvent extends RepetitiveEvent {

    
    /**
     * Constructs a fixed terminationInclusive event ending at a given date
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     */
    private LocalDate terminationInclusive;
    private long numberOfOccurrences;
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, LocalDate terminationInclusive) {
        super(title, start, duration, frequency);
        this.terminationInclusive=terminationInclusive;
        LocalDateTime startOccu=myStart;
        long nOccu=0;
        while(startOccu.toLocalDate().compareTo(terminationInclusive)<=0){
            startOccu=startOccu.plus(frequency.getDuration());
            nOccu++;
        }
        this.numberOfOccurrences=nOccu;
    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, long numberOfOccurrences) {
        super(title, start, duration, frequency);
        this.numberOfOccurrences=numberOfOccurrences;
        LocalDateTime startOccu=myStart;
        for(int i=1; i<numberOfOccurrences; i++){
            startOccu=startOccu.plus(frequency.getDuration());
        }
        this.terminationInclusive=startOccu.toLocalDate();
    }

    /**
     *
     * @return the termination date of this repetitive event
     */
    public LocalDate getTerminationDate() {
        return terminationInclusive;
    }

    public long getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public boolean isInDay(LocalDate aDay) {
        if (terminationInclusive.compareTo(aDay)>0 && myStart.toLocalDate().compareTo(aDay)<=0 && !datesException.contains(aDay)) {
            LocalDateTime startOccu = myStart;
            long nOccu = 1;
            while (startOccu.toLocalDate().compareTo(aDay) <= 0 && nOccu != numberOfOccurrences) {
                startOccu=startOccu.plus(frequency.getDuration());
                nOccu++;
            }
            if (nOccu != numberOfOccurrences) {
                startOccu=startOccu.minus(frequency.getDuration());
                if (startOccu.toLocalDate().compareTo(aDay) <= 0 && startOccu.plus(myDuration).toLocalDate().compareTo(aDay) >= 0)
                    return true;
            }
        }
        return false;
    }
}
