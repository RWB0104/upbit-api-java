package api.exchange.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 계좌 객체 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 12:23:48
 */
@Getter
@Setter
@ToString
public class Account
{
	// 화폐 코드
	private String currency;
	
	// 주문가능 금액/수량
	private String balance;
	
	// 주문 대기중인 금액/수량
	private String locked;
	
	// 매수평균가
	private String avg_buy_price;
	
	// 매수평균가 수정 여부
	private boolean avg_buy_price_modified;
	
	// 평단가 기준 화폐
	private String unit_currency;
}