import org.junit.jupiter.api.Test;
import quotation.bean.MarketCode;
import quotation.module.MarketApi;
import util.UpbitResponse;

/**
 * 시세 종목 API 테스트 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 02:38:00
 */
public class MarketTest
{
	/**
	 * 마켓 코드 테스트
	 */
	@Test
	public void testMarketCode()
	{
		try
		{
			UpbitResponse<MarketCode[]> response = new MarketApi().getMarketCode(false);
			
			System.out.println(response);
			
			for (MarketCode item : response.getBody())
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