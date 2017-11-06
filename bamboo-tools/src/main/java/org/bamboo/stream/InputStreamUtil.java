package org.bamboo.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamUtil {

	/**
	 * 利用BufferedReader实现InputStream转换成String
	 * 
	 * @param in
	 * @param encode
	 * @return
	 */
	public static String inToStr_Reader(InputStream in, String encode) {
		if (encode == null || encode.equals("")) {
			encode = "utf-8";
		}
		String str = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, encode));
			StringBuffer sb = new StringBuffer();
			while ((str = reader.readLine()) != null) {
				sb.append(str).append("\n");
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

	/**
	 * 利用byte数组实现InputStream转换成String
	 * 
	 * @param in
	 * @param encode
	 * @return
	 */
	public static String inToStr_ByteArray(InputStream in, String encode) {
		if (encode == null || encode.equals("")) {
			encode = "utf-8";
		}
		byte[] b = new byte[1024];
		int len = 0;
		StringBuffer sb = new StringBuffer();

		try {
			while ((len = in.read(b)) != -1) {
				sb.append(new String(b, 0, len, encode));
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}
}
