package CarRepairShop.Car;

public class Car {
    private int id;
    private Integer age;
    private String mark;
    private String model;
    private String faultDescription;
    private CarState carState;

    public Car(Integer age, String mark, String model, String faultDescription, int id) {
        this.id = id;
        this.age = age;
        this.mark = mark;
        this.model = model;
        this.faultDescription = faultDescription;
        this.carState = CarState.WAITING;
    }

    public void setCarState(CarState carState) {
        this.carState = carState;
    }

    public boolean isBeingPrepared() {
        return CarState.PENDING == this.carState;
    }

    @Override
    public String toString() {
        return String.format("\n" +
                "=== Samochód ===\n" +
                "ID: %s\n" +
                "Marka: %s\n" +
                "Model: %s\n" +
                "Rocznik: %d\n" +
                "Opis awarii: %s\n" +
                "Status: %s\n===========================", id, mark, model, age, faultDescription, carState.toString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFaultDescription() {
        return faultDescription;
    }

    public void setFaultDescription(String faultDescription) {
        this.faultDescription = faultDescription;
    }

    public CarState getCarState() {
        return carState;
    }
}
