package quotation.module;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import quotation.bean.Ticker;
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
 * 시세 Ticker API 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 02:50:30
 */
public class TickerApi
{
	/**
	 * 현재가 정보 응답 반환 메서드
	 *
	 * @param markets: [String] 마켓 코드 배열
	 *
	 * @return [UpbitResponse] Ticker[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<Ticker[]> getTickers(String[] markets) throws IOException
	{
		String query = String.join(",", markets);
		
		HashMap<String, String> params = new HashMap<>();
		params.put("markets", query);
		
		URL url = new URL(Util.builder(ApiList.TICKER.getUrl(), Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.addRequestProperty("Accept", "application/json");
		
		UpbitResponse<Ticker[]> response = new UpbitResponse<>();
		
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
			
			Ticker[] tickers = new Gson().fromJson(responseBuilder.toString(), Ticker[].class);
			
			response.setSuccess(true);
			response.setBody(tickers);
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