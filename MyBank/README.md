### My_Bank<br>

은행 웹 어플리케이션

------

### ⏱ 프로젝트 기간<br>

24.01.24~24.02.08

------

### 👫 프로젝트 간단 소개<br>

**[1인개발]jincchus**

1. 화면 구현
2. USER - 회원가입, 로그인, 로그아웃
3. HISTORY - 계좌 생성 및 목록
4. ACCOUNT - 입출금 기능, 이체 기능
5. Interceptor 및 ExceptionHandler

------

### 🌟프로젝트 페이지 소개<br>

![MyBank_Jsp](https://github.com/Jincchus/Project/assets/136421962/7ae8a11a-4929-41f4-b362-50bf7e1bcc57)

------

### 🎯프로젝트 결과<br>

- OAUTH2.0 API 구현
- Validation 사용
- @Transactional 어노테이션에 대한 이해 및 중요성 체득
- 로직 구성에 대한 고찰
- HttpHeader와 HttpBody에 대한 이해
- SESSION에 대한 이해
- 인터셉터와 ExceptionHandler 사용


------

### 🌝프로젝트 주요 기능<br>
| 분류                                               | 기능              | 상세                                                         |
| -------------------------------------------------- | ----------------- | ------------------------------------------------------------ |
| Front                                              | 화면 구현         | 회원가입, 로그인, 입출금, 입출금 내역, 상세 내역             |
| Back - USER                                        | 회원가입          | \- 회원가입 유효성 검사 <br />- 프로필 사진 업로드           |
|                                                    | 로그인, 로그아웃  | \- 일반 로그인, 로그아웃 <br />- Kakao OAUTH2 API 이용 로그인, 로그아웃 <br />- Naver OAUTH2 API 이용 로그인, 로그아웃 |
| Back - HISTORY                                     | 계좌 생성 및 목록 | \- SESSION을 이용한 인증검사 <br />- 계좌 생성 <br />- 계좌 목록(다중 계좌 검증) <br />- 단일 계좌 검색(계좌 상세) |
| Back - ACCOUNT                                     | 입출금            | \- 계좌 존재 여부 확인 <br />- 본인 계좌 여부 확인 <br />- 계좌 비밀번호 확인 <br />- 잔액 여부 확인 <br />- 입금 처리<br />- 출금 처리 <br />- 거래 내역 등록 |
|                                                    | 이체              | \- 출금 계좌 존재 여부 확인 <br />- 입금 계좌 존재 확인 <br />- 출금 계좌 본인 소유 확인 <br />- 출금 계좌 비밀번호 확인 <br />- 출금 계좌 잔액 확인 후 수정 <br />- 입금 계좌 잔액 수정 <br />- 거래 내역 등록 처리 |
| Back - <br />Interceptor 및 <br />ExceptionHandler | Interceptor       | \- session 을 통한 인터셉터 구현                             |
|                                                    | ExceptionHandler  | \- RestfuleExceptionHandler <br />- 일반 예외 Handler        |

------

### 🖥개발 환경<br>

| 유형 | 구분      | 서비스 배포 환경                                             |
| ---- | --------- | ------------------------------------------------------------ |
| SW   | OS        | - Window10                                                   |
|      | Browser   | - Chrome                                                     |
|      | Framework | - Spring Boot 3.1.5                                          |
|      | Language  | - Java17 <br />- HTML5 <br />- CSS3                          |
|      | Library   | - tomcat-embed-jasper <br />- jakarta.servlet.jsp.jstl 2.0.0 <br />- mybatis-spring-boot-starter:2.3.0 <br />- mysql-connector-j <br />- spring-boot-starter-web <br />- lombok <br />- spring-boot-devtools |
|      | DBMS      | - MySQL 8.0                                                  |
|      | Tool      | - STS4<br />- Git<br />- Github                              |

