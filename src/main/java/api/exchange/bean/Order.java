package api.exchange.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 주문 리스트 객체 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 17:19:09
 */
@Getter
@Setter
@ToString
public class Order
{
	// 주문 고유 아이디
	private String uuid;
	
	// 주문 종류
	private String side;
	
	// 주문 방식
	private String ord_type;
	
	// 주문 당시 화폐 가격
	private String price;
	
	// 주문 상태
	private String state;
	
	// 마켓의 유일키
	private String market;
	
	// 주문 생성 시간
	private String created_at;
	
	// 사용자가 입력한 주문 양
	private String volume;
	
	// 체결 후 남은 주문 양
	private String remaining_volume;
	
	// 수수료로 예약된 비용
	private String reserved_fee;
	
	// 남은 수수료
	private String remaining_fee;
	
	// 사용된 수수료
	private String paid_fee;
	
	// 거래에 사용 중인 비용
	private String locked;
	
	// 체결된 양
	private String executed_volume;
	
	// 해당 주문에 걸린 체결 수
	private int trades_count;
}