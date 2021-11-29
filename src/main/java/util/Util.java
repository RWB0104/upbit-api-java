package util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 유틸 클래스
 *
 * @author RWB
 * @since 2021.11.29 Mon 23:15:46
 */
public class Util
{
	/**
	 * 객체 연결 문자열 반환 메서드
	 *
	 * @param objs: [String] 가변 객체
	 *
	 * @return [String] 객체 연결 문자열
	 */
	public static String builder(Object... objs)
	{
		StringBuilder builder = new StringBuilder();
		
		for (Object obj : objs)
		{
			builder.append(obj.toString());
		}
		
		return builder.toString();
	}
	
	/**
	 * URL 쿼리 문자열 반환 메서드
	 *
	 * @param map: [HashMap] 파라미터 맵
	 *
	 * @return [String] URL 쿼리 문자열
	 */
	public static String queryBuilder(HashMap<String, String> map)
	{
		StringBuilder builder = new StringBuilder();
		
		for (Map.Entry<String, String> param : map.entrySet())
		{
			String pre = builder.length() == 0 ? "?" : "&";
			
			// URL 쿼리 생성 시도
			try
			{
				builder.append(pre).append(URLEncoder.encode(param.getKey(), "UTF-8")).append("=").append(URLEncoder.encode(param.getValue(), "UTF-8"));
			}
			
			// 예외
			catch (Exception e)
			{
				builder.append(pre).append(param.getKey()).append("=").append(param.getValue());
			}
		}
		
		return builder.toString();
	}
}