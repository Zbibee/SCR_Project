package CarRepairShop;

import java.util.function.Supplier;

public class BufferInfo {

    private final Supplier<Integer> getHowManyCarsInParking;
    private final int totalCapacity;

    public BufferInfo(Supplier<Integer> getHowManyCarsInParking,
                      int totalCapacity) {
        this.getHowManyCarsInParking = getHowManyCarsInParking;
        this.totalCapacity = totalCapacity;
    }

    public int size() {
        return getHowManyCarsInParking.get();
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public boolean isFull(){
        return getHowManyCarsInParking.get() == totalCapacity;
    }

    public boolean isEmpty() {
        return getHowManyCarsInParking.get() == 0;
    }
}