package com.ktdsuniversity.edu.bizmatch.common.utils;

public final class ParameterCheck {
	public static boolean parameterCodeValid(String parameter, int size) {
		if(parameter != null && parameter.trim().length()>size) {
			return false;
		}
		return true;
	}
}
