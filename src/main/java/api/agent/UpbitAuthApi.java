package api.agent;

import api.exchange.module.AccountsApi;
import api.exchange.module.OrdersApi;
import lombok.Getter;
import lombok.experimental.Delegate;

public class UpbitAuthApi extends UpbitApi
{
	@Getter
	private final String api;
	
	@Getter
	private final String secret;
	
	@Delegate
	private final AccountsApi accountsApi;
	
	@Delegate
	private final OrdersApi ordersApi;
	
	public UpbitAuthApi(String api, String secret)
	{
		this.api = api;
		this.secret = secret;
		
		accountsApi = new AccountsApi(this.api, this.secret);
		ordersApi = new OrdersApi(this.api, this.secret);
	}
}