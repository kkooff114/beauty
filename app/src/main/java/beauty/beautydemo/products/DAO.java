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

	private static List<Product> allProducts = new ArrayList<Product>();

	private static List<Product> allFoundations = new ArrayList<Product>();
	private static List<Product> allBlushes = new ArrayList<Product>();
	private static List<Product> allLipglosses = new ArrayList<Product>();
	private static List<Product> allEyebrows = new ArrayList<Product>();
	private static List<Product> allEyeshadows = new ArrayList<Product>();

	private static TreeMap<Color, Product> colorFoundationsMap = new TreeMap<Color, Product>();
	private static TreeMap<Color, Product> colorBlushesMap = new TreeMap<Color, Product>();
	private static TreeMap<Color, Product> colorLipglossesMap = new TreeMap<Color, Product>();
	private static TreeMap<Color, Product> colorEyebrowsMap = new TreeMap<Color, Product>();
	private static TreeMap<Color, Product> colorEyeshadowsMap = new TreeMap<Color, Product>();

	static
	{
		try
		{
			init();
		} catch (Exception e)
		{
			Log.d("Beauty", "Inited once already");
		}
	}

	private static void addProduct(Product product)
	{
		allColors.add(product.getColor());
		allProducts.add(product);
		if (product instanceof Foundation)
		{
			allFoundations.add((Foundation) product);
			colorFoundationsMap.put(product.getColor(), product);
		}
		if (product instanceof Blush)
		{
			allBlushes.add((Blush) product);
			colorBlushesMap.put(product.getColor(), product);
		}
		if (product instanceof Lipgloss)
		{
			allLipglosses.add((Lipgloss) product);
			colorLipglossesMap.put(product.getColor(), product);
		}
		if (product instanceof Eyebrows)
		{
			allEyebrows.add((Eyebrows) product);
			colorEyebrowsMap.put(product.getColor(), product);
		}
		if (product instanceof Eyeshadow)
		{
			allEyeshadows.add((Eyeshadow) product);
			colorEyeshadowsMap.put(product.getColor(), product);
		}
	}

	private static void init() throws Exception
	{
		if (inited) throw new Exception("inited already");
		inited = false;

		addProduct(new Lipgloss(new Color(12, 12, 12), "chanel", "Chanel 1", "a kind of gloss", 1000));
	}

}