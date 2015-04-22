package test.spring.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

public final class BizUtil {

	/**
	 * @title: isExistNull
	 * @description: 判断是否存在null
	 * @param args
	 * @return
	 */
	public static boolean isExistNull(Object... args) {
		for (Object arg : args) {
			if (arg == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @title: isExistEmpty
	 * @description: 判断是否存在空，String类型包括""
	 * @param args
	 * @return
	 */
	public static boolean isExistEmpty(Object... args) {
		for (Object arg : args) {
			if (arg == null || (arg.getClass().equals(String.class) && StringUtils.isEmpty((String) arg))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @title: isExistBlank
	 * @description: 判断是否存在空，String类型包括""、" "
	 * @param args
	 * @return
	 */
	public static boolean isExistBlank(Object... args) {
		for (Object arg : args) {
			if (arg == null || (arg.getClass().equals(String.class) && StringUtils.isBlank((String) arg))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @title: isExistNonPositive
	 * @description: 判断是否存在非正数
	 * @param args
	 * @return boolean
	 */
	public static boolean isExistNonPositive(Number... args) {
		for (Number arg : args) {
			if (arg.doubleValue() <= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @title: isAllNull
	 * @description: 判断参数是否全部为null
	 * @param args
	 * @return
	 */
	public static boolean isAllNull(Object... args) {
		boolean result = true;
		for (Object arg : args) {
			result = result && (null == arg);
		}
		return result;
	}

	/**
	 * @title: isAllEmpty
	 * @description: 判断是否全部为空，String类型包括""
	 * @param args
	 * @return
	 */
	public static boolean isAllEmpty(Object... args) {
		boolean result = true;
		for (Object arg : args) {
			result = result
					&& (null == arg || (arg.getClass().equals(String.class) && StringUtils.isEmpty((String) arg)));
		}
		return result;
	}

	/**
	 * @title: isAllBlank
	 * @description: 判断是否全部为空，String类型包括""、" "
	 * @param args
	 * @return
	 */
	public static boolean isAllBlank(Object... args) {
		boolean result = true;
		for (Object arg : args) {
			result = result
					&& (null == arg || (arg.getClass().equals(String.class) && StringUtils.isBlank((String) arg)));
		}
		return result;
	}

	/**
	 * @title: mapToList
	 * @description: Map 转 List
	 * @param <K>
	 *            泛型 key
	 * @param <V>
	 *            泛型 value
	 * @param map
	 * @return
	 */
	public static <K, V> List<V> mapToList(Map<K, V> map) {
		List<V> list = new ArrayList<V>();
		Iterator<Entry<K, V>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<K, V> entry = iter.next();
			list.add(entry.getValue());
		}
		return list;
	}

}