package com.sichuan.geologenvi.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class URLUtil {
	private static final String EMPTY_STRING = "";
	private static final String UTF_8 = "UTF-8";
	private static final String PAIR_SEPARATOR = "=";
	private static final String PARAM_SEPARATOR = "&";
	private static final char QUERY_STRING_SEPARATOR = '?';

	private static final String ERROR_MSG = String.format("Cannot find specified encoding: %s",
			UTF_8);

	private static final Set<EncodingRule> ENCODING_RULES;

	static {
		Set<EncodingRule> rules = new HashSet<EncodingRule>();
		rules.add(new EncodingRule("*", "%2A"));
		rules.add(new EncodingRule("+", "%20"));
		rules.add(new EncodingRule("%7E", "~"));
		ENCODING_RULES = Collections.unmodifiableSet(rules);
	}

	/**
	 * Turns a map into a form-urlencoded string
	 * 
	 * @param map any map
	 * @return form-url-encoded string
	 */
	public static String formURLEncodeMap(Map<String, String> map) {
		return (map.size() <= 0) ? EMPTY_STRING : doFormUrlEncode(map);
	}

	private static String doFormUrlEncode(Map<String, String> map) {
		StringBuffer encodedString = new StringBuffer(map.size() * 20);
		for (String key : map.keySet()) {
			encodedString.append(PARAM_SEPARATOR).append(formURLEncode(key));
			if (map.get(key) != null) {
				encodedString.append(PAIR_SEPARATOR).append(formURLEncode(map.get(key)));
			}
		}
		return encodedString.toString().substring(1);
	}

	/**
	 * 将請求参数集转化为浏览器对应的参数字符串{@code username=zhangsan&password=666666&age=25}
	 * 
	 * @param params 請求参数集
	 * @return 浏览器对应的参数字符串
	 */
	public static String map2string(final Map<String, String> params) throws UnsupportedEncodingException {
		final StringBuilder buf = new StringBuilder();

		for (Iterator<Map.Entry<String, String>> it = params.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, String> me = it.next();
			buf.append(me.getKey()).append('=').append(URLEncoder.encode(me.getValue(), "utf-8")).append('&');
		}

		if (buf.length() > 0) {
			buf.deleteCharAt(buf.length() - 1);
		}
//		LogUtil.i(HttpAsyncTask.TAG, buf.toString());
		return buf.toString();
	}

	/**
	 * Percent encodes a string
	 * 
	 * @param string plain string
	 * @return percent encoded string
	 */
	public static String percentEncode(String string) {
		String encoded = formURLEncode(string);
		for (EncodingRule rule : ENCODING_RULES) {
			encoded = rule.apply(encoded);
		}
		return encoded;
	}

	/**
	 * Translates a string into application/x-www-form-urlencoded format
	 * 
	 * @param plain
	 * @return form-urlencoded string
	 */
	public static String formURLEncode(String string) {
		try {
			return URLEncoder.encode(string, UTF_8);
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalStateException(ERROR_MSG, uee);
		}
	}

	/**
	 * Decodes a application/x-www-form-urlencoded string
	 * 
	 * @param string form-urlencoded string
	 * @return plain string
	 */
	public static String formURLDecode(String string) {
		try {
			return URLDecoder.decode(string, UTF_8);
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalStateException(ERROR_MSG, uee);
		}
	}

	/**
	 * Append given parameters to the query string of the url
	 * 
	 * @param url the url to append parameters to
	 * @param params any map
	 * @return new url with parameters on query string
	 */
	public static String appendParametersToQueryString(String url, Map<String, String> params) {
		String queryString = URLUtil.formURLEncodeMap(params);
		if (queryString.equals(EMPTY_STRING)) {
			return url;
		} else {
			url += url.indexOf(QUERY_STRING_SEPARATOR) != -1 ? PARAM_SEPARATOR
					: QUERY_STRING_SEPARATOR;
			url += queryString;
			return url;
		}
	}

	/**
	 * Concats a key-value map into a querystring-like String
	 * 
	 * @param params key-value map
	 * @return querystring-like String
	 */
	public static String concatSortedPercentEncodedParams(Map<String, String> params) {
		StringBuilder result = new StringBuilder();
		for (String key : params.keySet()) {
			result.append(key).append(PAIR_SEPARATOR);
			result.append(params.get(key)).append(PARAM_SEPARATOR);
		}
		return result.toString().substring(0, result.length() - 1);
	}

	/**
	 * Parses and form-urldecodes a querystring-like string into a map
	 * 
	 * @param queryString querystring-like String
	 * @return a map with the form-urldecoded parameters
	 */
	public static Map<String, String> queryStringToMap(String queryString) {
		Map<String, String> result = new HashMap<String, String>();
		if (queryString != null && queryString.length() > 0) {
			for (String param : queryString.split(PARAM_SEPARATOR)) {
				String pair[] = param.split(PAIR_SEPARATOR);
				String key = formURLDecode(pair[0]);
				String value = pair.length > 1 ? formURLDecode(pair[1]) : EMPTY_STRING;
				result.put(key, value);
			}
		}
		return result;
	}

	private static final class EncodingRule {
		private final String ch;
		private final String toCh;

		EncodingRule(String ch, String toCh) {
			this.ch = ch;
			this.toCh = toCh;
		}

		String apply(String string) {
			return string.replace(ch, toCh);
		}
	}

	public static SoftReference<Bitmap> getImage(String imageUrl) {
		try {
			URL u = new URL(imageUrl);
			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			con.connect();
			InputStream is = con.getInputStream();
			SoftReference<Bitmap> bm = new SoftReference<Bitmap>(BitmapFactory.decodeStream(is));
			// con.getResponseCode();
			is.close();

			return bm;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
