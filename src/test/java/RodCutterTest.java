import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomasz Lelek
 * @since 2014-06-02
 */
public class RodCutterTest {

    @Test
    public void VerboseExceptionTest() {
        /*rodCutter.setPrices(prices);
        try {
            rodCutter.maxProfit(0);
            fail("Expected exception for zero length");
        } catch (RodCutterException ex) {
            assertTrue("expected", true);
        } */
    }

    @Test
    public void ConciseExceptionTest() {
        List<String> prices = new ArrayList<>();
        RodCutter rodCutter = new RodCutter();
        rodCutter.setPrices(prices);
        TestHelper.assertThrows(RodCutterException.class, () -> rodCutter.maxProfit(0));
    }

}
