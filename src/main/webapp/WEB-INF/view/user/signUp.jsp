<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- header -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<!-- 메인페이지 -->
<div class="col-sm-8">
	<h2>회원 가입</h2>
	<h5>어서오세요 환영합니다</h5> <!-- multipart/form-data 반드시 선언 -->
	<form action="/user/sign-up" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="username">username:</label> 
			<input type="text"	name="username" class="form-control" placeholder="Enter username" id="username" value="경진">
		</div>
		
		<div class="form-group">
			<label for="pwd">password:</label> 
			<input type="password" name="password" class="form-control" placeholder="Enter password" id="pwd" value="1234">
		</div>
		
		<div class="form-group">
			<label for="fullname">fullname:</label> 
			<input type="text" name="fullname" class="form-control" placeholder="Enter fullname" id="fullname" value="박">
		</div>
		
		<div class="form-group">
			<label for="eMail">e-mail:</label> 
			<input type="text" name="eMail" class="form-control" placeholder="Enter eMail" id="eMail" value="chopoo2001@gmail.com">
			<input type="button" class="btn btn-primary" id="emailCheck" value="중복 확인"></input>
		</div>
				  
		</br>
		
	     <div class="custom-file">
		    <input type="file" class="custom-file-input" id="customFile" name="customFile">
		    <label class="custom-file-label" for="customFile">Choose file</label>
		  </div>
		</br>
		</br>
		<button type="submit" class="btn btn-primary">회원가입</button>
	</form>
</div>
</div>
</br>
</div>

<script>

// 이메일 중복 검사
$("#emailCheck").click(function () {
    $.ajax({
        url: "/email-check",
        type: "post",
        dataType: "json",
        data: {"eMail": $("#eMail").val()},
        success: function (data) {
            if (data >= 1) {
                alert("이미 존재하는 이메일입니다.");
            } else {
                alert("사용 가능한 이메일입니다.");
            }
        }
    })
});
</script>




<!-- footer -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>



