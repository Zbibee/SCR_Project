package CarRepairShop;

import CarRepairShop.Car.Car;
import CarRepairShop.Car.CarFactory;
import CarRepairShop.RepairStation.RepairStation;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarRepairShop {

    private static final int MAX_BUFFER_SIZE = 12;

    private final Queue<Car> carsQueue;
    private final ReentrantLock queueLock;
    private final Condition isQueueFull;
    private final Condition isQueueEmpty;
    private final ExecutorService processingStationsExecutor;
    private final List<RepairStation> repairStations;
    private final CarShopPrinter carShopPrinter;

    public CarRepairShop(int howManyProcessingStations) {
        this.carsQueue = new LinkedList<>();
        this.queueLock = new ReentrantLock(true);
        this.isQueueFull = queueLock.newCondition();
        this.isQueueEmpty = queueLock.newCondition();
        this.processingStationsExecutor = Executors.newFixedThreadPool(howManyProcessingStations);

        BufferInfo bufferInfo = new BufferInfo(carsQueue::size, MAX_BUFFER_SIZE);
        this.carShopPrinter = new CarShopPrinter(bufferInfo);

        this.repairStations = Stream
                .generate(() -> new RepairStation(this::remove, bufferInfo))
                .limit(howManyProcessingStations).collect(Collectors.toList());

        this.repairStations.forEach(processingStationsExecutor::submit);
    }

    public void closeCarRepairShop(){
        this.repairStations.forEach(RepairStation::close);
    }

    public ExecutorService getProcessingStationsExecutor() {
        return processingStationsExecutor;
    }

    public void makeCar(Integer age, String mark, String model, String faultDesc) {
        put(CarFactory.createNewCar(age, mark, model, faultDesc));
    }

    private void put(Car car) {
        carShopPrinter.printInfoBeforePuttingIntoBuffer();
        queueLock.lock();
        try {
            while (carsQueue.size() == MAX_BUFFER_SIZE) {
                try {
                    isQueueFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            carsQueue.add(car);
            isQueueEmpty.signalAll();
        } finally {
            queueLock.unlock();
        }
        carShopPrinter.printInfoAfterPuttingIntoBuffer(car);
    }

    private Car remove() {
        queueLock.lock();
        try {
            while (carsQueue.size() == 0) {
                try {
                    isQueueEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Car car = carsQueue.remove();
            isQueueFull.signalAll();
            return car;
        } finally {
            queueLock.unlock();
        }
    }
}
