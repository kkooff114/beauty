package beauty.beautydemo.bean;

import java.io.Serializable;

/**
 * @title: KeyValue.java
 * @author: Lou Jiwei
 * @Email: kkooff114@gmail.com
 * @version 创建时间：2014-6-5 下午4:23:45
 * @description: spinner使用
 */
public class KeyValue implements Serializable{

	private static final long serialVersionUID = 1L;
	private String key;
	private String value;
	
	public KeyValue(){
		
	}
	
	public KeyValue(String key, String value){
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

}
