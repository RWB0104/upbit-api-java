package api.agent;

import api.quotation.module.CandlesApi;
import api.quotation.module.MarketApi;
import api.quotation.module.OrderBookApi;
import api.quotation.module.TickerApi;
import api.quotation.module.TradesApi;
import lombok.experimental.Delegate;

/**
 * Upbit API 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 23:05:56
 */
public class UpbitApi
{
	@Delegate
	private final MarketApi marketApi = new MarketApi();
	
	@Delegate
	private final CandlesApi candlesApi = new CandlesApi();
	
	@Delegate
	private final TradesApi tradesApi = new TradesApi();
	
	@Delegate
	private final TickerApi tickerApi = new TickerApi();
	
	@Delegate
	private final OrderBookApi orderBookApi = new OrderBookApi();
}