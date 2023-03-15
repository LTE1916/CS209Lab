import java.io.IOException;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Practice4 {
    public static class City
    {
        private String name;
        private String state;
        private int population;

        public City(String name, String state, int population)
        {
            this.name = name;
            this.state = state;
            this.population = population;
        }

        public String getName()
        {
            return name;
        }

        public String getState()
        {
            return state;
        }

        public int getPopulation()
        {
            return population;
        }

    }

    public static Stream<City> readCities(String filename) throws IOException
    {
        return Files.lines(Paths.get(filename))
                .map(l -> l.split(", "))
                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        Stream<City> cities = readCities("cities.txt");
        // TODO: Map<String, Long> cityCountPerState = ...
         Map<String, Long> cityCountPerState = cities.collect(Collectors.groupingBy(City::getState,Collectors.counting()));
        System.out.println(cityCountPerState);
        
        
        cities = readCities("cities.txt");
        // Q2: count the total population for each state
        // TODO: Map<String, Integer> statePopulation = ...
        Map<String, Integer> statePopulation =cities.collect(Collectors.groupingBy(City::getState,Collectors.summingInt(City::getPopulation)));
                System.out.println(statePopulation);

                
        cities = readCities("cities.txt");
        // Q3: for each state, get the set of cities with >500,000 population
        // TODO: Map<String, Set<City>> largeCitiesByState = ...
        Map<String, Set<City>> largeCitiesByState =cities.collect(Collectors.groupingBy(City::getState,Collectors.toSet()));
      
        largeCitiesByState.forEach((s, cities1) -> {
            System.out.print(s+":[");
            for(City city:cities1){
                if(city.population>500000) System.out.print("City{name='"+city.name+"','state="+city.state+"',population="+city.population+"}");
            }
            System.out.print("]");
            System.out.println();
        });
    }
}
