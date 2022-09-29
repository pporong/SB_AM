package com.gbr.exam.demo.util;

public class Ut {
	
	public static boolean empty(Object obj) {
		
		if (obj == null) {
			return true;
		}
		if (obj instanceof String == false) {
			return true;
		}
		
		String str = (String) obj;
		
		return str.trim().length() == 0;
	}

	public static Object f(String fromat, Object... args) {
		
		return String.format(fromat, args);
	}

}
