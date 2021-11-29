package quotation.module;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import quotation.bean.Tick;
import util.ApiList;
import util.UpbitResponse;
import util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 시세 체결 API 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 02:21:16
 */
public class TradesApi
{
	/**
	 * 최근 체결 내역 응답 반환 메서드
	 *
	 * @param market: [String] 마켓 코드
	 *
	 * @return [UpbitResponse] Tick[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<Tick[]> getTicks(String market) throws IOException
	{
		return getTicks(market, "", 1, "", 0);
	}
	
	/**
	 * 최근 체결 내역 응답 반환 메서드
	 *
	 * @param market: [String] 마켓 코드
	 * @param to: [String] 마지막 체결 시간 (ex. 082006 or 08:20:06)
	 * @param count: [int] 체결 개수
	 * @param cursor: [String] 페이지네이션 커서 (입력한 sequential_id를 기준으로 출력)
	 * @param daysAgo: [int] 이전 날짜 (1 ~ 7일 이전의 데이터 조회 가능)
	 *
	 * @return [UpbitResponse] Tick[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<Tick[]> getTicks(String market, String to, int count, String cursor, int daysAgo) throws IOException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("market", market);
		params.put("to", to);
		params.put("count", String.valueOf(count));
		params.put("cursor", cursor);
		params.put("daysAgo", String.valueOf(daysAgo));
		
		URL url = new URL(Util.builder(ApiList.TRADES.getUrl(), "/ticks", Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.addRequestProperty("Accept", "application/json");
		
		UpbitResponse<Tick[]> response = new UpbitResponse<>();
		
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
			
			Tick[] ticks = new Gson().fromJson(responseBuilder.toString(), Tick[].class);
			
			response.setSuccess(true);
			response.setBody(ticks);
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
			response.setError(object.getAsJsonObject("error").get("name").getAsString());
			response.setErrorMessage(object.getAsJsonObject("error").get("message").getAsString());
		}
		
		connection.disconnect();
		
		return response;
	}
}