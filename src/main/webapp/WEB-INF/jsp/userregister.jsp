<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<script src="${pageContext.request.contextPath }/jquery/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/usercssandjs/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/usercssandjs/login.css">
</head>
<body>
	<div class="login_cont">
		<div class="login_nav">
			<div class="nav_slider">
				<a href="#" class="signup focus">注册</a>
			</div>
		</div>  
		<form id="userForm">
			<div class="input_signup active">
				<input class="input" id="username" autocomplete="off" type="text" aria-label="用户名（包含字母／数字／下划线" placeholder="用户名">
				<font class="tisi"></font>	
				<input class="input" id="name" type="text" autocomplete="off" aria-label="用户名（包含字母／数字／下划线" placeholder="真实姓名">			
				<font class="tisi"></font>	
				<input class="input" id="email" type="text" aria-label="邮箱" placeholder="邮箱">
				<font class="tisi"></font>	
				<input class="input" id="tel" type="text" aria-label="手机号（仅支持中国大陆）" placeholder="手机号（仅支持中国大陆）">
				<font class="tisi"></font>	
				<input class="input" id="password" type="password" aria-label="密码" placeholder="密码（不少于 6 位）">
				<font class="tisi"></font>	
				<input class="input" id="repassword" type="password" aria-label="密码" placeholder="再次输入密码">
                <font class="tisi"></font>	
                <input  type="date" id="birthday" aria-label="密码" placeholder="生日">
				<font class="tisi"></font>
				<input type="button" id="submit" class="button" name="button" value="注册">
			</div>
		</form>
		 <a href="${pageContext.request.contextPath }/user/login" class="signup focus">已有账号?登录</a>
	</div>
</body>
<script src="${pageContext.request.contextPath }/layer/layer.js"></script>
<script type="text/javascript">
 $(function(){
	 $("#username").focusout(function(){
		 $(this).next().text("");
		 if($(this).val()==""){
			 $(this).next().attr("color","red");
			 $(this).next().text("抱歉用户名不能为空!")
		 }else{
			 $(this).next().attr("color","green");
			 $(this).next().text("用户名正确!")
		 }	 
	 })
	 $("#name").focusout(function(){
		 $(this).next().text("");
		 if($(this).val()==""){
			 $(this).next().attr("color","red");
			 $(this).next().text("抱歉真实姓名不能为空!")
		 }else{
			 $(this).next().attr("color","green");
			 $(this).next().text("真实姓名正确!")
		 }	 
	 })
	  $("#email").focusout(function(){
		 $(this).next().text("");
		 var e=$(this).val();
		 var email=/^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/;
		 if($(this).val()==""){
			 $(this).next().attr("color","red");
			 $(this).next().text("抱歉邮箱不能为空!");
		 }else{
			 if(email.test(e)){
				 $(this).next().attr("color","green");
				 $(this).next().text("邮箱正确!")
			 }else{
				 $(this).next().attr("color","red");
				 $(this).next().text("抱歉邮箱格式错误!");
			 }
		 }	 
	 })
	 $("#tel").focusout(function(){
		 $(this).next().text("");
		 var tel=/^1\d{10}$/;
		 if($(this).val()==""){
			 $(this).next().attr("color","red");
			 $(this).next().text("抱歉手机不能为空!");
		 }else{
			 if(tel.test($(this).val())){
				 $(this).next().attr("color","green");
				 $(this).next().text("手机号码正确!")
			 }else{
				 $(this).next().attr("color","red");
				 $(this).next().text("抱歉手机格式错误!");
			 }
		 }	 
	 })
	  $("#password").focusout(function(){
		 $(this).next().text("");
		 if($(this).val()==""){
			 $(this).next().attr("color","red");
			 $(this).next().text("抱歉密码不能为空!")
		 }else{
			 $(this).next().attr("color","green");
			 $(this).next().text("密码正确!")
		 }	 
	 })
	  $("#repassword").focusout(function(){
		 $(this).next().text("");
		 var repassword=$(this).val();
		 var password=$("#password").val();
		 if(repassword==""){
			 $(this).next().attr("color","red");
			 $(this).next().text("抱歉真实姓名不能为空!")
		 }else if(repassword!=password){
			 $(this).next().attr("color","red");
		 	 $(this).next().text("两次密码不一致，请重新输入!")
		 }else{
			 $(this).next().attr("color","green");
			 $(this).next().text("确认密码正确!")
		 }	 
	 })
	  $("#birthday").focusout(function(){
		 $(this).next().text("");
		 if($(this).val()==""){
			 $(this).next().attr("color","red");
			 $(this).next().text("抱歉生日不能为空!")
		 }else{
			 $(this).next().attr("color","green");
			 $(this).next().text("生日正确!")
		 }	 
	 })
	 $("#submit").click(function(){
	        var loadingIndex = null;
	        var username=$("#username").val(); 
	        var name=$("#name").val();
	        var email=$("#email").val();
	        var tel=$("#tel").val();
	        var password=$("#password").val();
	        var birthday=$("#birthday").val();
	        var loadingIndex = null;
	        $.ajax({
	        	type : "POST",
	        	url  : "${pageContext.request.contextPath }/user/add",
	        	data : {
	        		"username" : username,
	        		"name"  : name,
	        		"email" : email,
	        		"tel" : tel,
	        		"password" : password,
	        		"birthday" : birthday
	        	},
		        
	        	beforeSend : function(){
	        		loadingIndex = layer.msg('处理中', {icon: 16});
	        	},
	        	success : function(result) {
	        		layer.close(loadingIndex);
	        		if (result=="have") {
						layer.msg("抱歉该用户名已经注册，请重新输入", {time:2000, icon:5, shift:6}, function(){
	                    	
	                    });
	        		}else if(result="true"){
	        			document.getElementById("userForm").reset();
						layer.msg("恭喜注册成功", {time:2000, icon:6, shift:6}, function(){	
	                    });
						$(".tisi").text("");
	        		} else {
	                    layer.msg("注册失败，请重新注册", {time:2000, icon:5, shift:6}, function(){
	                    	
	                    });
	        		}
	        		
	        	}
	        });
	 })
	 
 })
</script>
</html>