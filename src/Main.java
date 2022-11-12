import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Storage storage = new Storage(20);
//        Storage.checkStorage(0);
//        Person person = new Person("June", "Hilson", 213);
//        Person.persons.add(person);
//        storage.rentStorage(person, 0);
//        Storage.checkStorage(1);
//        Storage.checkStorage(0);
//        Person.checkPerson(person);
//
//        System.out.println();
//
//        Storage storage2 = new Storage(20);
//        Person person2 = new Person("Harry", "Whistle", 121);
//        Person.persons.add(person2);
//        storage2.rentStorage(person2, 1);
//        Person.checkPerson(person2);
//        Storage.checkStorage(1);

        Storage storage = new Storage();

        Scanner scan = new Scanner(System.in);

        while (true){
            System.out.println("\nCar-Service Manager v0.001");
            System.out.println("Available Commands:" +
                    "\n1.Create Storage" +
                    "\n2.Create Person" +
                    "\n3.Rent Storage" +
                    "\n4.Check Storage" +
                    "\n5.Check Person" +
                    "\n6.Create ParkSpace" +
                    "\n7.Check ParkSpace"

            );
            int num = scan.nextInt();

            switch (num){
                case 1:
                    System.out.println("Specify a volume. (Max 20)");
                    int volume = scan.nextInt();
                    new Storage(volume);
                    break;
                case 2:
                    System.out.println("Specify name, surname and ID.");
                    System.out.print("Name: ");
                    String name = scan.next();
                    System.out.print("\nSurname: ");
                    String surname = scan.next();
                    System.out.println("\nID: ");
                    int personID = scan.nextInt();

                    Person.allPersons.add(new Person(name, surname, personID));
                    break;
                case 3:
                    System.out.println("Specify a person ID");
                    System.out.print("Person ID: ");
                    personID = scan.nextInt();
                    for (Person w:Person.allPersons){
                        if (w.personID == personID){
                            int idx = Person.allPersons.indexOf(w);
                            Person getPerson = Person.allPersons.get(idx);
                            storage.rentStorage(getPerson, Storage.rentalCounter);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Specify a Storage Number");
                    System.out.print("Storage Number: ");
                    int strgNum = scan.nextInt();
                    Storage.checkStorage(strgNum);
                    break;
                case 5:
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
                case 6:
                    System.out.println("Which operation would you like to do? \n1.Specify length, width and height \nJust specify m3");
                    int answr = scan.nextInt();
                    if (answr == 1){
                        System.out.print("Specify length: ");
                        int len = scan.nextInt();
                        System.out.print("Specify width: ");
                        int width = scan.nextInt();
                        System.out.print("Specify height: ");
                        int height = scan.nextInt();
                        new ParkingSpace(len, width, height);
                    }
                    if (answr == 2){
                        System.out.println("Specify m3: ");
                        int m3 = scan.nextInt();
                        new ParkingSpace(m3);
                    }else{
                        System.out.println("You can either type: \n1 - (To specify length, width and height) \n2 - (To only specify m3)");
                    }
                    break;
                case 7:
                    System.out.println("Specify a Parking number: ");
                    int parkNum = scan.nextInt();
                    ParkingSpace.checkParkSpace(parkNum);
                    break;

            }
        }


    }
}
