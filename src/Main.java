import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.*;

public class Main {
    public static void main(String[] args) {

        // Task 1

        List<City> listCity = new ArrayList<>();

        try (Scanner scanner = new Scanner(Paths.get("tmp/city.csv").toFile())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] column = line.split(";", -1);

                String name = column[1];
                String region = column[2];
                String district = column[3];
                int population = Integer.parseInt(column[4]);
                String foundation = column[5];

                City city = new City(name, region, district, population, foundation);
                listCity.add(city);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        listCity.forEach(System.out::println);


//         Task 2

        listCity.stream()
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .forEach(System.out::println);

        listCity.stream()
                .sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName))
                .forEach(System.out::println);

//         Task 3

        listCity.stream()
                .map(City::getPopulation)
                .max(Integer::compareTo)
                .ifPresent(System.out::println);

//          Task 4

        listCity.stream()
                .collect(Collectors.groupingBy(City::getRegion, Collectors.counting()))
                .forEach((key, value) -> System.out.println(key + " - " + value));
    }
}
