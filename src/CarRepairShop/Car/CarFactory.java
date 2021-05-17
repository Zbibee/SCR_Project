package CarRepairShop.Car;

public class CarFactory {
    private static int idCounter = 0;

    public static Car createNewCar(Integer age, String mark, String model, String faultDescription) {
        return new Car(age, mark, model, faultDescription, ++idCounter);
    }
}
