import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

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
    }
}
