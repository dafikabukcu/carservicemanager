import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Storage extends Warehouse{

    public int storageID;
    public static ArrayList<Integer> storageIDs = new ArrayList<>();
    public static HashMap<Integer, Person> authorizedPersons = new HashMap<>();
    public int storageVolume;


    public Person personTenant;
    public static ArrayList<Integer> rentedStorages = new ArrayList<>();

    public static ArrayList<Integer> owedMoneys = new ArrayList<>();

    public static int rentalCounter = 1;

    public int rentalCharge = 500;



    public Storage(){
        storageID = ThreadLocalRandom.current().nextInt(1, 83);
        if (storageIDs.contains(storageID)){
            storageID = ThreadLocalRandom.current().nextInt(1, 100);
            storageIDs.add(storageID);
        }else{
            storageIDs.add(storageID);
        }
        System.out.println("Total Storages: " + storageIDs.size());
        System.out.println("Available Storage Space: " + storageSpace + "m3");
    }


    public Storage(int storageVolume){
        if (storageVolume>108){
            System.out.println("You cannot take more than 108m3.");
        }else{
            this.storageVolume = storageVolume;
            storageID = ThreadLocalRandom.current().nextInt(1, 83);
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
    }

    public Storage(int length, int width, int height){
        if (height>3 || width>6 || length>6){
            System.out.println("Maximum rentable storage capacity: Height: 3m, Width: 6m, Length: 6m");
        }else{
            storageVolume = length*width*height;
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
    }


    public void rentStorage(Person person, int storageNum){
        try{
            this.personTenant = person;
            rentalCharge = ThreadLocalRandom.current().nextInt(500, 1500);
            owedMoneys.add(rentalCharge);
            int rentalRoom = storageIDs.get(storageNum);
            if (rentedStorages.contains(rentalRoom) || storageNum == 0){
                System.out.println("Room " + storageNum + " isn't available.");
            }else{
                rentedStorages.add(rentalRoom);
                person.rentedRoom = storageNum;
                System.out.println("Successfuly rented room " + storageNum + " for " + person.name);
                rentalCounter++;
            }
            Person.personsRentedd.put(person, storageNum);
            Person.personsDebt.put(person, rentalCharge);
        }catch (IndexOutOfBoundsException e){
            System.out.println("No storage left.");
        }

    }

    public static void checkStorage(int storageNum) {
        try {
            Set<Map.Entry<Person, Integer>> rentedPersons = Person.personsRentedd.entrySet();
            if (storageNum == 0){
                System.out.println("This is a staff-only storage.");
            }else{
                if (rentedStorages.contains(storageIDs.get(storageNum))) {
                    System.out.println("Room " + storageNum + " is in use.");
                    for (Map.Entry<Person, Integer> w : rentedPersons){
                        if (w.getValue() == storageNum){
                            System.out.println("Owner: " + w.getKey().name + " " + w.getKey().surname);
                        }
                    }
                }else{
                    System.out.println("You can rent room " + storageNum);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Can't find the storage " + storageNum);
        }
    }

    public static void authorizePerson(Person person, int storageNum){
        if (storageNum == 0){
            Scanner scan = new Scanner(System.in);
            System.out.println("This person has to be a staff member to be authorized here. Do you accept? (Yes/No)");
            String answr = scan.next().toLowerCase();
            if (answr == "yes"){
                int editStorage = storageIDs.get(storageNum);
                if (!rentedStorages.contains(editStorage)){
                    System.out.println("Storage needs to be rented to being authorized for new people.");
                }else{
                    authorizedPersons.put(storageNum, person);
                    System.out.println("Succesfully authorized " + authorizedPersons.get(storageNum).name + " for Storage: " + storageNum);
                }
            }else{
                System.out.println("Cannot authorize the person here then.");
            }

        }else{
            int editStorage = storageIDs.get(storageNum);
            if (!rentedStorages.contains(editStorage)){
                System.out.println("Storage needs to be rented to being authorized for new people.");
            }else{
                authorizedPersons.put(storageNum, person);
                System.out.println("Succesfully authorized " + authorizedPersons.get(storageNum).name + " for Storage: " + storageNum);
            }
        }
    }

    public static void transferRights(Person a, Person b){
        Set<Map.Entry<Person, Integer>> rentedPersons = Person.personsRentedd.entrySet();
        Set<Map.Entry<Person, Integer>> debtPersons = Person.personsDebt.entrySet();

        for (Map.Entry<Person, Integer> w : rentedPersons){
            if (w.getKey() == a){
                int rentd = Person.personsRentedd.remove(a);
                Person.personsRentedd.put(b, rentd);
                int debt = Person.personsDebt.remove(a);
                Person.personsDebt.put(b, debt);
            }
        }
        System.out.println("Successfuly transferred rights.");
    }
}
