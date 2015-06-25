package test.spring.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @classname: AbstractModel
 * @description: 所有类的基类 实现方法：toJson, toString
 */
public class AbstractModel implements Serializable {

	private static final long serialVersionUID = 6963281353838397908L;

	/**
	 * @title: toJson
	 * @description: 对象转换为json格式
	 * @return String
	 */
	public String toJson() {
		return JSON.toJSONString(this);
	}
	
	public JSONObject toJSONObject() {
		return JSON.parseObject(toJson());
	}

	/**
	 * @title: toString
	 * @description: 重写toString方法
	 * @return String
	 */
	public String toString() {
		return JSON.toJSONString(this);
	}
}