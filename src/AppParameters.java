import CarRepairShop.CarRepairShop;

public class AppParameters {
    private final int processingStationsNumber;
    private final int carSimulatorsNumber;
    private final int carsPerSimulator;
    private CarRepairShop carRepairShop;

    public AppParameters(Builder builder) {
        this.carSimulatorsNumber = builder.carSimulatorsNumber;
        this.carsPerSimulator = builder.carsPerSimulator;
        this.processingStationsNumber = builder.processingStationsNumber;
        this.carRepairShop = builder.carRepairShop;
    }

    public int getProcessingStationsNumber() {
        return processingStationsNumber;
    }

    public int getCarSimulatorsNumber() {
        return carSimulatorsNumber;
    }

    public int getCarPerSimulator() {
        return carsPerSimulator;
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
                int carSimulatorsNumber) {
            this.carSimulatorsNumber = carSimulatorsNumber;
            return this;
        }

        public Builder setCarsPerSimulator(int carsPerSimulator) {
            this.carsPerSimulator = carsPerSimulator;
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
