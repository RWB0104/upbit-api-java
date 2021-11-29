package quotation.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 기타 캔들 (주, 월) 객체 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 01:54:52
 */
@Getter
@Setter
@ToString
public class Candle
{
	// 마켓 코드
	private String market;
	
	// 캔들 기준 시각 (UTC)
	private String candle_date_time_utc;
	
	// 캔들 기준 시각 (KST)
	private String candle_date_time_kst;
	
	// 시가
	private double opening_price;
	
	// 고가
	private double high_price;
	
	// 저가
	private double low_price;
	
	// 종가
	private double trade_price;
	
	// 해당 캔들에서 마지막 틱이 저장된 시간
	private long timestamp;
	
	// 누적 거래 금액
	private double candle_acc_trade_price;
	
	// 누적 거래량
	private double candle_acc_trade_volume;
	
	// 캔들 기간의 가장 첫 날
	private String first_day_of_period;
}