import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * @author Tomasz Lelek
 * @since 2014-05-30
 */
public class StriingsComparatorFilter {

    public static void main(String[] args) throws IOException, InterruptedException {

        final List<Person> people = Arrays.asList(new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35));


        final Function<Person, String> byTheirName = Person::getName;
        System.out.println(Arrays.toString(people.stream().sorted(comparing(byTheirName)).toArray()));

        List<Person> olderThan20 = new ArrayList<>();
        people.stream()
                .filter(person -> person.getAge() > 20)
                .forEach(olderThan20::add);
        System.out.println("People older than 20: " + olderThan20);


        List<Person> olderThan20b = people.stream()
                .filter(person -> person.getAge() > 20)
                .collect(toList());
        System.out.println("People older than 20: " + olderThan20b);

        Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect(groupingBy(Person::getAge));
        System.out.println("People grouped by age: " + peopleByAge);

        Map<Integer, List<String>> nameOfPeopleByAge = people.stream()
                .collect(groupingBy(Person::getAge, mapping(Person::getName, toList())));
        System.out.println("People grouped by age: " + nameOfPeopleByAge);


        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonInEachAlphabet =
                people.stream()
                        .collect(groupingBy(person -> person.getName().charAt(0),
                                reducing(BinaryOperator.maxBy(byAge))));
        System.out.println("Oldest person in each alphabet:");
        System.out.println(oldestPersonInEachAlphabet);


        Files.list(Paths.get(".")).filter(Files::isDirectory).forEach(System.out::println);

        File[] result = new File(".").listFiles(File::isHidden);

        listAllSubfiles();

        final Path path = Paths.get(".");
        final WatchService watchService = path.getFileSystem().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
        System.out.println("Report any file changed within next 1 minutes...");

        final WatchKey watchKey = watchService.poll(1, TimeUnit.MINUTES);
        if (watchKey != null) {
            watchKey.pollEvents().stream().forEach(event ->
                    System.out.println(event.context()));
        }







    }


    public static void listAllSubfiles() {
        List<File> files =
                Stream.of(new File(".").listFiles()).flatMap(file -> file.listFiles() == null ?
                        Stream.of(file) : Stream.of(file.listFiles()))
                        .collect(toList());
        System.out.println("Count: " + files.size());
    }
}
