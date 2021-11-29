package quotation.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 마켓 코드 객체 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 01:47:46
 */
@Getter
@Setter
@ToString
public class MarketCode
{
	// 마켓 코드
	private String market;
	
	// 암호화폐 한글명
	private String korean_name;
	
	// 암호화폐 영문명
	private String english_name;
	
	// 유의 종목 여부 (NONE or CAUTION)
	private String market_warning;
}