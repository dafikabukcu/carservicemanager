public class ConsumerWarehouse {

    public int consumerRoom = 0;
    public int consumerVolume = 0;

    public int length = 0;
    public int width = 0;
    public int height = 0;

    public ConsumerWarehouse(){

    }

    // With cubic meters
    public ConsumerWarehouse(int consumerVolume){
        this.consumerVolume = consumerVolume;
    }

    // With length, width and height
    public ConsumerWarehouse(int length, int width, int height){
        this.length = length;
        this.width = width;
        this.height = height;
    }

}
