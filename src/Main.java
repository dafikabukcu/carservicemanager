import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    //test
    public static void main(String[] args) {
        Storage storage = new Storage();

        Scanner scan = new Scanner(System.in);

        while (true){
            System.out.println("\nCar-Service Manager v0.0.2");
            System.out.println("Available Commands:" +
                    "\n1.Storage Options"+
                    "\n2.Person Options"+
                    "\n3.ParkSpace Options"

            );
            int opt = scan.nextInt();
            switch (opt){
                case 1:
                    System.out.println("Available Storage Options:\n1.Create Storage\n2.Check Storage\n3.Rent Storage\n4.Transfer Storage Rights\n5.Authorize a Person for Storage");
                    opt = scan.nextInt();
                    switch (opt){
                        case 1:
                            System.out.println("Which operation would you like to do? \n1.Just specify m3\n2.Specify length, width and height");
                            int answr = scan.nextInt();
                            if (answr == 1){
                                System.out.println("Specify a volume. (Max 20)");
                                int volume = scan.nextInt();
                                new Storage(volume);
                                break;
                            }else if (answr == 2){
                                System.out.print("Specify length: ");
                                int len = scan.nextInt();
                                System.out.print("Specify width: ");
                                int width = scan.nextInt();
                                System.out.print("Specify height: ");
                                int height = scan.nextInt();
                                new Storage(len, width, height);
                            }else{
                                System.out.println("Couldn't find the option.");
                                break;
                            }
                            break;

                        case 2:
                            System.out.println("Specify a Storage Number");
                            System.out.print("Storage Number: ");
                            int strgNum = scan.nextInt();
                            Storage.checkStorage(strgNum);
                            break;

                        case 3:
                            System.out.println("Specify a person ID");
                            System.out.print("Person ID: ");
                            int personID = scan.nextInt();
                            for (Person w:Person.allPersons){
                                if (w.personID == personID){
                                    int idx = Person.allPersons.indexOf(w);
                                    Person getPerson = Person.allPersons.get(idx);
                                    storage.rentStorage(getPerson, Storage.rentalCounter);
                                }
                            }
                            break;

                        case 4:
                            Person getPerson = new Person();
                            Person getPerson2 = new Person();
                            System.out.println("Type both person's ID that you'd like to transfer rights with: ");
                            System.out.println("Owner: ");
                            int p1 = scan.nextInt();
                            for (Person w : Person.allPersons){
                                if (w.personID == p1){
                                    int idx = Person.allPersons.indexOf(w);
                                    getPerson = Person.allPersons.get(idx);
                                }
                            }
                            System.out.println("New Owner: ");
                            int p2 = scan.nextInt();
                            for (Person w : Person.allPersons){
                                if (w.personID == p2){
                                    int idx = Person.allPersons.indexOf(w);
                                    getPerson2 = Person.allPersons.get(idx);
                                }
                            }
                            Storage.transferRights(getPerson, getPerson2);
                            break;

                        case 5:
                            System.out.println("Which Storage do you want to edit authorized persons?");
                            int strg = scan.nextInt();
                            System.out.println("Type the ID of the person whom you'd like to authorize:");
                            int prsn = scan.nextInt();
                            for (Person w : Person.allPersons){
                                if (w.personID == prsn){
                                    int idx = Person.allPersons.indexOf(w);
                                    Storage.authorizePerson(Person.allPersons.get(idx), strg);
                                }
                            }
                            break;


                    }
                case 2:
                    System.out.println("Available Person Options:\n1.Register Person\n2.Check Person");
                    opt = scan.nextInt();
                    switch (opt){
                        case 1:
                            System.out.println("Specify name, surname and ID.");
                            System.out.print("Name: ");
                            String name = scan.next();
                            System.out.print("\nSurname: ");
                            String surname = scan.next();
                            System.out.println("\nID: ");
                            int personID = scan.nextInt();

                            Person.allPersons.add(new Person(name, surname, personID));

                            System.out.println("Now give your vehicle information.");
                            //int m3, int enginePower, String vehicleType, String engineType
                            System.out.println("What type of vehicle do you have?");
                            System.out.println("1. Offroad");
                            System.out.println("2. City Car");
                            System.out.println("3. Motorcycle");
                            System.out.println("4. Amphibious");
                            int vehicleTypeNum = scan.nextInt();
                            System.out.println("Do you want to specify your car size by, \n1. m3\n2. length-width-height?");
                            int choice = scan.nextInt();
                            if (choice == 1){
                                System.out.println("What's the m3 of your vehicle?");
                                int size = scan.nextInt();
                                System.out.println("What type of engine do you have?");
                                String engineType = scan.next();
                                System.out.println("What's the engine power of your vehicle?");
                                int enginePower = scan.nextInt();
                                Person.personVehicles.add(new Vehicle(size,enginePower,vehicleTypeNum,engineType));
                            }
                            if (choice == 2){
                                System.out.println("What's the length of your car?");
                                int len = scan.nextInt();
                                System.out.println("What's the width of your car?");
                                int width = scan.nextInt();
                                System.out.println("What's the height of your car?");
                                int height = scan.nextInt();
                                System.out.println("What type of engine do you have?");
                                String engineType = scan.next();
                                System.out.println("What's the engine power of your vehicle?");
                                int enginePower = scan.nextInt();
                                Person.personVehicles.add(new Vehicle(len, width, height,enginePower,vehicleTypeNum,engineType));
                            }
                            break;
                        case 2:
                            System.out.println("Specify a Person ID");
                            System.out.print("Person ID: ");
                            personID = scan.nextInt();
                            for (Person w:Person.allPersons){
                                if (w.personID == personID){
                                    int idx = Person.allPersons.indexOf(w);
                                    Person getPerson = Person.allPersons.get(idx);
                                    Person.checkPerson(getPerson);
                                }
                            }
                            break;
                    }
                case 3:
                    System.out.println("Available ParkSpace Options:\n1.Create ParkSpace\n2.Check Park Lots");
                    opt = scan.nextInt();
                    switch (opt){
                        case 1:
                            System.out.println("Specify m2: ");
                            int m3 = scan.nextInt();
                            new ParkingSpace(m3);
                            break;

                        case 2:
                            System.out.println("Specify a Parking number: ");
                            int parkNum = scan.nextInt();
                            ParkingSpace.checkParkSpace(parkNum);
                            break;
                    }
            }
        }


    }
}
