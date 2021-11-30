package api.exchange.module;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import util.module.Util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

/**
 * 인증 API 클래스
 *
 * @author RWB
 * @since 2021.11.30 Tue 13:44:38
 */
public class AuthApi
{
	private final String api;
	
	private final String secret;
	
	/**
	 * 생성자 메서드
	 *
	 * @param api: [String] API 키
	 * @param secret: [String] API Secret 키
	 */
	protected AuthApi(String api, String secret)
	{
		this.api = api;
		this.secret = secret;
	}
	
	/**
	 * 인증 토큰 반환 메서드
	 *
	 * @return [String] 인증 토큰
	 */
	protected String getAuthToken()
	{
		return JWT.create()
				.withClaim("access_key", api)
				.withClaim("nonce", UUID.randomUUID().toString())
				.sign(Algorithm.HMAC256(secret));
	}
	
	/**
	 * 인증 토큰 반환 메서드
	 *
	 * @param params: [HashMap] 파라미터
	 *
	 * @return [String] 인증 토큰
	 *
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 * @throws UnsupportedEncodingException 지원하지 않는 인코딩 예외
	 */
	protected String getAuthToken(HashMap<String, String> params) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		String query = Util.queryBuilder(params);
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(query.getBytes("UTF-8"));
		
		String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));
		
		return JWT.create()
				.withClaim("access_key", api)
				.withClaim("nonce", UUID.randomUUID().toString())
				.withClaim("query_hash", queryHash)
				.withClaim("query_hash_alg", "SHA512")
				.sign(Algorithm.HMAC256(secret));
	}
	
	/**
	 * 인증 토큰 반환 메서드
	 *
	 * @param query: [String] 쿼리 문자열
	 *
	 * @return [String] 인증 토큰
	 *
	 * @throws NoSuchAlgorithmException 찾을 수 없는 알고리즘 예외
	 * @throws UnsupportedEncodingException 지원하지 않는 인코딩 예외
	 */
	protected String getAuthToken(String query) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(query.getBytes("UTF-8"));
		
		String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));
		
		return JWT.create()
				.withClaim("access_key", api)
				.withClaim("nonce", UUID.randomUUID().toString())
				.withClaim("query_hash", queryHash)
				.withClaim("query_hash_alg", "SHA512")
				.sign(Algorithm.HMAC256(secret));
	}
}