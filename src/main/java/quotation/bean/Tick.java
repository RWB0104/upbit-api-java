package quotation.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 틱 객체 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 02:22:14
 */
@Getter
@Setter
@ToString
public class Tick
{
	// 체결 일자 (UTC)
	private String trade_date_utc;
	
	// 체결 시각 (UTC)
	private String trade_time_utc;
	
	// 체결 타임스탬프
	private long timestamp;
	
	// 체결 가격
	private double trade_price;
	
	// 체결량
	private double trade_volume;
	
	// 전일 증가
	private double prev_closing_price;
	
	// 변화량
	private double change_price;
	
	// 매도/매수
	private String ask_bid;
	
	// 체결 번호 (고유값)
	private long sequential_id;
}