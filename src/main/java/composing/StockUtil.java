package composing;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class StockUtil {
    public static StockInfo getPrice(final String ticker) {
        return new StockInfo(ticker, YahooFinance.getPrice(ticker));
    }

    public static Predicate<StockInfo> isPriceLessThan(final int price) {
        return stockInfo -> stockInfo.price.compareTo(BigDecimal.valueOf(price)) < 0;
    }

    public static StockInfo pickHigh(
            final StockInfo stockInfo1, final StockInfo stockInfo2) {
        return stockInfo1.price.compareTo(stockInfo2.price) > 0 ? stockInfo1 : stockInfo2;
    }
//...
}