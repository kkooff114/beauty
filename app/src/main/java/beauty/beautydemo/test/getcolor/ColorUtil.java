package beauty.beautydemo.test.getcolor;

import android.graphics.Color;

public class ColorUtil {
	 /**
     * 比较颜色
     * @param colorOld
     * @param colorLast
     * @return
     */
	public static int compareSpan(int colorOld,int colorLast){
		int or = Color.red(colorOld);
		int og = Color.green(colorOld);
		int ob = Color.blue(colorOld);
		
		int lr = Color.red(colorLast);
		int lg = Color.green(colorLast);
		int lb = Color.blue(colorLast);
		return (Math.abs(or-lr)+Math.abs(og-lg)+Math.abs(ob-lb))/3;
	}
}
