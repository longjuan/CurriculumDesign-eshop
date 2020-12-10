package com.eshop.common;

public class MyUtils {
	public static boolean haveNull(Object... objects) {
		for (Object object : objects) {
			if(object==null) {
				return true;
			}
		}
		return false;
	}
}
