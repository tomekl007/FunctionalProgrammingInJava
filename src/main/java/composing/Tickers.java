package composing;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Tickers {
    public static final List<String> symbols = Arrays.asList(
            "AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE", "AMZN", "CRAY", "CSCO", "DELL", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");


    public static void main(String[] args) {

        final BigDecimal HUNDRED = new BigDecimal("100");
        System.out.println("Stocks priced over $100 are " +
                Tickers.symbols
                        .stream()
                        .filter(symbol -> YahooFinance.getPrice(symbol).compareTo(HUNDRED) > 0).sorted()
                        .collect(joining(", ")));
    }


}

