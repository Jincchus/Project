/**
 * 사용자 개인정보 중복 체크
*/


$(function(){

	//아이디 중복체크
	
	$('#btnCheckUid').click(function(){
		
		const uid = $('input[name=km_uid]').val();
		
		if(!uid.match(reUid)){
			$('.msgId').css('color','red').text('유효한 아이디가 아닙니다.');
			isUidOk = false;
			return;
		}
		
		const jsonData ={
			"uid":uid
		};
		
		$.ajax({
			url:'/Farmstory2/user/checkUid.do',
			type:'GET',
			data: jsonData,
			dataType: 'json',
			success:function(data){
				if(data.result >= 1){
					$('.resultId').css('color','red').text('이미 사용 중인 아이디입니다.');
					isUidOk = false;
					
				}else{
					$('.resultId').css('color','green').text('사용가능한 아이디 입니다.');
					isUidOk = true;
				}
				
			}
			
		});
		
	});
	
	/*
	//닉네임 중복체크
	$('#btnCheckNick').click(function(){
		
		//입력 데이터 가져오기
		const nick = $('input[name=nick]').val();
		console.log(nick);
		
		if(!nick.match(reNick)){
			$('.resultNick').css('color','red').text('유효한 닉네임이 아닙니다.');
			isNickOk= false;
			return;
		}
		
		//json생성
		const jsonData ={
			"nick":nick
		};
		
		//데이터 전송
		$.getJSON('/Farmstory2/user/checkNick.do',jsonData, function(data){
			if(data.result>0){
				$('.resultNick').css('color','red').text('이미 사용중인 닉네임 입니다.');
				isNickOk = false;
			}else{
				$('.resultNick').css('color','green').text('사용 가능한 닉네임입니다.');
				isNickOk = true;
			}
			
		});
	});//닉네임 중복 체크 끝
	*/
		
	//이메일 중복 체크
	document.getElementsByName('km_email')[0].onfocusout = function(){
		const resultEmail = document.getElementsByClassName('msgEmail')[0];
		
		//입력 데이터 가져오기
		const email = this.value;
		
		if(!email.match(reEmail)){
			resultEmail.innerText = '유효한 이메일이 아닙니다.';
			resultEmail.style.color= 'red';
			isEmailOk = false;
			return;
		}
		
		//데이터 전송
		const xhr = new XMLHttpRequest();
		xhr.open('GET', '/Farmstory2/user/checkEmail.do?email='+email);
		xhr.send();
		
		//응답 결과
		xhr.onreadystatechange = function(){
			if(xhr.readyState == XMLHttpRequest.DONE){
				if(xhr.status == 200){
					const data = JSON.parse(xhr.response);
					console.log('data : '+data);
					
					if(data.result >0 ){
						resultEmail.innerText = '이미 사용중인 이메일 입니다.';
						resultEmail.style.color= 'red';
						isEmailOk = false;
					}else{
						resultEmail.innerText='사용 가능한 이메일 입니다.';
						resultEmail.style.color='green';
						isEmailOk = true;
					}
					
				}
			}
			
		}
		
	};
		
	//휴대폰 중복체크
	document.getElementsByName('km_hp')[0].addEventListener('focusout',function(){
		
		const resultHp = document.getElementsByClassName('msgHp')[0];
		const hp = this.value;
		
		
		if(!hp.match(reHp)){
			resultHp.innerText='유효한 휴대폰 번호가 아닙니다.';
			resultHp.style.color='red';
			isHpOk = false;
			return;
		}
		
		const url = '/Farmstory2/user/checkHp.do?hp='+this.value;
		
		fetch(url)
					.then(response => response.json())
					.then(data => {
						
						console.log(data);
						
						if(data.result > 0){
							resultHp.innerText='이미 사용중인 휴대폰 번호입니다.';
							resultHp.style.color='red';
							inHpOk = false;
						}else{
							resultHp.innerText='사용 가능한 휴대폰 번호입니다.';
							resultHp.style.color='green';
							isHpOk = true;
						}
						
					});
		
		
	});//end
	
	
});