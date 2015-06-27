package beauty.beautydemo.custview.expandtabview;

import java.util.HashSet;

/**
 * @title:			SubViewButtonListener.java
 * @author:			Lou Jiwei 
 * @Email:			kkooff114@gmail.com
 * @version 		创建时间：2015-3-11 上午11:58:22
 * @description:	
 */
public interface SubViewButtonListener {
	
	/**
	 * 确定按钮
	 * @param set 所选关键字的key集合
	 */
	public void confirm(HashSet<String> set);
}
