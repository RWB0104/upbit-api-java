import org.junit.jupiter.api.Test;
import quotation.bean.OrderBook;
import quotation.module.OrderBookApi;
import util.UpbitResponse;

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
	public void testTicker()
	{
		try
		{
			UpbitResponse<OrderBook[]> response = new OrderBookApi().getOrderBook(new String[] {
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