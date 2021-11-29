import org.junit.jupiter.api.Test;
import quotation.bean.Tick;
import quotation.module.TradesApi;
import util.UpbitResponse;

/**
 * 시세 체결 API 테스트 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 02:38:54
 */
public class TradeTest
{
	/**
	 * 최근 체결 내역 테스트 (전체 파라미터)
	 */
	@Test
	public void testReqTicks()
	{
		try
		{
			UpbitResponse<Tick[]> response = new TradesApi().getTicks("KRW-BTC");
			
			System.out.println(response);
			
			for (Tick item : response.getBody())
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
	 * 최근 체결 내역 테스트 (전체 파라미터)
	 */
	@Test
	public void testTicks()
	{
		try
		{
			UpbitResponse<Tick[]> response = new TradesApi().getTicks("KRW-BTC", "09:50:37", 10, "", 2);
			
			System.out.println(response);
			
			for (Tick item : response.getBody())
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