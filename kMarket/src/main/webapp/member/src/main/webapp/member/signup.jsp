<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
window.onload = function(){
	
	const chk1 = document.getElementsByName('agree1')[0];
	const chk2 = document.getElementsByName('agree2')[0];
	const chk3 = document.getElementsByName('agree3')[0];
	const chk4 = document.getElementsByName('agree4')[0];
	
	const agree = document.querySelector('.agree');
	agree.addEventListener('click', function(e){
		e.preventDefault();
		console.log("클릭!");
		
		if(!chk1.checked){
			alert('이용약관에 동의하셔야 합니다.');
			return;
		}else if(!chk2.checked){
			alert('전자금융거래 이용약관에 동의하셔야 합니다.');
			return;
		}else if(!chk3.checked){
			alert('개인정보 수집동의에 동의하셔야 합니다.');
		}else if(!chk4.checked){
			alert('위치정보 이용약관에 동의하셔야 합니다.');
		}else{
			location.href = '/Kmarket/member/register.do';
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
      <h3>
        <span class="essential">(필수)</span>케이마켓 이용약관
      </h3>
      <textarea class="terms" readonly>${dto.getTerms()}</textarea>
      <label>
        <input type="checkbox" name="agree1" />동의합니다.
      </label>
      <h3>
        <span class="essential">(필수)</span>전자금융거래 이용약관
      </h3>
      <textarea class="financial" readonly>${dto.getFinance()}</textarea>
      <label> <input type="checkbox" name="agree2" />동의합니다. </label>
      <h3>
        <span class="essential">(필수)</span>개인정보 수집동의
      </h3>
      <textarea class="privacy" readonly>${dto.getPrivacy()}</textarea>
      <label>
        <input type="checkbox" name="agree3" />동의합니다.
      </label>
    </section>
    <section>
      <h3>
        <span class="optional">(선택)</span>위치정보 이용약관
      </h3>
      <textarea class="location" readonly>${dto.getLocation()}</textarea>
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