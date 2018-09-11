<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" media="all"/>
 <link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
 <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/move-top.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easing.js"></script>
<script src="${pageContext.request.contextPath }/js/easyResponsiveTabs.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath }/css/easy-responsive-tabs.css" rel="stylesheet" type="text/css" media="all"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/global.css">
<script src="${pageContext.request.contextPath }/js/slides.min.jquery.js"></script>
<script src="${pageContext.request.contextPath }/layer/layer.js"></script>
<style type="text/css">
	.main .content .row{
		margin:40px 0px;
	}
</style>
</head>
<body>
  <div class="wrap">
	<div class="header">
		<div class="headertop_desc">
			<div class="call">
				 <p><span>需要帮助?</span>打我电话 <span class="number">1-22-3456789</span></span></p>
			</div>
			<div class="account_desc">
				<ul>
					<li>
						<c:if test="${user!=null}">
							<a href="${pageContext.request.contextPath }/user/outlogin">注销</a>
						</c:if>
						<c:if test="${user==null}">
							<a href="${pageContext.request.contextPath }/user/login">登录</a>
						</c:if>
					</li>
					<li><a href="#">查看</a></li>
					<li><a href="${pageContext.request.contextPath }/cart/tocart">我的购物车</a>
				</li></ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="header_top">
			<div class="logo">
				<a href="${pageContext.request.contextPath }/store/index"><img src="${pageContext.request.contextPath }/images/logo.png" alt="" /></a>
			</div>
			 <div class="cart">
			  	  <p>欢迎来到大众商城! <span>${user.name}</span><div id="dd" class="wrapper-dropdown-2">￥<span id="detail">0.00</span>元
			  	   	<ul class="dropdown">
							<li ><span id="oncart">你的购物车中没有商品</span>
									<table class="carttable" style="display:none;">
										<thead>
											<tr>
												<td width="107px">商品</td>
												<td width="40px">数量</td>
												<td width="40px">小计</td>
											</tr>
										</thead>
										<tbody id="cartData">
											          
              							</tbody>
									</table>
								</li>
					</ul></div></p>
			  </div>
			  <script type="text/javascript">
				function DropDown(el) {
					this.dd = el;
					this.initEvents();
				}
				DropDown.prototype = {
					initEvents : function() {
						var obj = this;
	
						obj.dd.on('click', function(event){
							$(this).toggleClass('active');
							event.stopPropagation();
						});	
					}
				}
	
				$(function() {
	
					var dd = new DropDown( $('#dd') );
	
					$(document).click(function() {
						// all dropdowns
						$('.wrapper-dropdown-2').removeClass('active');
					});
				});
	
			</script>
	 	<div class="clear"></div>
  	</div>
	<div class="header_bottom">
	     	<div class="menu">
	     		<ul>
			    	<li class="active"><a href="${pageContext.request.contextPath }/store/index">主页</a></li
     			></ul>
	     	</div>
	     	<div class="search_box">
	     		<form action="${pageContext.request.contextPath }/store/queryStore" method="post">
	     			<input type="text" name="queryText"><input type="submit" value="">
	     		</form>
	     	</div>
	     	<div class="clear"></div>
	     </div>	
 <div class="main" >
    <div class="content">
     	<div class="row">
          <div class="col-lg-1"><input type="checkbox" value="1" id="allSelBox">全选</div>
          <div class="col-lg-5">商品信息</div>
          <div class="col-lg-1">单价</div>
          <div class="col-lg-2">数量</div>
          <div class="col-lg-2">金额</div>
          <div class="col-lg-1">操作</div>
        </div>
        <hr color="#333333"/>
        <c:forEach items="${cart }" var="c">
        	<div class="row">
	           <div class="col-lg-1"><input type="checkbox" value="${c.product.id }" class="shopp" name="shopp"></div>
	           <div class="col-lg-2"><img width="50px" height="50px" src="${c.product.image }"></div>
	           <div class="col-lg-3">${c.product.name }:${c.product.pdesc }</div>
	           <div class="col-lg-1">￥<span>${c.product.shop_price }</span></div>
	           <div class="col-lg-2 a">
	          	<input class="jian" type="button" value="-">
	            <input type="text" size="2" value="${c.count }" name="count" class="count">
	            <input class="jia" type="button" value="+">
	          </div>
             <div class="col-lg-2 b">￥<span>${c.subtotal }</span>元</div>
             <div class="col-lg-1"><a href="#" class="deletecart" pid="${c.product.id }">删除</a>
             </div>
        </div>
        <hr color="#333333"/>
        </c:forEach>
    </div>
   </div>
 <div class="panel panel-default">
  <div class="panel-body">
   <div class="row">
           <div class="col-lg-5">总量:<span id="alltotal"></span></div>
          <div class="col-lg-5">总价:<span id="allprice"></span>       元</div>
          <div class="col-lg-2">
          	<button type="button" class="btn btn-default btn-lg">
  				<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> 结算
			</button>
          </div>
        </div>
  </div>
