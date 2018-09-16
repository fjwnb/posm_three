<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/Wopop_files/style_log.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/Wopop_files/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/Wopop_files/userpanel.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/Wopop_files/jquery.ui.all.css">
</head>
<body class="login" mycollectionplug="bind">
<div class="login_m">
	<div class="login_logo" style="font-size:40px">登录</div>
	<div class="login_boder">
	<div class="login_padding" id="login_model">
	<form>
  		<h2>用户名</h2>
        <label>
        <input type="text" id="username" class="txt_input txt_input2" >
         </label>
      	<h2>密码</h2>
      	<label>
        	<input type="password" name="password" id="password" class="txt_input" o>
      	</label>
  		<p class="forgot"><a id="iforget" href="${pageContext.request.contextPath }/user/register">没有账号?注册</a></p>
  		<div class="rem_sub">
            <div class="rem_sub_l">
                <input type="checkbox" name="checkbox" id="save_me">
                <label for="checkbox">记住密码</label>
             </div>
        <label>
          <input type="button" class="sub_button" name="button" id="Btnlogin" value="登录" style="opacity: 0.7;">
        </label>
  		</div>
  		</form>
	</div>
</div>
</div>
</body>
<script src="${pageContext.request.contextPath }/jquery/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath }/layer/layer.js"></script>
 <script type="text/javascript">
 $(function(){
	 $("#Btnlogin").click(function(){
		// 非空校验
	        var username = $("#username").val();
	        // 表单元素的value取值不会为null, 取值是空字符串
	        if ( username == "" ) {
	            layer.msg("用户登录账号不能为空，请输入", {time:2000, icon:5, shift:6}, function(){
	            	
	            });
	        	return;
	        }
	        var password = $("#password").val();
	        if ( password == "" ) {
	            layer.msg("用户登录密码不能为空，请输入", {time:2000, icon:5, shift:6}, function(){
	            	
	            });
	        	return;
	        }
	        var loadingIndex = null;
	        $.ajax({
	        	type : "POST",
	        	url  : "${pageContext.request.contextPath }/user/doAJAXLogin",
	        	data : {
	        		"username" : username,
	        		"password"  : password
	        	},
		        
	        	beforeSend : function(){
	        		loadingIndex = layer.msg('处理中', {icon: 16});
	        	},
	        	success : function(result) {
	        		layer.close(loadingIndex);
	        		if (result.success) {
	        			window.location.href = "${pageContext.request.contextPath }/store/index";
	        		} else {
	                    layer.msg("用户登录账号或密码不正确，请重新输入", {time:2000, icon:5, shift:6}, function(){
	                    	
	                    });
	        		}
	        	}
	        });
	 })
	 
 })
</script>
</html>