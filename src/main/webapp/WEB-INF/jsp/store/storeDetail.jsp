<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>选购</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" media="all"/>
<link href="${pageContext.request.contextPath }/css/slider.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/move-top.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easing.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/startstop-slider.js"></script>
</head>
<body>
  <div class="wrap">
	<div class="header">
		<div class="headertop_desc">
			<div class="call">
				 当前城市:<span id="city"></span>&nbsp;<span id="tianqi"></span>
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
					<li><a href="#">查看</a>
					</li>
					<li>
						<c:if test="${user==null}">
							<a href="${pageContext.request.contextPath }/user/login">我的购物车</a>
						</c:if>
						<c:if test="${user!=null}">
							<a href="${pageContext.request.contextPath }/cart/tocart">我的购物车</a>
						</c:if>
					</li>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="header_top">
			<div class="logo">
				<a href="${pageContext.request.contextPath }/store/index"><img src="${pageContext.request.contextPath }/images/logo.png" alt="" /></a>
			</div>
			  <div class="cart">
			  	   <p>欢迎来到大众商城! <span id="userlogin">${user.name}</span><div id="dd" class="wrapper-dropdown-2">￥<span id="detail">0.00</span>元
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
			    	<li class="active"><a href="${pageContext.request.contextPath }/store/index">主页</a></li>
			    	<div class="clear"></div>
     			</ul>
	     	</div>
	     	<div class="search_box">
	     		<form action="${pageContext.request.contextPath }/store/queryStore" method="post">
	     			<input type="text" name="queryText"><input type="submit" value="">
	     		</form>
	     	</div>
	     	<div class="clear"></div>
	     </div>
	</div>
 <div class="main">
    <div class="content">
			<div class="section group">
				<c:forEach items="${stores }" var="p">
					<div class="images_1_of_4" style="float:left;margin-left:10px;margin-top:20px;border:1px solid #CCC;">
						<a href="${pageContext.request.contextPath }/store/getByid?id=${p.id }"><img width="212px" height="212px" src="${p.image }" alt="" /></a>					
						<h2>${p.name }</h2>
						<div class="price-details">
				       		<div class="price-number">
								<p><span class="rupees">￥${p.shop_price  }</span></p>
					    	</div>
					       	<div class="add-cart">								
								<h4><a class="addcart" href="#" addcartid="${p.id }">添加到购物车</a></h4>
							</div>
						<div class="clear"></div>
					</div>
				</div>
				</c:forEach>			
			</div>
    </div>
 </div>
</div>
   <div class="footer">
   	  <div class="wrap">	
	     		
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
<script src="${pageContext.request.contextPath }/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	dd();
	$.ajax({
		url:"http://restapi.amap.com/v3/ip?key=6830bd278765a3715bda9c4de2b972d3",
		success:function(e){
			console.log(e);
			var city=e.city;
			var province=e.province;
			$("#city").text(province+city);
			$.ajax({
					url:"https://restapi.amap.com/v3/weather/weatherInfo?&key=6830bd278765a3715bda9c4de2b972d3&city="+city,
					success:function(e){
						var weather=e.lives[0].weather;
						var temperature=e.lives[0].temperature;
						var reporttime=e.lives[0].reporttime;
						$("#tianqi").text(weather+" "+temperature+"℃"+"  数据发布的时间:"+reporttime);
					}
			})	
		}
	})
	$(".addcart").click(function(){
		var pid=$(this).attr("addcartid");
		var userlogin=$("#userlogin").text();
		if(userlogin==""){
			window.location.href="${pageContext.request.contextPath }/user/login";
		}else{
			$.ajax({
	        	type : "POST",
	        	url  : "${pageContext.request.contextPath }/cart/addcart",
	        	data : {
	        		"pid" : pid,
	        	},
	        	success : function(result) {
	        		if (result=="true") {
						layer.msg("加入购物车成功", {time:2000, icon:6, shift:6}, function(){                   	
	                    });
						dd();
	        		} else {
	                    layer.msg("加入购物车失败", {time:2000, icon:5, shift:6}, function(){
	                    	
	                    });
	        		}
	        	}
	        });
		}
		
		return false;
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
        		if(result.bl){
	        		var total=result.total;
	        		var tableContent="";
	        		$("#detail").text(total);
	       			$("#oncart").remove();
	       			$(".carttable").show();
					$.each(result.cartItems, function(i, c){
						tableContent += '<tr>';
	   	               	tableContent += '  <td align="center"><img width="50px" height="50px" src="'+c.product.image+'"><br/>'+c.product.name+'</td>';
						tableContent += '  <td>'+c.num+'</td>';
						tableContent += '  <td>'+c.subtotal+'</td>';
						tableContent += '<td>';
					});
	       			$("#cartData").html(tableContent);
        		}
        	}
        });
		
	}
})
</script>
</html>