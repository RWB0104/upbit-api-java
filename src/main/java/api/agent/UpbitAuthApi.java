package api.agent;

import api.exchange.module.AccountsApi;
import api.exchange.module.OrdersApi;
import lombok.Getter;
import lombok.experimental.Delegate;

/**
 * Upbit 인증용 API 클래스
 *
 * @author RWB
 * @since 2021.11.05 Sun 00:23:59
 */
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
	
	/**
	 * 생성자 메서드
	 *
	 * @param api: [String] API 키
	 * @param secret: [String] Secret 키
	 */
	public UpbitAuthApi(String api, String secret)
	{
		this.api = api;
		this.secret = secret;
		
		accountsApi = new AccountsApi(this.api, this.secret);
		ordersApi = new OrdersApi(this.api, this.secret);
	}
}