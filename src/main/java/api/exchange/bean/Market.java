package api.exchange.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 마켓 정보 객체
 *
 * @author RWB
 * @since 2021.11.30 Tue 14:47:14
 */
@Getter
@Setter
@ToString
public class Market
{
	// 마켓 키
	private String id;
	
	// 마켓 이름
	private String name;
	
	// 지원 주문 방식
	private String[] order_types;
	
	// 지원 주문 종류
	private String[] order_sides;
	
	// 매수 제약사항
	private MarketAskBid bid;
	
	// 매도 제약사항
	private MarketAskBid ask;
	
	// 최대 매도/매수 금액
	private String max_total;
	
	// 마켓 운영 상태
	private String state;
}