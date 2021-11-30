package api.quotation.bean;

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
public class Ticker
{
	// 마켓 코드
	private String market;
	
	// 최근 거래 일자 (UTC)
	private String trade_date;
	
	// 최근 거래 시각 (UTC)
	private String trade_time;
	
	// 최근 거래 일자 (KST)
	private String trade_date_kst;
	
	// 최근 거래 시각 (KST)
	private String trade_time_kst;
	
	// 시가
	private double opening_price;
	
	// 고가
	private double high_price;
	
	// 저가
	private double low_price;
	
	// 종가
	private double trade_price;
	
	// 종가
	private double prev_closing_price;
	
	// 시세 변화 (EVEN or RISE or FALL)
	private String change;
	
	// 변화액의 절대값
	private double change_price;
	
	// 변화율의 절대값
	private double change_rate;
	
	// 부호가 있는 변화액
	private double signed_change_price;
	
	// 부호가 있는 변화율
	private double signed_change_rate;
	
	// 가장 최근 거래량
	private double trade_volume;
	
	// 누적 거래대금 (UTC 0시 기준)
	private double acc_trade_price;
	
	// 24시간 누적 거래대금
	private double acc_trade_price_24h;
	
	// 누적 거래량 (UTC 0시 기준)
	private double acc_trade_volume;
	
	// 24시간 누적 거래량
	private double acc_trade_volume_24h;
	
	// 52주 신고가
	private double highest_52_week_price;
	
	// 52주 신고가 달성일
	private String highest_52_week_date;
	
	// 52주 신저가
	private double lowest_52_week_price;
	
	// 52주 신저가 달성일
	private String lowest_52_week_date;
	
	// 타임스탬프
	private long timestamp;
}