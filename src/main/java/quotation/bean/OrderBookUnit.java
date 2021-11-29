package quotation.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 호가 유닛 객체 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 03:05:31
 */
@Getter
@Setter
@ToString
public class OrderBookUnit
{
	// 매도 호가
	private double ask_price;
	
	// 매수 호가
	private double bid_price;
	
	// 매도 잔량
	private double ask_size;
	
	// 매수 잔량
	private double bid_size;
}