package CarRepairShop.RepairStation;

import CarRepairShop.BufferInfo;
import CarRepairShop.Car.Car;
import CarRepairShop.Car.CarState;

import java.util.Random;
import java.util.function.Supplier;

public class RepairStation implements Runnable { private static int stationNumberCounter = 0;
    private final int stationNumber;
    private final Supplier<Car> carSupplier;
    private final RepairStationInfoPrinter repairStationInfoPrinter;
    private boolean isCloseCommandIssued = false;

    public RepairStation(Supplier<Car> carSupplier, BufferInfo bufferInfo) {
        this.stationNumber = ++stationNumberCounter;
        this.carSupplier = carSupplier;
        this.repairStationInfoPrinter = new RepairStationInfoPrinter(bufferInfo, stationNumber);
    }

    @Override
    public void run() {
        while (!isCloseCommandIssued) {
            repairStationInfoPrinter.infoBeforeProcessingStart();

            Car car = this.carSupplier.get();
            int processingTime = 35000;

            car.setCarState(CarState.PENDING);
            repairStationInfoPrinter.printCarStatus(car);
            tryToSleep(new Random().nextInt(processingTime) + 1000);

            car.setCarState(CarState.READY);
            repairStationInfoPrinter.printCarStatus(car);
            tryToSleep(new Random().nextInt(processingTime) + 1000);

            car.setCarState(CarState.DONE);
            repairStationInfoPrinter.printCarStatus(car);

            tryToSleep(processingTime / 3);
        }

        repairStationInfoPrinter.printCloseRepairStation(stationNumber);
    }

    public void close() {
        this.isCloseCommandIssued = true;
    }

    private void tryToSleep(long timeInMs) {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
