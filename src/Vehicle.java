import java.util.ArrayList;

public class Vehicle {

    public int m3;
    public int enginePower;
    public String vehicleType;
    public String engineType;

    public int length;
    public int width;
    public int height;

    public final ArrayList<String>vehicleTypes = new ArrayList<>();

    public Vehicle(){
       vehicleTypes.add("Offroad");
       vehicleTypes.add("City Car");
       vehicleTypes.add("Motorcycle");
       vehicleTypes.add("Amphibious");
    }

    public Vehicle(int m3, int enginePower, int vehicleTypeNum, String engineType){
        if (vehicleTypeNum == 1) vehicleType = "Offroad";
        if (vehicleTypeNum == 2) vehicleType = "City Car";
        if (vehicleTypeNum == 3) vehicleType = "Motorcycle";
        if (vehicleTypeNum == 4) vehicleType = "Amphibious";

        for (String w : vehicleTypes){
            if (vehicleType.equals(w.toLowerCase())){
                this.vehicleType = vehicleType;
                this.m3 = m3;
                this.enginePower = enginePower;
                this.engineType = engineType;
            }else{
                System.out.println("We do not support this type of vehicle here.");
            }
        }
    }

    public Vehicle(int length, int width, int height, int enginePower, int vehicleTypeNum, String engineType){
        if (vehicleTypeNum == 1) vehicleType = "Offroad";
        if (vehicleTypeNum == 2) vehicleType = "City Car";
        if (vehicleTypeNum == 3) vehicleType = "Motorcycle";
        if (vehicleTypeNum == 4) vehicleType = "Amphibious";
        for (String w : vehicleTypes){
            if (vehicleType.equals(w)){
                this.vehicleType = vehicleType;
                this.length = length;
                this.width = width;
                this.height = height;
            }else{
                System.out.println("We do not support this type of vehicle here.");
            }
        }
    }



}