</div>
   <div class="footer">
   	  <div class="wrap">	
	     <div class="section group">
				
		</div>			
        </div>
        <div class="copy_right">
				<p>版权所有©2018。公司名称保留所有权利。</p>
		   </div>
    </div>
   <script type="text/javascript">
		$(document).ready(function() {			
			$().UItoTop({ easingType: 'easeOutQuart' });
			
		});
	</script>
    <a href="#" id="toTop"><span id="toTopHover"> </span></a>
</body>
<script>
$(function(){
	dd();
	$("#allSelBox").click(function(){
		 var flg = this.checked; 	
		$(".shopp").each(function(){
			    this.checked = flg;
		});
		all();
	});
	$(".shopp").click(function(){
		var i=0;
		var j=0;
		$(".shopp").each(function(){
			if(this.checked==true){
				i+=1;
			}
			j+=1;	
		});
		if(i!=j){
			$("#allSelBox").attr("checked",false);
		}else{
			$("#allSelBox").attr("checked",true);
		}
		all();
	})
	//计算总钱，总数量
	function all(){
		var i=0;
		var j=0;
		$(".shopp").each(function(){
			if(this.checked==true){
				var a=$(this).parent().siblings(".a").find(".count").val();
				i+=parseInt(a);
				var b=$(this).parent().siblings(".b").find("span").text();
				j+=parseFloat(b);
			}
		});
		$("#alltotal").text(i);
		$("#allprice").text(j);
	}
	$(".jian").click(function(){
		var oldcount=$(this).next().val();
		if(oldcount>1){
			$(this).next().val(parseInt(oldcount)-1);
		}else{
			$(this).next().val(1);
		}
		var newcount=$(this).next().val();
		var i=$(this).parent().prev().find("span").text();
		var s=parseFloat(newcount)*parseFloat(i);
		$(this).parent().next().find("span").text(parseFloat(s));
		all();
	})
	$(".jia").click(function(){
		var oldcount=$(this).prev().val();
		$(this).prev().val(parseInt(oldcount)+1);
		var newcount=$(this).prev().val();
		var i=$(this).parent().prev().find("span").text();
		var s=parseFloat(newcount)*parseFloat(i);
		$(this).parent().next().find("span").text(parseFloat(s));
		all();
	})
	
	/**
	*购物时页面头部显示金额随购物车的改变而改变金额
	*/
	function dd(){
		$.ajax({
        	type : "POST",
        	url  : "${pageContext.request.contextPath }/cart/getcart",
        	data : {},
        	success : function(result) {	
        		var total=result.total;
        		var tableContent="";
        		$("#detail").text(total);
       			$("#oncart").remove();
       			$(".carttable").show();
				$.each(result.cartItems, function(i, c){
					tableContent += '<tr>';
   	               	tableContent += '  <td align="center"><img width="50px" height="50px" src="'+c.product.image+'"><br/>'+c.product.name+'</td>';
					tableContent += '  <td>'+c.count+'</td>';
					tableContent += '  <td>'+c.subtotal+'</td>';
					tableContent += '<td>';
				});
       			$("#cartData").html(tableContent);
        	}
        });
		
	}
	//删除购物车的商品
	$(".deletecart").click(function(){
		var id=$(this).attr("pid");
		var $s=$(this).parents(".row");
		layer.confirm("确认移出购物车吗？",  {icon: 3, title:'提示'}, function(cindex){
			layer.close(cindex);
			var loadingIndex=null;
			$.ajax({
				type:"post",
		    	url:"${pageContext.request.contextPath }/cart/removecart",
		    	data:{"id":id},
		    	beforeSend : function(){
        			loadingIndex = layer.msg('移出中', {icon: 16});
        		},
		    	success:function (result){
		    		layer.close(loadingIndex);
		    		if(result.success){
						layer.msg("移出成功", {time:2000, icon:6, shift:6}, function(){
                        	
                        });
						$s.remove();;
						all();
						dd();
		    		}else{
		    			layer.msg("移出失败", {time:2000, icon:5, shift:6}, function(){
                        	
                        });
		    		}
		    		
		    	}
			})
		}, function(cindex){
			
		});
		return false;
	})
})
</script>
</html>