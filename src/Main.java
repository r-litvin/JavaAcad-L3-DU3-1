import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    //tasks:
    //task01: addBooking in BookingManager
    //  tested in fillBookings01
    //task02: gedBooking(index) in BookingManager
    //  tested in test02
    //task03: getBookings() method in BookingManager
    //  tested in test02
    //task04: clearBookings() method in BookingManager
    //  tested in test02
    //task05: getNumberOfWorkingBookings in BookingManager
    //  tested in test02
    //task06: getAverageGuests in BookingManager
    //  tested in test02
    //tasks07 - 10: fillBookings method in Main
    //  tested in fillBookings01
    //task07: Karel Dvořák, narozen 15. 5. 1990, si rezervuje pokoj číslo 3 od 1. 6. 2023 do 7. 6. 2023. Bude to pracovní pobyt.
    //task08: Jiný pan Karel Dvořák, narozen 3. 1. 1979, si rezervuje pokoj číslo 2 od 18. 7. 2023 do 21. 7. 2023. Bude to rekreační pobyt.
    //task09: Fyzioterapeutka Karolína Tmavá si pro své klienty rezervuje pokoj číslo 2 na dvoudenní pobyty v měsíci srpnu. Vytvoř 10 dvoudenních rezervací pro rekreační pobyty:
    //task10: Fyzioterapeutka Karolína Tmavá z předchozího úkolu si dále rezervuje pokoj číslo 3 na celý srpen (od 1.8. do 31.8.).
    //  tested in fillBookings01
    //task11: V hlavní třídě projektu připrav metodu pro výpis seznam všech rezervací ve formátu...
    //  method is printAllBookings(), tested in test02
    //task12: Připrav metodu pro výpis prvních 8 rezervací, které jsou určeny pro rekreaci
    //  method is printVacationBookings(), tested in test02
    //task13: Připrav v hlavní třídě metodu printGuestStatistics, která vypíše...
    //  tested in test02
    //task14: Do třídy Booking přidej metodu getBookingLength
    //  tested in test02, used in Booking.getPrice()
    //task15: Přidej do třídy Booking metodu getPrice, která spočítá celkovou cenu objednávky.
    //  used in printBooking, needed for tasks 11 & 12
    //task16: Do metody main přidej volání všech metod, které jsi v tomto úkolu vytvořil
    //  prepared method test02 to package the calls

    public static void main(String[] args) {
        BookingManager myBookings = new BookingManager();
        //test01();
        //task16: demonstrate/test that everything works
        test02(myBookings, true);

        System.out.println("End OK.");
    }

    public static void test02(BookingManager bookings, boolean verbose){
        if(verbose) System.out.println("\nTest of BookingManager class usage:");
        fillBookings01(bookings);
        if(verbose) System.out.println("Test BookingManager class filled successfully.");
        if(verbose) System.out.println("Total number of bookings is "+bookings.getNumberOfBookings());
        //task02: gedBooking(index) in BookingManager
        if(bookings.getNumberOfBookings()>2){
            Booking dummyBooking = bookings.getBooking(2);
            if (verbose) System.out.println("getBooking(2) => "+dummyBooking);
        }

        //task03: getBookings() method in BookingManager
        //task12
        printVacationBookings(bookings.getBookings(), 8);
        //task05: getNumberOfWorkingBookings()
        System.out.println("Number of business trip bookings is "+bookings.getNumberOfWorkingBookings());
        //task06: getAverageGuests in BookingManager
        System.out.println("Avg number of guests is "+String.format("%.2f", bookings.getAverageGuests()));
        //task11:
        printAllBookings(bookings);
        //task13: Guest Statistics
        printGuestStatistics(bookings.getBookings());
        //task14: getBookingLength method in Booking
        int bookingLength0 = bookings.getBooking(0).getBookingLength();
        if (verbose) System.out.println("Booking #0 has length "+bookingLength0);

        //task04: clearBookings();
        bookings.clearBookings();
    }

    private static void printAllBookings(BookingManager myBookings) {
        // výpis všech rezervací
        //datumOd až datumDo: jméno hlavního hosta (datum narození)[počet hostů, výhledNaMoře ano/ne] za cena
        System.out.println("List of All Bookings:");
        for (Booking booking : myBookings.getBookings()){
            printBooking(booking);
        }
    }

    private static void printGuestStatistics(List<Booking> bookings){
        int oneGuest=0, twoGuests=0, manyGuests=0; //could use an array in principle?
        for (Booking booking : bookings){
            if (booking.getGuests().size() == 1) { oneGuest += 1;}
            else if (booking.getGuests().size() == 2) {twoGuests += 1;}
            else {manyGuests += 1;}
        }
        System.out.println("=== Guest Statistics ===");
        System.out.println("   1 Guest  : "+oneGuest+" bookings");
        System.out.println("   2 Guests : "+twoGuests+" bookings");
        System.out.println("many Guests : "+manyGuests+" bookings");
        System.out.println("=== ================ ===");

    }

    private static void printBooking(Booking booking){
        String outputString = "", dateFrom, dateTo;
        Guest guest0;
        BigDecimal numberOfNights;
        Map<Boolean, String> view = new HashMap<Boolean, String>();
        view.put(true, "ano");
        view.put(false, "ne");
        dateFrom = booking.getStayStart().format(DateTimeFormatter.ofPattern("d.M.yyyy"));
        dateTo = booking.getStayEnd().format(DateTimeFormatter.ofPattern("d.M.yyyy"));
        guest0 = booking.getGuests().get(0);

        //task15: uses Booking.getPrice()
        outputString += dateFrom + " až " + dateTo +": "
                + guest0.getName() + " "
                + guest0.getSurname() + " ("
                + guest0.getBirthday().format(DateTimeFormatter.ofPattern("d.M.yyyy")) + ")"
                + "[" + booking.getNumberOfGuests() + ", "
                + view.get(booking.getRoom().isView()) + "] za "
                + booking.getPrice()
                + " INR";
        System.out.println(outputString);
    }

    private static void printVacationBookings(List<Booking> bookings, int total){
        int count = 1;
        System.out.println("List of vacation bookings:");
        for (Booking booking : bookings){
            if (booking.getVacationType() == VacationType.LEISURE){
                printBooking(booking);
                count++;
                if (count > total){return;}
            }

        }
    }

    private static void fillBookings01(BookingManager bookings) {
        //task07: guest1
        Guest guest1 = new Guest ("Karel", "Dvořák",
                LocalDate.of(1990, 5,5));
        //task08: guest2
        Guest guest2 = new Guest ("Karel", "Dvořák",
                LocalDate.of(1979, 1,3));

        Guest guest22 = new Guest("Karla", "Dvořáková",
                LocalDate.of(1981, 9, 11));
        //task09 & task10: guest3
        Guest guest3 = new Guest ("Karolína", "Tmavá",
                LocalDate.of(1989, 11,17));
        //task08 & task09: room2
        Room room2 = new Room(2, 1,
                false, true,
                BigDecimal.valueOf(1950));
        //task07: room3
        Room room3 = new Room(3, 2,
                true, false,
                BigDecimal.valueOf(1750));

        //task07: new booking
        bookings.addBooking(room3, guestList(guest1),
                LocalDate.of(2023, 6, 1),
                LocalDate.of(2023, 6, 7),
                VacationType.BUSINESS);
        //task08: new booking
        bookings.addBooking(room2, guestList(guest2, guest22),
                LocalDate.of(2023, 7, 18),
                LocalDate.of(2023, 7, 21),
                VacationType.LEISURE);

        //task01: addBooking
        //task10
        Booking booking3 = new Booking(room3, guestList(guest3),
                LocalDate.of(2023, 8, 1),
                LocalDate.of(2023, 8, 31),
                VacationType.BUSINESS);
        bookings.addBooking(booking3);

        //task09: many consecutive bookings
        int day0 = 1;
        for (int i=0; i<10; i++){
            bookings.addBooking(room2, guestList(guest3),
                    LocalDate.of(2023, 8, day0 + 2*i),
                    LocalDate.of(2023, 8, day0 + 2*i + 1),
                    VacationType.BUSINESS);
        }

    }

    private static void test01() {
        Room room1 = new Room(101, 2,
                true, true,
                BigDecimal.valueOf(1950));
        Guest guest1 = new Guest("Antonin", "Svoboda",
                LocalDate.of(1965, 2, 3));
        Guest guest2 = new Guest("Antonie", "Svobodova",
                LocalDate.of(1967, 7, 12));
        BookingManager myBookings = new BookingManager();
        myBookings.addBooking(room1, guestList(guest1, guest2));
        myBookings.addBooking(room1, guestList(guest2),
                LocalDate.of(2023,11,7),
                LocalDate.of(2023, 11, 12),
                VacationType.BUSINESS);

    }

    private static List<Guest> guestList(Guest... args){
        List<Guest> newGuestList = new ArrayList<>();
        Collections.addAll(newGuestList, args);
        return newGuestList;
    }
}