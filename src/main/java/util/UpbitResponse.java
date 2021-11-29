package util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 업비트 응답 클래스
 *
 * @param <T> 사용자 정의 객체
 *
 * @author RWB
 * @since 2021.11.29 Mon 23:41:56
 */
@Getter
@Setter
@ToString
public class UpbitResponse<T>
{
	// 상태값
	private int status;
	
	// 연결 성공 여부
	private boolean isSuccess;
	
	// 응답
	private T body;
	
	// 에러 코드
	private String error;
	
	// 에러 설명
	private String errorMessage;
}