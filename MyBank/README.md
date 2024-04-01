# mybank

프로젝트 기간: 2024년 1월 24일 → 2024년 2월 8일
프로젝트: 개인 프로젝트
태그: BOOTSTRAP, CSS, GIT, GITHUB, GRADLE, HTML, JSP, JavaScript, MYSQL, SPRINGBOOT
프로젝트 주제: 은행 웹 어플리케이션 개발
개발 인원(역할): 1명 (백엔드, 프론트엔드 개발)
Github: https://github.com/Jincchus/bank

![Untitled](mybank%203918b043520b403bbf67e9ff6308335d/Untitled.png)

### 🎯 결과 및 성과

- OAUTH2.0 API 구현
- Validation 사용
- @Transactional 어노테이션에 대한 이해 및 중요성 체득
- 로직 구성에 대한 고찰
- HttpHeader와 HttpBody에 대한 이해
- SESSION에 대한 이해
- 인터셉터와 ExceptionHandler 사용

---

### 📖 내용

- [user] 회원가입 / 로그인
- [account] 계좌생성 / 계좌리스트 / 입금 / 출금
- [history] 각 계좌별 입·출금 내역

---

### 🛠 서비스 환경

| 유형 | 구분 | 서비스 배포 환경 |
| --- | --- | --- |
| SW | OS | Window10 |
|  | Browser | Chrome |
|  | Framework | Spring Boot 3.1.5 |
|  | Language | Java 17
Vue.js 3.0.0
HTML5
CSS3 |
|  | Library | tomcat-embed-jasper
jakarta.servlet.jsp.jstl 2.0.0
mybatis-spring-boot-starter:2.3.0
mysql-connector-j
spring-boot-starter-web
lombok
spring-boot-devtools |
|  | DBMS | MySQL 8.0 |
|  | Tool | IntelliJ IDEA Community Edition
HeidSQL
Git
Github
Jira
Figma
SLACK |

### 🙋‍♂️ 구현 기능

| 분류 | 기능 | 상세 |
| --- | --- | --- |
| Front | 화면 구현 | 회원가입, 로그인, 입출금, 입출금 내역, 상세 내역 |
| Back - USER | 회원가입 | - 회원가입 유효성 검사
- 프로필 사진 업로드 |
|  | 로그인, 로그아웃 | - 일반 로그인, 로그아웃
- Kakao OAUTH2 API 이용 로그인, 로그아웃
- Naver OAUTH2 API 이용 로그인, 로그아웃 |
| Back - ACCOUNT | 계좌 생성 및 목록 | - SESSION을 이용한 인증검사
- 계좌 생성
- 계좌 목록(다중 계좌 검증)
- 단일 계좌 검색(계좌 상세) |
|  | 입출금 | - 계좌 존재 여부 확인
- 본인 계좌 여부 확인
- 계좌 비밀번호 확인
- 잔액 여부 확인
- 출금 처리
- 거래 내역 등록 |
|  | 이체 | - 출금 계좌 존재 여부 확인
- 입금 계좌 존재 확인
- 출금 계좌 본인 소유 확인
- 출금 계좌 비밀번호 확인
- 출금 계좌 잔액 확인 후 수정
- 입금 계좌 잔액 수정
- 거래 내역 등록 처리 |
| Back - Interceptor 및 ExceptionHandler | Interceptor | - session 을 통한 인터셉터 구현 |
|  | ExceptionHandler | - RestfuleExceptionHandler
- 일반 예외 Handler |
