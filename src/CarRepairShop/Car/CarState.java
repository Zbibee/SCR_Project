package CarRepairShop.Car;

public enum  CarState {
    PLACED("Samochód przyjęty do naprawy."),
    WAITING("Oczekuje na swoją kolej"),
    PENDING("Samochód w trakcie naprawy"),
    READY("Samochód gotowy do odbioru"),
    DONE("Samochód oddany włascicielowi");

    private final String stringName;

    CarState(String stringName) {
        this.stringName = stringName;
    }

    @Override
    public String toString() {
        return stringName;
    }
}
