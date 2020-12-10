package com.eshop.common;

import java.util.Arrays;
import java.util.List;

public class Const {
	public static final List<String> allowSubfix;
	static {
		String[] subfix = {"gif","jpeg","jpg","png"};
		allowSubfix = Arrays.asList(subfix);
	}
}
