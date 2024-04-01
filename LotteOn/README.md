[lotteOn_readMe_code]
<div align=center>
  
 ![image](https://github.com/bllor/Project2023/assets/136154061/7f59fe08-879a-4182-a260-fac95b4114a3)

</div>
<h1>🛒프로젝트 소개</h1><br>
롯데e-커머스 LOTTE ON 쇼핑몰 개발
<br>
<h1>⏱ 프로젝트 기간</h1><br>
2023.10.16 ~ 2023.10.30 (11일 88시간)
<br>
<h1>👫 멤버소개</h1>

|이름|직책|역할|
|:----|:----|:----|
|김철학|PM|프로젝트 기획 및 계획수립, 프로젝트 일정관리|
|이동한|팀장|로그인 ,관리자 물품 등록, 물품 보기 기능 구현|
|최동일|팀원|관리자 고객센터, 상품 구매하기, 구매 완료|
|박경진|팀원|상품 목록, 보기, 장바구니|
|박성용|팀원|고객센터 |
<br>
<h1>🌟프로젝트 메인 페이지 소개</h1><br>

![LotteOn 메인페이지](https://github.com/bllor/Project2023/assets/136154061/99dc1a6e-72b0-43fe-b49d-dc5c7b6dd123)


<h1>🌝본인 주요 개발기능</h1>

**1.상품**<br>
**1)상품보기**: 상품 보기 페이지에서 물건 구매 시 장바구니에 담을 수 있고, 바로 구매할 수 있습니다.<br>
**2)구매하기**: 포인트 사용, 배송 정보 변경, 선택 항목을 삭제하여 최종주문에서 제외합니다. .<br>
**3)주문완료**: 주문금액, 결제바업, 주문자의 정보와 수취인에 대한 정보가 나옵니다.<br>
<br>
**2.관리자 고객센터**<br>
**1)글보기(QnA/FAQ/Notice)**: 게시글이 대분류, 소분류, 작성일, 제목, 내용, 작성자를 각 카테고리에 맞게 출력 및 페이징 처리<br>
**2)글쓰기(QnA)**:글 작성 시 대분류 카테고리에 따라 소분류 카테고리가 변경됩니다.<br>
**3)글수정(QnA)**:글쓰기와 마찬가지로 대분류 카테고리에 따라 소분류 카테고리가 변경됩니다.<br>
**4)답글달기(QnA)**: 고객센터에서 작성된 문의글에 답글을 달 수 있으며, 답글을 달 경우 답변완료로 상태가 변경이 된다.<br>
<br>
<h1>🖥개발 환경</h1>

**OS** : Window10<br>
**Browser** : Chorme 117.0.5938<br>
**Language(Server)** : Java17, Spring Boot, Spring Security, Spring Data JPA 
**Language(Client)** : HTML5, CSS3, JavaScript(Es6), jQuery3.1
**Library** : <br>
activation-1.1.1.jar<br> 
cos-05Nov2022.jar<br> 
gson-2.10.1.jar <br>
javax.mail-1.6.2.jar<br> 
javax.mail-api-1.6.2.jar jstl-1.2.jar <br>
logback-classic-1.4.11.jar <br>
logback-core-1.4.11.jar <br>
mysql-connector-java-8.0.32.jar <br>
slf4j-api-2.0.7.jar <br>
jackson-core-2.15.3.jar <br>
modelmapper-3.1.1.jar <br>
lombok <br>
etc
**DMBS** : MySQL 8.0
**Tool** : IntelliJ IDEA 2023-02-04, Mysql Workbench 8.0.21, HeidSQL 12.5, Git 2.41.0, Github
<br>
<h1>프로젝트를 하면서 느낀 점</h1>
<br>

**1.카테고리 같이 자주 변경되지 않는 값은 데이터베이스에서 가져오는 것이 편하다는 것을 알게되었습니다.**<br>

이전 프로젝트에서 카테고리를 사용할 때 자바스크립트를 이용하였습니다. 그래서 Notice, FAQ, QnA에 따라 중분류, 소분류 카테고리를 입력해야하고,write, list, modify등 카테고리가 사용되는 html에 자바스크립트를 추가해야 했습니다.<br>
만약 카테고리가 바뀌게 된다면 카테고리가 사용되는 모든 html의 자바스크립트를 변경해줘야 하므로 좋은 방법이 아닙니다.<br>
그래서 데이터베이스에 카테고리를 저장해두었고, API를 이용하여 카테고리를 받았습니다.<br>
이 방법을 통해서 카테고리를 불러오고, 수정하기 용이해졌습니다.

