import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private List<Booking> listOfBookings = new ArrayList<>();

    public void addBooking(Room room, List<Guest> guests, LocalDate stayStart, LocalDate stayEnd){
        Booking newBooking = new Booking(room, guests, stayStart, stayEnd);
        this.listOfBookings.add(newBooking);
    }

    public void addBooking(Room room, List<Guest> guests, LocalDate stayStart, LocalDate stayEnd, VacationType vacationType){
        Booking newBooking = new Booking(room, guests, stayStart, stayEnd, vacationType);
        this.listOfBookings.add(newBooking);
    }

    public void addBooking(Room room, List<Guest> guests){
        Booking newBooking = new Booking(room, guests);
        this.listOfBookings.add(newBooking);
    }

    public void addBooking(Booking newBooking){
        this.listOfBookings.add(newBooking);
    }


    public void removeBooking(Booking oldBooking){
        this.listOfBookings.remove(oldBooking);
    }

    public Booking getBooking(int index){
        return this.listOfBookings.get(index);
    }

    public List<Booking> getBookings(){
        return this.listOfBookings;
    }

    public void clearBookings(){
        this.listOfBookings.clear();
    }

    public int getNumberOfWorkingBookings(){
        int workingBookingCount=0;
        for (Booking booking : this.listOfBookings){
            if (booking.getVacationType() == VacationType.BUSINESS){
                workingBookingCount++;
            }
        }
        return workingBookingCount;
    }

    public double getAverageGuests(){
        double sumGuests=0.0;
        for (Booking booking : this.listOfBookings){
            sumGuests += (double) booking.getNumberOfGuests();
        }
        return sumGuests/this.listOfBookings.size();
    }


    public String toString(){
        String outputString = "";
        for (Booking booking : this.listOfBookings){
            outputString += booking.toString() + "\n";
        }
        return outputString;
    }
}
