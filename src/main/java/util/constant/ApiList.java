package util.constant;

import lombok.Getter;

/**
 * API 열거 객체
 *
 * @author RWB
 * @since 2021.11.29 Mon 23:09:06
 */
public enum ApiList
{
	ACCOUNTS("https://api.upbit.com/v1/accounts"),
	ORDER("https://api.upbit.com/v1/order"),
	ORDERS("https://api.upbit.com/v1/orders"),
	WITHDRAWS("https://api.upbit.com/v1/withdraws"),
	DEPOSITS("https://api.upbit.com/v1/deposits"),
	MARKET("https://api.upbit.com/v1/market"),
	CANDLES("https://api.upbit.com/v1/candles"),
	TRADES("https://api.upbit.com/v1/trades"),
	TICKER("https://api.upbit.com/v1/ticker"),
	ORDERBOOK("https://api.upbit.com/v1/orderbook");
	
	@Getter
	private final String url;
	
	/**
	 * 생성자 메서드
	 *
	 * @param url: [String] API URL
	 */
	ApiList(String url)
	{
		this.url = url;
	}
}