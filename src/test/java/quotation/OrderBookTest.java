package quotation;

import api.quotation.bean.OrderBook;
import api.quotation.module.OrderBookApi;
import org.junit.jupiter.api.Test;
import util.bean.UpbitResponse;

/**
 * 시세 호가 정보 API 테스트 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 02:38:54
 */
public class OrderBookTest
{
	/**
	 * 호가 정보 테스트
	 */
	@Test
	public void testOrderBooks()
	{
		try
		{
			UpbitResponse<OrderBook[]> response = new OrderBookApi().getOrderBooks(new String[] {
					"KRW-BTC",
					"BTC-ETH"
			});
			
			System.out.println(response);
			
			for (OrderBook item : response.getBody())
			{
				System.out.println(item);
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}