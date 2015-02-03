package beauty.beautydemo.products;

/**
 * Created by chenqiming on 2/3/15.
 */
public class Color {

    private int R;
    private int G;
    private int B;

    public Color(int r, int g, int b) throws Exception {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255)
            throw new Exception("color value not valid");
        this.R = r;
        this.G = g;
        this.B = b;
    }

    static int getDifference(Color c1, Color c2)
    {
        return Math.abs(c1.getR()-c2.getR()) + Math.abs(c1.getG()-c2.getG()) + Math.abs(c1.getB()-c2.getB());
    }

    public int getR()
    {
        return R;
    }

    public int getG()
    {
        return G;
    }

    public int getB()
    {
        return B;
    }

    public int getRGB()
    {
        return android.graphics.Color.rgb(R,G,B);
    }
}
