import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Tomasz Lelek
 * @since 2014-05-29
 */
public class Lambdas {

    public static void main(String[] args) {
        final List<String> friends =
                Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

       long countFriendsStartN = friends.stream().filter(checkIfStartsWith("N")).count();
        System.out.println(countFriendsStartN);

        pickName(friends, "N");
        pickName(friends, "X");

        System.out.println("Total number of characters in all names: " + friends.stream()
                .mapToInt(String::length)
                .sum());

        final Optional<String> aLongName = friends.stream()
                .reduce((name1, name2) ->
                        name1.length() >= name2.length() ? name1 : name2);
        aLongName.ifPresent(name ->
                System.out.println(String.format("A longest name: %s", name)));


        String joined = friends.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }



    final Function<String, Predicate<String>> startsWithLetter = (String letter) -> (String name) -> name.startsWith(letter);


    public static void pickName(
            final List<String> names, final String startingLetter) {
        final Optional<String> foundName = names.stream()
                .filter(name ->name.startsWith(startingLetter))
                .findFirst();
        System.out.println(String.format("A name starting with %s: %s", startingLetter, foundName.orElse("No name found")));
    }
}
