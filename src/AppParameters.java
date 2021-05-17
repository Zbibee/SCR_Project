import CarRepairShop.CarRepairShop;

public class AppParameters {
    private final int processingStationsNumber;
    private final int orderSimulatorsNumber;
    private final int ordersPerSimulator;
    private CarRepairShop carRepairShop;

    public AppParameters(Builder builder) {
        this.orderSimulatorsNumber = builder.carSimulatorsNumber;
        this.ordersPerSimulator = builder.carsPerSimulator;
        this.processingStationsNumber = builder.processingStationsNumber;
        this.carRepairShop = builder.carRepairShop;
    }

    public int getProcessingStationsNumber() {
        return processingStationsNumber;
    }

    public int getCarSimulatorsNumber() {
        return orderSimulatorsNumber;
    }

    public int getCarPerSimulator() {
        return ordersPerSimulator;
    }

    public CarRepairShop getCarRepairShop() {
        return carRepairShop;
    }

    static Builder builder(){
        return new Builder();
    }

    public static final class Builder {
        private int processingStationsNumber;
        private int carSimulatorsNumber;
        private int carsPerSimulator;
        private CarRepairShop carRepairShop;

        private Builder() {}

        public static Builder anAppParameters() {
            return new Builder();
        }

        public Builder setProcessingStationsNumber(
                int processingStationsNumber) {
            this.processingStationsNumber = processingStationsNumber;
            return this;
        }

        public Builder setCarSimulatorsNumber(
                int orderSimulatorsNumber) {
            this.carSimulatorsNumber = orderSimulatorsNumber;
            return this;
        }

        public Builder setCarsPerSimulator(int ordersPerSimulator) {
            this.carsPerSimulator = ordersPerSimulator;
            return this;
        }

        public Builder setRestaurantType(CarRepairShop carRepairShop) {
            this.carRepairShop = carRepairShop;
            return this;
        }

        public AppParameters build() {
            return new AppParameters(this);
        }
    }
}
