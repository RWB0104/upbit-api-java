import org.junit.jupiter.api.Test;
import quotation.bean.Candle;
import quotation.bean.DayCandle;
import quotation.bean.MinuteCandle;
import quotation.module.CandlesApi;
import util.UpbitResponse;

/**
 * 시세 캔들 API 테스트 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 02:38:39
 */
public class CandleTest
{
	/**
	 * 분 캔들 테스트
	 */
	@Test
	public void testReqMinuteCandles()
	{
		try
		{
			UpbitResponse<MinuteCandle[]> response = new CandlesApi().getMinuteCandles(1, "KRW-BTC");
			
			System.out.println(response);
			
			for (MinuteCandle item : response.getBody())
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
	 * 분 캔들 테스트 (전체 파라미터)
	 */
	@Test
	public void testMinuteCandles()
	{
		try
		{
			UpbitResponse<MinuteCandle[]> response = new CandlesApi().getMinuteCandles(240, "KRW-BTC", "2020-05-05T08:20:11+09:00", 20);
			
			System.out.println(response);
			
			for (MinuteCandle item : response.getBody())
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
	 * 일 캔들 테스트
	 */
	@Test
	public void testReqDayCandles()
	{
		try
		{
			UpbitResponse<DayCandle[]> response = new CandlesApi().getDayCandles("KRW-BTC");
			
			System.out.println(response);
			
			for (DayCandle item : response.getBody())
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
	 * 일 캔들 테스트 (전체 파라미터)
	 */
	@Test
	public void testDayCandles()
	{
		try
		{
			UpbitResponse<DayCandle[]> response = new CandlesApi().getDayCandles("KRW-ETC", "2020-05-05T08:20:11+09:00", 20, "KRW");
			
			System.out.println(response);
			
			for (DayCandle item : response.getBody())
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
	 * 주 캔들 테스트
	 */
	@Test
	public void testReqWeekCandles()
	{
		try
		{
			UpbitResponse<Candle[]> response = new CandlesApi().getWeekCandles("KRW-BTC");
			
			System.out.println(response);
			
			for (Candle item : response.getBody())
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
	 * 주 캔들 테스트 (전체 파라미터)
	 */
	@Test
	public void testWeekCandles()
	{
		try
		{
			UpbitResponse<Candle[]> response = new CandlesApi().getWeekCandles("KRW-BTC", "2020-05-05T08:20:11+09:00", 20);
			
			System.out.println(response);
			
			for (Candle item : response.getBody())
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
	 * 월 캔들 테스트
	 */
	@Test
	public void testReqMonthCandles()
	{
		try
		{
			UpbitResponse<Candle[]> response = new CandlesApi().getMonthCandles("KRW-BTC");
			
			System.out.println(response);
			
			for (Candle item : response.getBody())
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
	 * 월 캔들 테스트 (전체 파라미터)
	 */
	@Test
	public void testMonthCandles()
	{
		try
		{
			UpbitResponse<Candle[]> response = new CandlesApi().getMonthCandles("KRW-BTC", "2020-05-05T08:20:11+09:00", 20);
			
			System.out.println(response);
			
			for (Candle item : response.getBody())
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