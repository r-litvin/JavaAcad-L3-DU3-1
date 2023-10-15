import java.math.BigDecimal;


public class Room {
    /**
     * Pokoj je identifikován svým číslem.
     * O každém pokoji chceme evidovat, kolik lůžek tam je (neřeš přistýlky apod.),
     * zda má pokoj balkón a
     * zda má výhled na moře a
     * jaká je cena pokoje za jednu noc
     * (předpokládej, že je cena pevně daná bez ohledu na sezónu a počet ubytovaných).
     */
    private int number;
    private int beds;
    private boolean balcony;
    private boolean view;
    private BigDecimal pricePerNight;

    public Room(int number, int beds, boolean hasBalcony, boolean hasView, BigDecimal pricePerNight){
        this.number = number;
        this.beds = beds;
        this.balcony = hasBalcony;
        this.view = hasView;
        this.pricePerNight = pricePerNight;
    }

    //region: setters and getters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    //endregion
}
