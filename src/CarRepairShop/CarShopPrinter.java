package CarRepairShop;

import CarRepairShop.Car.Car;
import CarRepairShop.Utils.DateUtils;

import java.util.Date;

public class CarShopPrinter {

    private final static String BUFFER_FULL_INFO = "Parking jest pełny. Przyjęcie następnego samochodu do naprawy będzie "
            + "zrealizowane po zwolnieniu miejsca.";

    private final static String NEW_CAR_IN_PARKING =
            "Przyjęto kolejny samochód do naprawy, czeka na parkingu: ";

    private final BufferInfo bufferInfo;

    public CarShopPrinter(BufferInfo bufferInfo) {
        this.bufferInfo = bufferInfo;
    }

    public void printInfoBeforePuttingIntoBuffer() {
        if (bufferInfo.isFull()) {
            System.out.println(getThreadInfo() + BUFFER_FULL_INFO);
        }
    }

    public void printInfoAfterPuttingIntoBuffer(Car car) {
        String newCarInfo = String.format("%s%s%n", NEW_CAR_IN_PARKING, car);
        System.out.println(getThreadInfo() + newCarInfo + getBufferInfo() + "\n");
    }

    private String getBufferInfo() {
        return String.format("Stan parkingu %d/%d",
                bufferInfo.size(),
                bufferInfo.getTotalCapacity());
    }

    private String getThreadInfo() {
        return String.format("\n%s [Producent, wątek nr. %d]\t", DateUtils.getDate(new Date()), Thread.currentThread().getId());
    }
}
