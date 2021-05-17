import CarRepairShop.CarRepairShop;
import CarRepairShop.CarSimulator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class main {

    public static void main(String[] args) {
        AppParameters appParameters;
        if (args.length == 3) {
            appParameters =
                    AppParameters.builder()
                            .setProcessingStationsNumber(Integer.parseInt(args[0]))
                            .setCarSimulatorsNumber(Integer.parseInt(args[1]))
                            .setCarsPerSimulator(Integer.parseInt(args[2]))
                            .setRestaurantType(new CarRepairShop(5))
                            .build();
        } else {
            appParameters = new AppParametersReader().readFromUser();
            System.out.println("Uruchamiam stanowiska naprawcze i czekam na klientów");
        }

        CarRepairShop carRepairShop = initCarRepairShop(appParameters);

        List<CarSimulator> carSimulators =
                Stream.generate(
                        () -> new CarSimulator(carRepairShop, appParameters.getCarPerSimulator()))
                        .limit(appParameters.getCarSimulatorsNumber())
                        .collect(Collectors.toList());
        ExecutorService simulatorsExecutor =
                Executors.newFixedThreadPool(appParameters.getCarSimulatorsNumber());
        carSimulators.forEach(simulatorsExecutor::submit);

        simulatorsExecutor.shutdown();

        try {
            simulatorsExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

            System.out.println("\nZakończył się czas przyjmowania samochodów.\n" +
                    "Symulatory przyjmowania samochodów zostały wyłączone.\n" +
                    "Kończę naprawy i zamykam stanowiska naprawcze.\n");

            carRepairShop.closeCarRepairShop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ExecutorService processingStationsExecutor = carRepairShop.getProcessingStationsExecutor();
        processingStationsExecutor.shutdown();
        try {
            processingStationsExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            System.out.println("\n" +
                    "Wszystkie samochody zostały naprawione.\n" +
                    "Warsztat został zamknięty.\n" +
                    "Dziękujemy za skorzystanie z usług Janusz CAR\n");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static CarRepairShop initCarRepairShop(AppParameters appParameters) {
        int processingStationsNumber = appParameters.getProcessingStationsNumber();
        return new CarRepairShop(processingStationsNumber);
    }
}

