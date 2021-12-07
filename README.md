# Upbit API for JAVA

<p align="center">
  <img src="https://user-images.githubusercontent.com/50317129/145045058-9f97a7fa-6bb8-4284-a721-144c4921b544.png" width="256px" height="256px" />
</p>

Upbit API 자바 라이브러리

<br />
<br />
<br />










# 📙 Information

<br />
<br />





## **💬 Language**

* [![Java](http://img.shields.io/badge/java-v1.8+-007396?style=flat&logo=java&logoWidth=25)](https://www.java.com/ko/)

<br />
<br />





## **🧱 Framework**

* [![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoWidth=25)](https://gradle.org/)

<br />
<br />





## **📚 JAVA Dependency**

* ![lombok](https://img.shields.io/badge/lombok-v1.18.22-blue)
* ![gson](https://img.shields.io/badge/gson-v2.8.9-blue)
* ![java-jwt](https://img.shields.io/badge/java--jwt-v3.18.2-blue)
* ![junit-jupiter-api](https://img.shields.io/badge/junit--jupiter--api-v5.8.2-blue)
* ![junit-jupiter-engine](https://img.shields.io/badge/junit--jupiter--engine-v5.8.2-blue)

<br />
<br />
<br />










# ⚙ 사용 방법

* Gradle

``` gradle
// https://mvnrepository.com/artifact/dev.itcode/upbit-api-java
implementation group: 'dev.itcode', name: 'upbit-api-java', version: '1.0'
```

* Maven

``` xml
<!-- https://mvnrepository.com/artifact/dev.itcode/upbit-api-java -->
<dependency>
    <groupId>dev.itcode</groupId>
    <artifactId>upbit-api-java</artifactId>
    <version>1.0</version>
</dependency>
```

<br />
<br />
<br />

* 인증
  * ✅ 인증 토큰
  * ✅ 파라미터가 있는 인증 토큰
* QUOTATION API
  * 시세 종목 조회
    * ✅ 마켓 코드 조회
  * 시세 캔들 조회
    * ✅ 분(Minute) 캔들
    * ✅ 일(Day) 캔들
    * ✅ 주(Week) 캔들
    * ✅ 월(Month) 캔들
  * 시세 체결 조회
    * ✅ 최근 체결 내역
  * 시세 Ticker 조회
    * ✅ 현재가 정보
  * 시세 호가 정보(Orderbook) 조회
    * ✅ 호가 정보 조회
* EXCHANGE API
  * 자산
    * ✅ 전체 계좌 조회
  * 주문
    * ✅ 주문 가능 정보
    * ✅ 개별 주문 정보
    * ✅ 주문 리스트 조회
    * ✅ 주문 취소 접수
    * ✅ 주문하기
  * 출금
    * ❌ 출금 리스트 조회
    * ❌ 개별 출금 조회
    * ❌ 출금 가능 정보
    * ❌ 코인 출금하기
    * ❌ 원화 출금하기
  * 입금
    * ❌ 입금 리스트 조회
    * ❌ 개별 입금 조회
    * ❌ 입금 주소 생성 요청
    * ❌ 전체 입금 주소 조회
    * ❌ 개별 입금 주소 조회
    * ❌ 원화 입금하기
  * 서비스 정보
    * ❌ 입출금 현황
    * ❌ API 키 리스트 조회

<br />
<br />
<br />










# 📄 관련 문서

* [Upbit API for JAVA Wiki](https://github.com/RWB0104/upbit-api-java/wiki)
