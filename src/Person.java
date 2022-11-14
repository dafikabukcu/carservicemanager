import java.time.LocalDate;
import java.util.*;

public class Person{

    // Personal Information
    public String name;
    public String surname;
    public int personID;
    public String address;
    public LocalDate birthDate;
    public LocalDate rentalDate;


    public static ArrayList<Person> allPersons = new ArrayList<>();

    public static ArrayList<Vehicle> personVehicles = new ArrayList<>();
    public static HashMap<Person, Integer> personsRentedd = new HashMap<>();
    public static HashMap<Person, Integer> personsDebt = new HashMap<>();

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
        Set<Map.Entry<Integer, Person>> authorizedPersons = Storage.authorizedPersons.entrySet();
        try {
            int room = personsRentedd.get(person);
            int debt = personsDebt.get(person);
            System.out.println(person.name + " has the room: " + room + " Needs to pay: " + debt);
            for (Map.Entry<Integer, Person> w : authorizedPersons) {
                if (w.getValue() == person) {
                    System.out.println("And authorized in Storage: " + w.getKey());
                }
            }
        }catch (NullPointerException e){
            System.out.println(person.name + " " + person.surname + " does not have any rented rooms.");
            for (Map.Entry<Integer, Person> w : authorizedPersons){
                if (w.getValue() == person){
                    System.out.println("But is authorized in Storage: " + w.getKey());
                }
            }
        }
    }

}
