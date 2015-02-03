package beauty.beautydemo.products;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by chenqiming on 2/3/15.
 */
final public class DAO
{
    private static boolean inited = false;

    private static List<Color> allColors = new ArrayList<Color>();

    private static List<Product> allProduct = new ArrayList<Product>();

    private static TreeMap<Color, Product> colorMap = new TreeMap<Color, Product>();

    static {
        try {
            init();
        } catch (Exception e) {
            Log.d("Beauty", "Inited once already");
        }
    }

    private static void addProduct(Product product)
    {
        allColors.add(product.getColor());
        allProduct.add(product);
        colorMap.put(product.getColor(), product);
    }

    public static void init() throws Exception
    {
        if (inited) throw new Exception("inited already");
        inited = false;

        addProduct(new Lipgloss(new Color(12,12,12), "chanel", "Chanel 1", "a kind of gloss", 1000));
    }
}
