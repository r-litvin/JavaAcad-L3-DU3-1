import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Guest {
    // jméno, příjmení a datum narození.
    private String name;
    private String surname;
    private LocalDate birthday;
    public Guest(String name, String surname, LocalDate birthday){
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

//region: setters and getters
    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    //endregion

    public String toString(){
        String outputString = "";
        outputString += this.name + " " + this.surname + " ("
                + this.birthday.format(DateTimeFormatter.ofPattern("d.M.yyy"))+")";
        return outputString;
    }

}
