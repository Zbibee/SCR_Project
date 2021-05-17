package CarRepairShop;

import java.util.Random;

public class CarSimulator implements Runnable {
    private static final Random random = new Random();
    private final CarRepairShop carRepairShop;
    private final int ordersNumber;

    public CarSimulator(CarRepairShop carRepairShop, int ordersNumber) {
        this.carRepairShop = carRepairShop;
        this.ordersNumber = ordersNumber;
    }

    private void tryToSleep(long timeInMs) {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < ordersNumber; ++i) {
            tryToSleep(new Random().nextInt(500));
            carRepairShop.makeCar(3, "Hyundai", "Kona", "Nie działa");
            tryToSleep(new Random().nextInt(3500) + 500);
        }
    }
}