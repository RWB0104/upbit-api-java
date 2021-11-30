package api.exchange.module;

import api.exchange.bean.Account;
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

/**
 * 자산 API 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 12:23:00
 */
public class AccountsApi extends AuthApi
{
	/**
	 * 생성자 메서드
	 *
	 * @param api: [String] API 키
	 * @param secret: [String] API Secret 키
	 */
	public AccountsApi(String api, String secret)
	{
		super(api, secret);
	}
	
	/**
	 * 전체 계좌 조회 응답 반환 메서드
	 *
	 * @return [UpbitResponse] Account[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 */
	public UpbitResponse<Account[]> getAccounts() throws IOException
	{
		URL url = new URL(Util.builder(ApiList.ACCOUNTS.getUrl()));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.addRequestProperty("Accept", "application/json");
		connection.addRequestProperty("Authorization", Util.builder("Bearer ", getAuthToken()));
		
		UpbitResponse<Account[]> response = new UpbitResponse<>();
		
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
			
			Account[] accounts = new Gson().fromJson(responseBuilder.toString(), Account[].class);
			
			response.setSuccess(true);
			response.setBody(accounts);
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