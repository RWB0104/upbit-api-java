package exchange;

import api.agent.UpbitAuthApi;
import api.exchange.bean.Order;
import api.exchange.bean.OrderDetail;
import api.exchange.bean.OrderInfo;
import api.exchange.module.OrdersApi;
import org.junit.jupiter.api.Test;
import util.Key;
import util.bean.UpbitResponse;

import java.util.UUID;

/**
 * 주문 API 테스트 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 15:16:56
 */
public class OrdersTest
{
	/**
	 * 주문 가능 정보 테스트
	 */
	@Test
	public void testOrderInfo()
	{
		try
		{
			UpbitResponse<OrderInfo> response = new UpbitAuthApi(Key.API, Key.SECRET).getOrderInfo("KRW-BTC");
			
			System.out.println(response);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 개별 주문 조회 테스트
	 */
	@Test
	public void testOrder()
	{
		try
		{
			UpbitResponse<OrderDetail> response = new UpbitAuthApi(Key.API, Key.SECRET).getOrder("d328a446-9232-4831-a45e-75014e513508");
			
			System.out.println(response);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 주문 리스트 조회 테스트 (필수 파라미터)
	 */
	@Test
	public void testReqOrderLists()
	{
		try
		{
			UpbitResponse<Order[]> response = new OrdersApi(Key.API, Key.SECRET).getOrderLists();
			
			System.out.println(response);
			
			for (Order item : response.getBody())
			{
				System.out.println(item);
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 주문 리스트 조회 테스트 (일부 파라미터)
	 */
	@Test
	public void testSubOrderLists()
	{
		try
		{
			UpbitResponse<Order[]> response = new OrdersApi(Key.API, Key.SECRET).getOrderLists("KRW-BTC", "done");
			
			System.out.println(response);
			
			for (Order item : response.getBody())
			{
				System.out.println(item);
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 주문 리스트 조회 테스트
	 */
	@Test
	public void testOrderLists()
	{
		try
		{
			String[] temp = new String[] {};
			
			UpbitResponse<Order[]> response = new OrdersApi(Key.API, Key.SECRET).getOrderLists("KRW-BTC", temp, temp, "done", temp, 1, 100, "desc");
			
			System.out.println(response);
			
			for (Order item : response.getBody())
			{
				System.out.println(item);
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 삭제 테스트 (필수 파라미터)
	 */
	@Test
	public void testReqDeleteOrder()
	{
		try
		{
			UpbitResponse<Order> response = new OrdersApi(Key.API, Key.SECRET).deleteOrder("d4788ecf-79fe-4bb5-85ad-a8e37048ac5d");
			
			System.out.println(response);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 삭제 테스트
	 */
	@Test
	public void testDeleteOrder()
	{
		try
		{
			UpbitResponse<Order> response = new OrdersApi(Key.API, Key.SECRET).deleteOrder("", "");
			
			System.out.println(response);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 매수 주문 테스트 (필수 파라미터)
	 */
	@Test
	public void testReqBuyOrder()
	{
		try
		{
			UpbitResponse<Order> response = new OrdersApi(Key.API, Key.SECRET).postBuyOrder("KRW-BTC", 10000);
			
			System.out.println(response);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 매수 주문 테스트
	 */
	@Test
	public void testBuyOrder()
	{
		try
		{
			UpbitResponse<Order> response = new OrdersApi(Key.API, Key.SECRET).postBuyOrder("KRW-BTC", 10000, UUID.randomUUID().toString());
			
			System.out.println(response);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 매수 지정가 주문 테스트
	 */
	@Test
	public void testLimitBuyOrder()
	{
		try
		{
			UpbitResponse<Order> response = new OrdersApi(Key.API, Key.SECRET).postLimitOrder("KRW-BTC", "bid", 1, 1000000, UUID.randomUUID().toString());
			
			System.out.println(response);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 매도 주문 테스트 (필수 파라미터)
	 */
	@Test
	public void testReqSellOrder()
	{
		try
		{
			UpbitResponse<Order> response = new OrdersApi(Key.API, Key.SECRET).postSellOrder("KRW-BTC", 10000);
			
			System.out.println(response);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 매도 주문 테스트
	 */
	@Test
	public void testSellOrder()
	{
		try
		{
			UpbitResponse<Order> response = new OrdersApi(Key.API, Key.SECRET).postSellOrder("KRW-BTC", 10000, UUID.randomUUID().toString());
			
			System.out.println(response);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 매도 지정가 주문 테스트
	 */
	@Test
	public void testLimitSellOrder()
	{
		try
		{
			UpbitResponse<Order> response = new OrdersApi(Key.API, Key.SECRET).postLimitOrder("KRW-QTUM", "ask", 1, 100000, UUID.randomUUID().toString());
			
			System.out.println(response);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}