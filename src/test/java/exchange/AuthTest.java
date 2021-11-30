package exchange;

import api.exchange.module.AuthApi;
import org.junit.jupiter.api.Test;
import util.Key;

import java.util.HashMap;

public class AuthTest extends AuthApi
{
	public AuthTest()
	{
		super(Key.API, Key.SECRET);
	}
	
	@Test
	public void testAuthToken()
	{
		try
		{
			System.out.println(getAuthToken());
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParamAuthToken()
	{
		try
		{
			HashMap<String, String> params = new HashMap<>();
			params.put("market", "KRW-BTC");
			params.put("state", "done");
			
			System.out.println(getAuthToken(params));
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}