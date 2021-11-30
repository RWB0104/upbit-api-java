package exchange;

import api.agent.UpbitAuthApi;
import api.exchange.bean.Account;
import org.junit.jupiter.api.Test;
import util.Key;
import util.bean.UpbitResponse;

/**
 * 자산 API 테스트 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 14:11:46
 */
public class AccountTest
{
	/**
	 * 자산 테스트
	 */
	@Test
	public void testAccounts()
	{
		try
		{
			UpbitResponse<Account[]> response = new UpbitAuthApi(Key.API, Key.SECRET).getAccounts();
			
			System.out.println(response);
			
			for (Account item : response.getBody())
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