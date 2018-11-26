package com.guomai.dushuhui.util.common;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResourceUTils {
	
	/**
	 * 从json文件中加载json字符串
	 * @param context
	 * @param filename
	 * @return
	 */
	public static String loadJsonFromAsset(Context context, String filename) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					context.getAssets().open(filename)));
			String line;
			while ((line = bf.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();

	}
}
