package api.exchange.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 매도/매수 제약사항 객체 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 14:51:08
 */
@Getter
@Setter
@ToString
public class MarketAskBid
{
	// 화폐 영문명
	private String currency;
	
	// 주문금액 단위
	private String price_unit;
	
	// 최소 매도/매수 금액
	private int min_total;
}