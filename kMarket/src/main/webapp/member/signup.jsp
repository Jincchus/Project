<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
window.onload = function(){
	
	/*const chk1 = document.getElementsByName('agree1')[0];
	
	chk1.addEventListener('click', function(e){
		e.preventDefault();
		console.log("클릭!");
		
        if (!chk1.checked) {
            alert('이용약관에 동의하셔야 합니다.');
            return;
        
        	location.href = '/kMarket/member/register.do?type=normal';
        }
	}); */
	
    // 동의하기 버튼 클릭 이벤트 핸들러
    
	const agreeButton = document.querySelector('.agree');

    agreeButton.addEventListener('click', function() {
        // 이용약관 동의 체크박스
        const chk1 = document.getElementsByName('agree1')[0];

        if (!chk1.checked) {
            alert('이용약관에 동의하셔야 합니다.');
        } else {
            // 이동할 페이지의 URL을 여기에 지정
            const registerPageURL = '/kMarket/member/register.do?type=normal';
            location.href = registerPageURL;
        }
    });
	
} 
</script>
<main id="member">
	<div class="signup">
  		<nav>
    		<h1>약관동의</h1>
  		</nav>
   		<section>
   			<c:forEach var="term" items="${terms}">
		    	<h3>
		      		<span class="essential">(필수)</span>${term.title}
		    	</h3>
		        <textarea class="terms1" readonly>${term.content}</textarea>
		        <label>
		            <input type="checkbox" name="agree1" />동의합니다.
		        </label>
	  		</c:forEach>
		</section>
		<section>
			<h3>
				<span class="optional">(선택)위치정보 이용약관</span>
			</h3>
			<textarea class="location" readonly></textarea>
			<label>
				<input type="checkbox" name="agree4" />동의합니다.
			</label>
		</section>
	 	<div>
	 		<input type="button" class="agree" value="동의하기">
		</div>
	</div>
</main>
<%@ include file="./_footer.jsp" %>