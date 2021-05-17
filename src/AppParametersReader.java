import java.util.Scanner;

public class AppParametersReader {

    private final Scanner scanner;

    public AppParametersReader() {
        this.scanner = new Scanner(System.in);
    }

    public AppParameters readFromUser() {

        System.out.printf("Witaj w Janusz CAR\nZanim zaczniesz dzień w warsztacie uzupełnij dane.\n");

        return AppParameters.builder()
                .setCarSimulatorsNumber(readCarSimulatorNumbers())
                .setCarsPerSimulator(readCarsPerSimulator())
                .setProcessingStationsNumber(readProcessingStationsNumber())
                .build();
    }

    private int readCarSimulatorNumbers() {
        return readInt("Wybierz ilośc symulatorów przyjmujących samochody do naprawy");
    }

    private int readCarsPerSimulator() {
        return readInt("Podaj liczbę samochodów jaką ma przyjąć do naprawy każdy symulator");
    }

    private int readProcessingStationsNumber() {
        return readInt("Podaj ilość stanowisk naprawczych do naprawy samochodów");
    }

    private int readInt(String messageToDisplay) {
        System.out.printf("%s: ", messageToDisplay);
        return scanner.nextInt();
    }
}