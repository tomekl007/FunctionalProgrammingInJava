import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Tomasz Lelek
 * @since 2014-05-30
 */
public class DesiginingWithLambdaEx {

    public static void main(String[] args) {
        final List<Asset> assets = Arrays.asList(new Asset(Asset.AssetType.BOND, 1000),
                new Asset(Asset.AssetType.BOND, 2000),
                new Asset(Asset.AssetType.STOCK, 3000),
                new Asset(Asset.AssetType.STOCK, 4000)
        );

        System.out.println("Total of all assets: " + Asset.totalAssetValues(assets));

        System.out.println("Total of stocks: " + totalStockValues(assets));

        System.out.println("Total of bonds: " +
                totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.BOND));
    }


    public static int totalStockValues(final List<Asset> assets) {
        return assets.stream()
                .mapToInt(asset ->
                        asset.getType() == Asset.AssetType.STOCK ? asset.getValue() : 0)
                .sum();
    }

    public static int totalAssetValues(final List<Asset> assets,
                                       final Predicate<Asset> assetSelector) {
        return assets.stream().filter(assetSelector).mapToInt(Asset::getValue).sum();
    }



}
