package api.quotation.module;

import api.quotation.bean.Candle;
import api.quotation.bean.DayCandle;
import api.quotation.bean.MinuteCandle;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import util.bean.UpbitResponse;
import util.constant.ApiList;
import util.module.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 시세 캔들 API 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 00:09:08
 */
public class CandlesApi
{
	/**
	 * 분 캔들 응답 반환 메서드
	 *
	 * @param unit: [int] 분 단위 (1, 3, 5, 10, 15, 30, 60, 240)
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 *
	 * @return [UpbitResponse] MinuteCandle[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<MinuteCandle[]> getMinuteCandles(int unit, String market) throws IOException
	{
		return getMinuteCandles(unit, market, "", 1);
	}
	
	/**
	 * 분 캔들 응답 반환 메서드 (전체 파라미터)
	 *
	 * @param unit: [int] 분 단위 (1, 3, 5, 10, 15, 30, 60, 240)
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 * @param to: [String] 마지막 캔들의 시간 (ex. 2021-01-01 09:08:15 or 2021-01-01T09:08:15+09:00)
	 * @param count: [int] 캔들 개수 (1 ~ 200)
	 *
	 * @return [UpbitResponse] MinuteCandle[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<MinuteCandle[]> getMinuteCandles(int unit, String market, String to, int count) throws IOException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("market", market);
		params.put("to", to);
		params.put("count", String.valueOf(count));
		
		URL url = new URL(Util.builder(ApiList.CANDLES.getUrl(), "/minutes/", unit, "?", Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.addRequestProperty("Accept", "application/json");
		
		UpbitResponse<MinuteCandle[]> response = new UpbitResponse<>();
		
		int code = connection.getResponseCode();
		
		response.setStatus(code);
		
		// 응답이 정상일 경우
		if (code == 200)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			
			StringBuilder responseBuilder = new StringBuilder();
			String temp;
			
			while ((temp = reader.readLine()) != null)
			{
				responseBuilder.append(temp);
			}
			
			reader.close();
			
			MinuteCandle[] candles = new Gson().fromJson(responseBuilder.toString(), MinuteCandle[].class);
			
			response.setSuccess(true);
			response.setBody(candles);
			response.setRaw(responseBuilder.toString());
		}
		
		// 아닐 경우
		else
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
			
			StringBuilder responseBuilder = new StringBuilder();
			String temp;
			
			while ((temp = reader.readLine()) != null)
			{
				responseBuilder.append(temp);
			}
			
			JsonObject object = new Gson().fromJson(responseBuilder.toString(), JsonObject.class);
			
			response.setSuccess(false);
			response.setRaw(responseBuilder.toString());
			response.setError(object.getAsJsonObject("error").get("name").getAsString());
			response.setErrorMessage(object.getAsJsonObject("error").get("message").getAsString());
		}
		
		connection.disconnect();
		
		return response;
	}
	
	/**
	 * 일 캔들 응답 반환 메서드
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 *
	 * @return [UpbitResponse] DayCandle[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<DayCandle[]> getDayCandles(String market) throws IOException
	{
		return getDayCandles(market, "", 1, "");
	}
	
	/**
	 * 일 캔들 응답 반환 메서드 (전체 파라미터)
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 * @param to: [String] 마지막 캔들의 시간 (ex. 2021-01-01 09:08:15 or 2021-01-01T09:08:15+09:00)
	 * @param count: [int] 캔들 개수 (1 ~ 200)
	 * @param convertingPriceUnit: [String] 종가 환산 화폐 단위 (ex. KRW)
	 *
	 * @return [UpbitResponse] DayCandle[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<DayCandle[]> getDayCandles(String market, String to, int count, String convertingPriceUnit) throws IOException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("market", market);
		params.put("to", to);
		params.put("count", String.valueOf(count));
		params.put("convertingPriceUnit", convertingPriceUnit);
		
		URL url = new URL(Util.builder(ApiList.CANDLES.getUrl(), "/days?", Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.addRequestProperty("Accept", "application/json");
		
		UpbitResponse<DayCandle[]> response = new UpbitResponse<>();
		
		int code = connection.getResponseCode();
		
		response.setStatus(code);
		
		// 응답이 정상일 경우
		if (code == 200)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			
			StringBuilder responseBuilder = new StringBuilder();
			String temp;
			
			while ((temp = reader.readLine()) != null)
			{
				responseBuilder.append(temp);
			}
			
			reader.close();
			
			DayCandle[] array = new Gson().fromJson(responseBuilder.toString(), DayCandle[].class);
			
			response.setSuccess(true);
			response.setBody(array);
			response.setRaw(responseBuilder.toString());
		}
		
		// 아닐 경우
		else
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
			
			StringBuilder responseBuilder = new StringBuilder();
			String temp;
			
			while ((temp = reader.readLine()) != null)
			{
				responseBuilder.append(temp);
			}
			
			JsonObject object = new Gson().fromJson(responseBuilder.toString(), JsonObject.class);
			
			response.setSuccess(false);
			response.setRaw(responseBuilder.toString());
			response.setError(object.getAsJsonObject("error").get("name").getAsString());
			response.setErrorMessage(object.getAsJsonObject("error").get("message").getAsString());
		}
		
		connection.disconnect();
		
		return response;
	}
	
	/**
	 * 주 캔들 응답 반환 메서드
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 *
	 * @return [UpbitResponse] Candle[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<Candle[]> getWeekCandles(String market) throws IOException
	{
		return getWeekCandles(market, "", 1);
	}
	
	/**
	 * 주 캔들 응답 반환 메서드 (전체 파라미터)
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 * @param to: [String] 마지막 캔들의 시간 (ex. 2021-01-01 09:08:15 or 2021-01-01T09:08:15+09:00)
	 * @param count: [int] 캔들 개수 (1 ~ 200)
	 *
	 * @return [UpbitResponse] Candle[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<Candle[]> getWeekCandles(String market, String to, int count) throws IOException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("market", market);
		params.put("to", to);
		params.put("count", String.valueOf(count));
		
		URL url = new URL(Util.builder(ApiList.CANDLES.getUrl(), "/weeks?", Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.addRequestProperty("Accept", "application/json");
		
		UpbitResponse<Candle[]> response = new UpbitResponse<>();
		
		int code = connection.getResponseCode();
		
		response.setStatus(code);
		
		// 응답이 정상일 경우
		if (code == 200)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			
			StringBuilder responseBuilder = new StringBuilder();
			String temp;
			
			while ((temp = reader.readLine()) != null)
			{
				responseBuilder.append(temp);
			}
			
			reader.close();
			
			Candle[] candles = new Gson().fromJson(responseBuilder.toString(), Candle[].class);
			
			response.setSuccess(true);
			response.setBody(candles);
			response.setRaw(responseBuilder.toString());
		}
		
		// 아닐 경우
		else
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
			
			StringBuilder responseBuilder = new StringBuilder();
			String temp;
			
			while ((temp = reader.readLine()) != null)
			{
				responseBuilder.append(temp);
			}
			
			JsonObject object = new Gson().fromJson(responseBuilder.toString(), JsonObject.class);
			
			response.setSuccess(false);
			response.setRaw(responseBuilder.toString());
			response.setError(object.getAsJsonObject("error").get("name").getAsString());
			response.setErrorMessage(object.getAsJsonObject("error").get("message").getAsString());
		}
		
		connection.disconnect();
		
		return response;
	}
	
	/**
	 * 월 캔들 응답 반환 메서드
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 *
	 * @return [UpbitResponse] Candle[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<Candle[]> getMonthCandles(String market) throws IOException
	{
		return getMonthCandles(market, "", 1);
	}
	
	/**
	 * 월 캔들 응답 반환 메서드 (전체 파라미터)
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 * @param to: [String] 마지막 캔들의 시간 (ex. 2021-01-01 09:08:15 or 2021-01-01T09:08:15+09:00)
	 * @param count: [int] 캔들 개수 (1 ~ 200)
	 *
	 * @return [UpbitResponse] Candle[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<Candle[]> getMonthCandles(String market, String to, int count) throws IOException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("market", market);
		params.put("to", to);
		params.put("count", String.valueOf(count));
		
		URL url = new URL(Util.builder(ApiList.CANDLES.getUrl(), "/months?", Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.addRequestProperty("Accept", "application/json");
		
		UpbitResponse<Candle[]> response = new UpbitResponse<>();
		
		int code = connection.getResponseCode();
		
		response.setStatus(code);
		
		// 응답이 정상일 경우
		if (code == 200)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			
			StringBuilder responseBuilder = new StringBuilder();
			String temp;
			
			while ((temp = reader.readLine()) != null)
			{
				responseBuilder.append(temp);
			}
			
			reader.close();
			
			Candle[] candles = new Gson().fromJson(responseBuilder.toString(), Candle[].class);
			
			response.setSuccess(true);
			response.setBody(candles);
			response.setRaw(responseBuilder.toString());
		}
		
		// 아닐 경우
		else
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
			
			StringBuilder responseBuilder = new StringBuilder();
			String temp;
			
			while ((temp = reader.readLine()) != null)
			{
				responseBuilder.append(temp);
			}
			
			JsonObject object = new Gson().fromJson(responseBuilder.toString(), JsonObject.class);
			
			response.setSuccess(false);
			response.setRaw(responseBuilder.toString());
			response.setError(object.getAsJsonObject("error").get("name").getAsString());
			response.setErrorMessage(object.getAsJsonObject("error").get("message").getAsString());
		}
		
		connection.disconnect();
		
		return response;
	}
}