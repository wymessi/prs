package com.wymessi.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author 王冶
 *
 */
public class ListUtils {

	/**
	 * 此方法比ArrayList.removeAll效率要高，时间复杂度低
	 * @param src 
	 * @param target 要在src中删去的集合
	 * @return
	 */
	public static <T> List<T> removeAll(List<T> src, List<T> target) {
		LinkedList<T> result = new LinkedList<>(src); // 大集合用linkedlist
		HashSet<T> targetHash = new HashSet<>(target); // 小集合用hashset
		Iterator<T> iter = result.iterator(); // 采用Iterator迭代器进行数据的操作

		while (iter.hasNext()) {
			if (targetHash.contains(iter.next())) {
				iter.remove();
			}
		}
		return result;
	}
}
