package CarRepairShop.RepairStation;

import CarRepairShop.BufferInfo;
import CarRepairShop.Car.Car;
import CarRepairShop.Utils.DateUtils;

import java.util.Date;

public class RepairStationInfoPrinter {

    private static final String TAKE_CAR_INFO = "Samochód zostaje ściągniety z parkingu i przekazany do stacji naprawczej: ";
    private static final String BUFFER_EMPTY_INFO = "Parking jest pusty. Samochód zostanie zabrany do naprawy po przyjęciu samochodu na parkingu.";
    private static final String CLOSE_REPAIR_STATION = "Stanowisko obsługi zamówień nr.%d zostało ZAMKNIĘTE.";

    private final BufferInfo bufferInfo;
    private final int stationNumber;

    public RepairStationInfoPrinter(BufferInfo bufferInfo,
                                    int stationNumber) {
        this.bufferInfo = bufferInfo;
        this.stationNumber = stationNumber;
    }

    public void infoBeforeProcessingStart() {
        if (bufferInfo.isEmpty()) {
            System.out.println(BUFFER_EMPTY_INFO);
        }
    }

    void printCarStatus(Car car) {
            String threadInfo = String.format("%s [Konsument, wątek nr. %d]\t", DateUtils.getDate(new Date()), Thread.currentThread().getId());
            String takeOrderInfo = "";
            String bufferSizeInfo = "";

            if (car.isBeingPrepared()) {
                takeOrderInfo = TAKE_CAR_INFO;
                bufferSizeInfo = getBufferSizeInfo();
            }

            String stationNumberInfo = getStationNumberInfo();
            String orderInfo = String.format("Samochód o id: %d\tStatus: %s\t",car.getId(), car.getCarState());
            System.out.println(threadInfo + takeOrderInfo + stationNumberInfo + orderInfo + bufferSizeInfo);
    }

    public void printCloseRepairStation(Integer stationNumber){
        String threadInfo = String.format("%s [Konsument, wątek nr. %d]\t", DateUtils.getDate(new Date()), Thread.currentThread().getId());
        System.out.println(threadInfo + String.format(CLOSE_REPAIR_STATION, stationNumber));
    }

    private String getBufferSizeInfo() {
        return String.format("Stan parkingu %d/%d", bufferInfo.size(), bufferInfo.getTotalCapacity());
    }

    private String getStationNumberInfo() {
        return String.format("Stanowisko Obsługi %d: ", this.stationNumber);
    }


}
