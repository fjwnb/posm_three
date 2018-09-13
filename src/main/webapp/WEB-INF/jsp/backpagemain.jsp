<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	.tree-closed {
	    height : 40px;
	}
	.tree-expanded {
	    height : auto;
	}
	a:link{
		text-decoration:none;
	}
	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" style="background-color:#6CF;color:#FFF;" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">大众购物 - 后台控制面板</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="margin-left:10px;padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> ${admin.name }<span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="${pageContext.request.contextPath }/admin/logout"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed">
						<span>
							<a href="${pageContext.request.contextPath }/category/categorymain" target="name"><i class="glyphicon glyphicon glyphicon-tasks"></i> 分类管理 </a>
						</span>
					</li>
					<li class="list-group-item tree-closed">
						<a href="${pageContext.request.contextPath }/product/productmain" target="name"><i class="glyphicon glyphicon-gift"></i> 商品管理 </a> 
					</li>
					<li class="list-group-item tree-closed">
						<a href="${pageContext.request.contextPath }/adminorder/ordermain" target="name"><i class="glyphicon glyphicon-ok"></i> 订单管理 </a> 
					</li>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="height:600px;">
          <iframe frameborder="0" name="name" height="100%" width="100%"></iframe>
        </div>
      </div>
    </div>
    <script src="${pageContext.request.contextPath }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/script/docs.min.js"></script>
  </body>
</html>