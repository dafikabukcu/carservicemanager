import java.time.LocalDate;
import java.util.ArrayList;

public class Person{

    // Personal Information
    public String name;
    public String surname;
    public int personID;
    public String address;
    public LocalDate birthDate;
    public LocalDate rentalDate;


    public static ArrayList<Person> allPersons = new ArrayList<>();

    public static ArrayList<Person> personsRented = new ArrayList<>();

    public static ArrayList<Person> personsParked = new ArrayList<>();


    public int rentedRoom;

    public int rentedParkSpace;

    //
    public boolean isAuthorized;


    public Person(){

    }

    public Person(String name, String surname, int personID) {
        this.name = name;
        this.surname = surname;
        this.personID = personID;
    }


    public static void checkPerson(Person person){
        int idx = personsRented.indexOf(person);
        try{
            int room = personsRented.get(idx).rentedRoom;
            System.out.println(person.name + " has the room: " + room);
        }catch (IndexOutOfBoundsException e){
            System.out.println(person.name + " " + person.surname + " does not have any rented rooms.");
        }
    }

}
