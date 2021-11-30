package quotation;

import api.agent.UpbitApi;
import api.quotation.bean.Ticker;
import org.junit.jupiter.api.Test;
import util.bean.UpbitResponse;

/**
 * 시세 Ticker API 테스트 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 02:59:36
 */
public class TickerTest
{
	/**
	 * 현재가 정보 테스트
	 */
	@Test
	public void testTicks()
	{
		try
		{
			UpbitResponse<Ticker[]> response = new UpbitApi().getTickers(new String[] { "KRW-BTC", "BTC-ETH" });
			
			System.out.println(response);
			
			for (Ticker item : response.getBody())
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