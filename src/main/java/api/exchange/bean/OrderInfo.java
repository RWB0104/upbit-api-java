package api.exchange.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 주문 가능 정보 객체 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 14:45:36
 */
@Getter
@Setter
@ToString
public class OrderInfo
{
	// 매수 수수료 비율
	private String bid_fee;
	
	// 매도 수수료 비율
	private String ask_fee;
	
	// 마켓 정보
	private Market market;
	
	// 매수 계좌 성보
	private Account bid_account;
	
	// 매도 계좌 정보
	private Account ask_account;
}