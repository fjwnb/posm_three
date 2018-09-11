<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大众商城管理员登录</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/csslogin/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/csslogin/body.css"/> 
</head>
<body>
<div class="container">
	<section id="content">
		<form action="">
			<h1>管理员登录</h1>
			<div>
				<input type="text" placeholder="账户" id="username" />
			</div>
			<div>
				<input type="password" placeholder="密码" id="password" />
			</div> 
			<div>
				<input type="button" onclick="dologin()" value="登录" class="btn btn-primary" />
			</div>
		</form>
	</section>
</div>
<script src="${pageContext.request.contextPath }/jquery/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath }/layer/layer.js"></script>
</body>
  <script type="text/javascript">
    function dologin() {
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
        	url  : "doAJAXLogin",
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
        			window.location.href = "backpagemain";
        		} else {
                    layer.msg("用户登录账号或密码不正确，请重新输入", {time:2000, icon:5, shift:6}, function(){
                    	
                    });
        		}
        	}
        });
    }
    </script>
</html>