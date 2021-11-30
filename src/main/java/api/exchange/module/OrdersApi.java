package api.exchange.module;

import api.exchange.bean.Order;
import api.exchange.bean.OrderDetail;
import api.exchange.bean.OrderInfo;
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
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

/**
 * 주문 API 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 14:39:44
 */
public class OrdersApi extends AuthApi
{
	/**
	 * 생성자 메서드
	 *
	 * @param api: [String] API 키
	 * @param secret: [String] API Secret 키
	 */
	public OrdersApi(String api, String secret)
	{
		super(api, secret);
	}
	
	/**
	 * 주문 가능 정보 조회 응답 반환 메서드
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 *
	 * @return [UpbitResponse] OrderInfo[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<OrderInfo> getOrderInfo(String market) throws IOException, NoSuchAlgorithmException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("market", market);
		
		URL url = new URL(Util.builder(ApiList.ORDERS.getUrl(), "/chance?", Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.addRequestProperty("Accept", "application/json");
		connection.addRequestProperty("Authorization", Util.builder("Bearer ", getAuthToken(params)));
		
		UpbitResponse<OrderInfo> response = new UpbitResponse<>();
		
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
			
			OrderInfo orderInfo = new Gson().fromJson(responseBuilder.toString(), OrderInfo.class);
			
			response.setSuccess(true);
			response.setBody(orderInfo);
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
	 * 개별 주문 조회 응답 반환 메서드 (필수 파라미터)
	 *
	 * @param uuid: [String] 주문 UUID
	 *
	 * @return [UpbitResponse] OrderDetail를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<OrderDetail> getOrder(String uuid) throws IOException, NoSuchAlgorithmException
	{
		return getOrder(uuid, "");
	}
	
	/**
	 * 개별 주문 정보 조회 응답 반환 메서드
	 *
	 * @param uuid: [String] 주문 UUID
	 * @param identifier: [String] 조회용 사용자 지정 값
	 *
	 * @return [UpbitResponse] OrderDetail를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<OrderDetail> getOrder(String uuid, String identifier) throws IOException, NoSuchAlgorithmException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("uuid", uuid);
		params.put("identifier", identifier);
		
		URL url = new URL(Util.builder(ApiList.ORDER.getUrl(), "?", Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.addRequestProperty("Accept", "application/json");
		connection.addRequestProperty("Authorization", Util.builder("Bearer ", getAuthToken(params)));
		
		UpbitResponse<OrderDetail> response = new UpbitResponse<>();
		
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
			
			OrderDetail orderDetail = new Gson().fromJson(responseBuilder.toString(), OrderDetail.class);
			
			response.setSuccess(true);
			response.setBody(orderDetail);
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
	 * 주문 리스트 조회 응답 반환 메서드 (필수 파라미터)
	 *
	 * @return [UpbitResponse] Order[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<Order[]> getOrderLists() throws IOException, NoSuchAlgorithmException
	{
		String[] temp = new String[] {};
		
		return getOrderLists("", temp, temp, "wait", temp, 1, 100, "desc");
	}
	
	/**
	 * 주문 리스트 조회 응답 반환 메서드 (일부 파라미터)
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 * @param state: [String] 주문 상태 (wait, watch, done, cancel)
	 *
	 * @return [UpbitResponse] Order[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<Order[]> getOrderLists(String market, String state) throws IOException, NoSuchAlgorithmException
	{
		String[] temp = new String[] {};
		
		return getOrderLists(market, temp, temp, state, temp, 1, 100, "desc");
	}
	
	/**
	 * 주문 리스트 조회 응답 반환 메서드
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 * @param uuids: [String[]] 주문 UUID 목록
	 * @param identifiers: [String[]] 주문 사용자 지정 값 목록
	 * @param state: [String] 주문 상태 (wait, watch, done, cancel)
	 * @param states: [String[]] 주문 상태 목록
	 * @param page: [int] 페이지 수
	 * @param limit: [int] 요청 갯수
	 * @param order_by: [String] 정렬 방식 (asc, desc)
	 *
	 * @return [UpbitResponse] Order[]를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<Order[]> getOrderLists(String market, String[] uuids, String[] identifiers, String state, String[] states, int page, int limit, String order_by) throws IOException, NoSuchAlgorithmException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("market", market);
		params.put("state", state);
		params.put("page", String.valueOf(page));
		params.put("limit", String.valueOf(limit));
		params.put("order_by", order_by);
		
		String statesQuery = states.length > 0 ? Util.builder("&states=", String.join("&states=", states)) : "";
		String uuidsQuery = uuids.length > 0 ? Util.builder("&uuids=", String.join("&uuids=", uuids)) : "";
		String identifiersQuery = identifiers.length > 0 ? Util.builder("&identifiers=", String.join("&identifiers=", identifiers)) : "";
		
		String query = Util.builder(Util.queryBuilder(params), statesQuery, uuidsQuery, identifiersQuery);
		
		URL url = new URL(Util.builder(ApiList.ORDERS.getUrl(), "?", query));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.addRequestProperty("Accept", "application/json");
		connection.addRequestProperty("Authorization", Util.builder("Bearer ", getAuthToken(query)));
		
		UpbitResponse<Order[]> response = new UpbitResponse<>();
		
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
			
			Order[] order = new Gson().fromJson(responseBuilder.toString(), Order[].class);
			
			response.setSuccess(true);
			response.setBody(order);
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
	 * 주문 취소 접수 응답 반환 메서드 (필수 파라미터)
	 *
	 * @param uuid: [String] 주문 UUID
	 *
	 * @return [UpbitResponse] Order를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<Order> deleteOrder(String uuid) throws IOException, NoSuchAlgorithmException
	{
		return deleteOrder(uuid, "");
	}
	
	/**
	 * 주문 취소 접수 응답 반환 메서드
	 *
	 * @param uuid: [String] 주문 UUID
	 * @param identifier: [String] 조회용 사용자 지정 값
	 *
	 * @return [UpbitResponse] Order를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<Order> deleteOrder(String uuid, String identifier) throws IOException, NoSuchAlgorithmException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("uuid", uuid);
		params.put("identifier", identifier);
		
		URL url = new URL(Util.builder(ApiList.ORDER.getUrl(), "?", Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("DELETE");
		connection.addRequestProperty("Accept", "application/json");
		connection.addRequestProperty("Authorization", Util.builder("Bearer ", getAuthToken(params)));
		
		UpbitResponse<Order> response = new UpbitResponse<>();
		
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
			
			Order order = new Gson().fromJson(responseBuilder.toString(), Order.class);
			
			response.setSuccess(true);
			response.setBody(order);
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
	 * 매수 주문 응답 반환 메서드 (필수 파라미터)
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 * @param volume: [int] 주문량
	 *
	 * @return [UpbitResponse] Order를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<Order> postSellOrder(String market, int volume) throws IOException, NoSuchAlgorithmException
	{
		return postSellOrder(market, volume, UUID.randomUUID().toString());
	}
	
	/**
	 * 매수 주문 응답 반환 메서드
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 * @param volume: [int] 주문량
	 * @param identifier: [String] 조회용 사용자 지정값
	 *
	 * @return [UpbitResponse] Order를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<Order> postSellOrder(String market, int volume, String identifier) throws IOException, NoSuchAlgorithmException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("market", market);
		params.put("side", "ask");
		params.put("volume", String.valueOf(volume));
		params.put("ord_type", "market");
		params.put("identifier", identifier);
		
		URL url = new URL(Util.builder(ApiList.ORDERS.getUrl(), "?", Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.addRequestProperty("Accept", "application/json");
		connection.addRequestProperty("Authorization", Util.builder("Bearer ", getAuthToken(params)));
		
		UpbitResponse<Order> response = new UpbitResponse<>();
		
		int code = connection.getResponseCode();
		
		response.setStatus(code);
		
		// 응답이 정상일 경우
		if (code == 201)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			
			StringBuilder responseBuilder = new StringBuilder();
			String temp;
			
			while ((temp = reader.readLine()) != null)
			{
				responseBuilder.append(temp);
			}
			
			reader.close();
			
			Order order = new Gson().fromJson(responseBuilder.toString(), Order.class);
			
			response.setSuccess(true);
			response.setBody(order);
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
	 * 매도 주문 응답 반환 메서드 (필수 파라미터)
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 * @param price: [int] 주문 가격
	 *
	 * @return [UpbitResponse] Order를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<Order> postBuyOrder(String market, int price) throws IOException, NoSuchAlgorithmException
	{
		return postBuyOrder(market, price, UUID.randomUUID().toString());
	}
	
	/**
	 * 매도 주문 응답 반환 메서드
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 * @param price: [int] 주문 가격
	 * @param identifier: [String] 조회용 사용자 지정값
	 *
	 * @return [UpbitResponse] Order를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<Order> postBuyOrder(String market, int price, String identifier) throws IOException, NoSuchAlgorithmException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("market", market);
		params.put("side", "bid");
		params.put("price", String.valueOf(price));
		params.put("ord_type", "price");
		params.put("identifier", identifier);
		
		URL url = new URL(Util.builder(ApiList.ORDERS.getUrl(), "?", Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.addRequestProperty("Accept", "application/json");
		connection.addRequestProperty("Authorization", Util.builder("Bearer ", getAuthToken(params)));
		
		UpbitResponse<Order> response = new UpbitResponse<>();
		
		int code = connection.getResponseCode();
		
		response.setStatus(code);
		
		// 응답이 정상일 경우
		if (code == 201)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			
			StringBuilder responseBuilder = new StringBuilder();
			String temp;
			
			while ((temp = reader.readLine()) != null)
			{
				responseBuilder.append(temp);
			}
			
			reader.close();
			
			Order order = new Gson().fromJson(responseBuilder.toString(), Order.class);
			
			response.setSuccess(true);
			response.setBody(order);
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
	 * 지정가 매도/매수 주문 응답 반환 메서드
	 *
	 * @param market: [String] 마켓 코드 (ex. KRW-BTC)
	 * @param side: [String] 주문 종류 (bid or ask)
	 * @param volume: [int] 주문량
	 * @param price: [int] 주문 가격
	 * @param identifier: [String] 조회용 사용자 지정값
	 *
	 * @return [UpbitResponse] Order를 포함한 Upbit 응답 객체
	 *
	 * @throws IOException 데이터 입출력 예외
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 */
	public UpbitResponse<Order> postLimitOrder(String market, String side, int volume, int price, String identifier) throws IOException, NoSuchAlgorithmException
	{
		HashMap<String, String> params = new HashMap<>();
		params.put("market", market);
		params.put("side", side);
		params.put("volume", String.valueOf(volume));
		params.put("price", String.valueOf(price));
		params.put("ord_type", "limit");
		params.put("identifier", identifier);
		
		URL url = new URL(Util.builder(ApiList.ORDERS.getUrl(), "?", Util.queryBuilder(params)));
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.addRequestProperty("Accept", "application/json");
		connection.addRequestProperty("Authorization", Util.builder("Bearer ", getAuthToken(params)));
		
		UpbitResponse<Order> response = new UpbitResponse<>();
		
		int code = connection.getResponseCode();
		
		response.setStatus(code);
		
		// 응답이 정상일 경우
		if (code == 201)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			
			StringBuilder responseBuilder = new StringBuilder();
			String temp;
			
			while ((temp = reader.readLine()) != null)
			{
				responseBuilder.append(temp);
			}
			
			reader.close();
			
			Order order = new Gson().fromJson(responseBuilder.toString(), Order.class);
			
			response.setSuccess(true);
			response.setBody(order);
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