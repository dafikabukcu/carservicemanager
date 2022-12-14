import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ParkingSpace {

    public int parkID;
    public static ArrayList<Integer> parkIDs = new ArrayList<>();
    public static ArrayList<Integer> rentedParkSpaces = new ArrayList<>();
    public static int parkRentalCounter;
    public int parkVolume;


    public Person personParking;
    public boolean hasRepaired;
    public static ArrayList<Person> parkedPersons = new ArrayList<>();



    public ParkingSpace(){

    }

    // Max m2 per one park space: 35.       Length: 5m + 5m(the area for the car to move in/move out)     Width: 3.5m.
    public ParkingSpace(int parkVolume){
        this.parkVolume = parkVolume;
        double parkSpace = parkVolume/35;
        for (int i=0; i<parkSpace; i++){
            parkID = ThreadLocalRandom.current().nextInt(1, 100);
            if (parkIDs.contains(parkID)){
                parkID = ThreadLocalRandom.current().nextInt(1, 100);
                parkIDs.add(parkID);
            }else{
                parkIDs.add(parkID);
            }
        }
        System.out.println("Total Park Spaces: " + parkIDs.size());
    }


    public void rentParkSpace(Person person, int parkNum){
        if (hasRepaired){
            try{
                LocalDate currentDate = LocalDate.now();
                LocalDate deadline = currentDate.plusDays(14);

                this.personParking = person;
                parkedPersons.add(personParking);
                int owner = parkedPersons.indexOf(personParking);
                int rentalSpace = parkIDs.get(parkNum);
                if (rentedParkSpaces.contains(rentalSpace)){
                    System.out.println("Park Space " + parkNum + " isn't available.");
                }else{
                    rentedParkSpaces.add(rentalSpace);
                    person.rentedParkSpace = parkNum;
                    System.out.println("Successfuly rented Park Space " + parkNum + " for " + person.name);
                    parkRentalCounter++;
                }
                Person.personsParked.add(person);
            }catch (IndexOutOfBoundsException e){
                System.out.println("No Park Space left.");
            }

        }else{
            System.out.println(person + " didn't make a repairment in our service.");
        }

    }

    public static void checkParkSpace(int parkNum) {
        try {
            int rentalSpace = parkIDs.get(parkNum);
            if (rentedParkSpaces.contains(parkIDs.get(parkNum))) {
                System.out.println("Park Space " + parkNum + " is in use.");
                System.out.println("Owner: " + Person.personsParked.get(parkNum).name + " " + Person.personsParked.get(parkNum).surname);
            }else{
                System.out.println("You can rent Park Space " + parkNum);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Can't find the Park Space " + parkNum);
        }
    }

}
