package composing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Tomasz Lelek
 * @since 2014-06-03
 */
public class Imperative {
    public static void main(String[] args) {
        final List<StockInfo> stocks = new ArrayList<>();
        for (String symbol : Tickers.symbols) {
            stocks.add(StockUtil.getPrice(symbol));
        }
        final List<StockInfo> stocksPricedUnder500 = new ArrayList<>();
        final Predicate<StockInfo> isPriceLessThan500 = StockUtil.isPriceLessThan(500);
        for (StockInfo stock : stocks) {
            if (isPriceLessThan500.test(stock)) stocksPricedUnder500.add(stock);
        }
        StockInfo highPriced = new StockInfo("", BigDecimal.ZERO);
        for (StockInfo stock : stocksPricedUnder500) {
            highPriced = StockUtil.pickHigh(highPriced, stock);
        }
        System.out.println("High priced under $500 is " + highPriced);


        findHighPriced(Tickers.symbols.parallelStream());

    }


    public static void findHighPriced(final Stream<String> symbols) {
        final StockInfo highPriced =
                symbols
                        .map(StockUtil::getPrice)
                        .filter(StockUtil.isPriceLessThan(500))
                        .reduce(StockUtil::pickHigh)
                        .get();
        System.out.println("High priced under $500 is " + highPriced);
    }


}
