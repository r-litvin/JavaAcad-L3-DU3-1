import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Booking {
    /**
     * V rámci jedné rezervace (booking) vždy
     * přiřazujeme pokoj jednomu nebo více hostům
     * na časové období mezi dvěma daty
     * (například pokoj číslo 3 na období od 15. 7. do 24. 7. 2021).
     * Pobyt je buď pracovní, nebo rekreační (type of vacation).
     */
    private Room room;
    private List<Guest> guests;
    private LocalDate stayStart;
    private  LocalDate stayEnd;
    private VacationType vacationType;

    public Booking(Room room, List<Guest> guests, LocalDate stayStart, LocalDate stayEnd, VacationType vacationType) {
        this.room = room;
        this.guests = guests;
        this.stayStart = stayStart;
        this.stayEnd = stayEnd;
        this.vacationType = vacationType;
    }

    public Booking(Room room,
                   List<Guest> guests,
                   LocalDate stayStart,
                   LocalDate stayEnd){
        this(room, guests, stayStart, stayEnd, VacationType.LEISURE);

    }

    public Booking(Room room,
                   List<Guest> guests){
        this(room, guests,
                LocalDate.now(),
                LocalDate.now().plusDays(6),
                VacationType.LEISURE);

    }

    //region: setters and getters

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public LocalDate getStayStart() {
        return stayStart;
    }

    public void setStayStart(LocalDate stayStart) {
        this.stayStart = stayStart;
    }

    public LocalDate getStayEnd() {
        return stayEnd;
    }

    public void setStayEnd(LocalDate stayEnd) {
        this.stayEnd = stayEnd;
    }

    public VacationType getVacationType() {
        return vacationType;
    }

    public void setVacationType(VacationType vacationType) {
        this.vacationType = vacationType;
    }


    //endregion

    public int getNumberOfGuests(){
        return this.guests.size();
    }

    public int getBookingLength(){
        return (int) ChronoUnit.DAYS.between(this.getStayStart(), this.getStayEnd());
    }

    public BigDecimal getPrice(){
        return this.room.getPricePerNight().multiply(BigDecimal.valueOf(this.getBookingLength()));
    }

    public String toString(){
        String outputString = "";
        String guests = "";
        for (Guest guest : this.guests){
            guests += guest.getSurname() + ", ";
        }
        outputString += "Room: " + this.room.getNumber()
                + " ( from "+this.stayStart.format(DateTimeFormatter.ofPattern("d.M.yyy"))
                + " to "+this.stayEnd.format(DateTimeFormatter.ofPattern("d.M.yyy"))+ " ), guests: "
                +guests;
        return outputString;
    }


}
