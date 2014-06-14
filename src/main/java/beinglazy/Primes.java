package beinglazy;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Primes {
    private static int primeAfter(final int number) {
        if (isPrime(number + 1)) return number + 1;
        else
            return primeAfter(number + 1);
    }

    public static List<Integer> primes(final int fromNumber, final int count) {
        return Stream.iterate(primeAfter(fromNumber - 1), Primes::primeAfter)
                .limit(count).collect(Collectors.<Integer>toList());
    }
//...

    public static boolean isPrime(final int number) {
        return number > 1 &&
                IntStream.rangeClosed(2, (int) Math.sqrt(number)).noneMatch(divisor -> number % divisor == 0);
    }

    public static void main(String[] args) {
        System.out.println(primes(2, 100));
        System.out.println("10 primes from 1: " + primes(1, 10));
        System.out.println("5 primes from 100: " + primes(100, 5));
    }
}