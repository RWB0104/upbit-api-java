package quotation;

import api.agent.UpbitApi;
import api.quotation.bean.Candle;
import api.quotation.bean.DayCandle;
import api.quotation.bean.MinuteCandle;
import org.junit.jupiter.api.Test;
import util.bean.UpbitResponse;

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
			UpbitResponse<MinuteCandle[]> response = new UpbitApi().getMinuteCandles(1, "KRW-BTC");
			
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
			UpbitResponse<MinuteCandle[]> response = new UpbitApi().getMinuteCandles(240, "KRW-BTC", "2020-05-05T08:20:11+09:00", 20);
			
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
			UpbitResponse<DayCandle[]> response = new UpbitApi().getDayCandles("KRW-BTC");
			
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
			UpbitResponse<DayCandle[]> response = new UpbitApi().getDayCandles("KRW-ETC", "2020-05-05T08:20:11+09:00", 20, "KRW");
			
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
			UpbitResponse<Candle[]> response = new UpbitApi().getWeekCandles("KRW-BTC");
			
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
			UpbitResponse<Candle[]> response = new UpbitApi().getWeekCandles("KRW-BTC", "2020-05-05T08:20:11+09:00", 20);
			
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
			UpbitResponse<Candle[]> response = new UpbitApi().getMonthCandles("KRW-BTC");
			
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
			UpbitResponse<Candle[]> response = new UpbitApi().getMonthCandles("KRW-BTC", "2020-05-05T08:20:11+09:00", 20);
			
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