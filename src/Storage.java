import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Storage extends Warehouse{

    public int storageID;
    public static ArrayList<Integer> storageIDs = new ArrayList<>();
    public int storageVolume;

    public Person personTenant;
    public static ArrayList<Person> tenants = new ArrayList<>();

    public static ArrayList<Integer> rentedStorages = new ArrayList<>();

    public static int rentalCounter;



    public Storage(){

    }


    public Storage(int storageVolume){
        this.storageVolume = storageVolume;
        //storageID = (int)(Math.random()*100);
        storageID = ThreadLocalRandom.current().nextInt(1, 100);
        if (storageVolume>storageSpace){
            System.out.println("Maximum m3 avaiable is" + storageSpace + "m3 at the moment. You cannot occupy more.");
        }else{
            if (storageIDs.contains(storageID)){
                storageID = ThreadLocalRandom.current().nextInt(1, 100);
                storageIDs.add(storageID);
            }else{
                storageIDs.add(storageID);
            }
            storageSpace = storageSpace - storageVolume;
            System.out.println("Total Storages: " + storageIDs.size());
            System.out.println("Available Storage Space: " + storageSpace + "m3");
        }
    }


    public void rentStorage(Person person, int storageNum){
        try{
            this.personTenant = person;
            tenants.add(personTenant);
            int owner = tenants.indexOf(personTenant);
            int rentalRoom = storageIDs.get(storageNum);
            if (rentedStorages.contains(rentalRoom)){
                System.out.println("Room " + storageNum + " isn't available.");
            }else{
                rentedStorages.add(rentalRoom);
                person.rentedRoom = storageNum;
                System.out.println("Successfuly rented room " + storageNum + " for " + person.name);
                rentalCounter++;
            }
            Person.personsRented.add(person);
        }catch (IndexOutOfBoundsException e){
            System.out.println("No storage left.");
        }

    }

    public static void checkStorage(int storageNum) {
        try {
            int rentalRoom = storageIDs.get(storageNum);
            if (rentedStorages.contains(storageIDs.get(storageNum))) {
                System.out.println("Room " + storageNum + " is in use.");
                System.out.println("Owner: " + Person.personsRented.get(storageNum).name + " " + Person.personsRented.get(storageNum).surname);
            }else{
                System.out.println("You can rent room " + storageNum);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Can't find the storage " + storageNum);
        }
    }
}
