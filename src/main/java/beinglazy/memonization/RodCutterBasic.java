package beinglazy.memonization;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RodCutterBasic {
    final List<Integer> prices;

    public RodCutterBasic(final List<Integer> pricesForLength) {
        prices = pricesForLength;
    }

    public int maxProfit(final int rodLenth) {
        return callMemoized(
                (final Function<Integer, Integer> func, final Integer length) -> {
                    System.out.println("func + " + length);
                    int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
                    for (int i = 1; i < length; i++) {
                        int priceWhenCut = func.apply(i) + func.apply(length - i);
                        if (profit < priceWhenCut) profit = priceWhenCut;
                    }
                    return profit;
                }, rodLenth);
    }


    public static <T, R> R callMemoized(final BiFunction<Function<T, R>, T, R> function, final T input){

        Function<T, R> memoized = new Function<T, R>() {
            private final Map<T, R> store = new HashMap<>();

            public R apply(final T input) {
                System.out.println("store " + input);
                return store.computeIfAbsent(input, key -> function.apply(this, key));
            }
        };
        System.out.println("memoized.apply " + input);
        return memoized.apply(input);
    }


    public static void main(String[] args) {
        final List<Integer> priceValues = Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);
        final RodCutterBasic rodCutter = new RodCutterBasic(priceValues);

        System.out.println(rodCutter.maxProfit(5));
        System.out.println(rodCutter.maxProfit(22));
    }
}
