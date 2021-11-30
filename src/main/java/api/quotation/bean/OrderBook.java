package api.quotation.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 호가 객체 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 03:04:17
 */
@Getter
@Setter
@ToString
public class OrderBook
{
	// 마켓 코드
	private String market;
	
	// 호가 생성 시각
	private long timestamp;
	
	// 호가 매도 총 잔량
	private double total_ask_size;
	
	// 호가 매수 총 잔량
	private double total_bid_size;
	
	// 호가
	private OrderBookUnit[] orderbook_units;
}