package api.exchange.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 체결 객체 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 17:21:24
 */
@Getter
@Setter
@ToString
public class Trade
{
	// 마켓 고유키
	private String market;
	
	// 체결 고유 아이디
	private String uuid;
	
	// 체결 가격
	private String price;
	
	// 체결 양
	private String volume;
	
	// 체결된 총 가격
	private String funds;
	
	// 체결 종류
	private String side;
	
	// 체결 시각
	private String created_at;
}