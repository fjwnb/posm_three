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
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/tasp.css" />
<link href="${pageContext.request.contextPath }/css/orderconfirm.css" rel="stylesheet" />
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
					<li><a href="#">查看</a></li>
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
			 	  <input type="hidden" id="uid" value="${user.id}"/>
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
	<div style="width:1200px;margin:auto;">
		<div id="address" class="address" style="margin-top: 20px;" data-spm="2">
		<h3 style="font-size:24px">确认收货地址
		 <span class="manage-address">
		 <a href="#" title="管理我的收货地址"
		 class="J_MakePoint" data-point-url="http://log.mmstat.com/buy.1.7">管理收货地址</a>
		 </span>
		</h3>
		<ul id="address-list" class="address-list">
		    <li class="J_Addr J_MakePoint clearfix" id="dizi">
		        
		   </li>
		 </ul>
		<ul id="J_MoreAddress" class="address-list hidden">	     
		 </ul>
		
		<div class="address-bar">
		 <a href="#" class="new J_MakePoint" id="J_NewAddressBtn">使用新地址</a>
		 </div>
		</div>
		<div>
		 <h3 class="dib" style="font-size:24px">确认订单信息</h3>
		 <table cellspacing="0" cellpadding="0" class="order-table" id="J_OrderTable" summary="统一下单订单信息区域">
		 <thead>
		 <tr>
		 <th class="s-title">商品<hr/></th>
		 <th class="s-price">单价(元)<hr/></th>
		 <th class="s-amount">数量<hr/></th>
		 <th class="s-agio">优惠方式(元)<hr/></th>
		 <th class="s-total">小计(元)<hr/></th>
		 </tr>
		 </thead>
		<tbody data-spm="3" class="J_Shop" data-tbcbid="0" data-outorderid="47285539868"  data-isb2c="false" data-postMode="2" data-sellerid="1704508670">
		 <c:forEach items="${list }" var="c">
		 	<tr class="item" data-lineid="19614514619:31175333266:35612993875" data-pointRate="0">
		     <td class="s-title">
		       <img height="50px" width="50px" src="${c.product.image }">&nbsp;&nbsp;&nbsp;${c.product.name }
		    </td>
		 	<td class="s-price">
		  	<span class='price '>
		 	<em class="style-normal-small-black J_ItemPrice"  >${c.product.shop_price }</em>
		  	</span>
		<input type="hidden" name="costprice" value="630.00" class="J_CostPrice" />
		</td>
		 <td class="s-amount" data-point-url="">
		    ${c.num }
		 </td>
		 <td class="s-agio">
		       <div class="J_Promotion promotion" data-point-url="">无优惠</div>
		   </td>
		 <td class="s-total">
		   
		 <span class='price '>
		 <em class="style-normal-bold-red J_ItemTotal ">${c.subtotal }</em>
		  </span>
		 </td>
		</tr>
		</c:forEach>
		<tr class="item-service">
		 <td colspan="5" class="servicearea" style="display: none"></td>
		</tr>
		
		<tr class="blue-line" style="height: 2px;"><td colspan="5"></td></tr>
		</tbody>
		<tfoot>
		 <tr>
		 <td colspan="5">
		
		<div class="order-go" data-spm="4">
		<div class="J_AddressConfirm address-confirm">
		 <div class="kd-popup pop-back" style="margin-bottom: 40px;">
		 <div class="box">
		 <div class="bd">
		 <div class="point-in">   
		   <em class="t">实付款：</em>  <span class='price g_price '>
		 <span>&yen;</span><em class="style-large-bold-red"  id="J_ActualFee"  >${total }</em>
		  </span>
		</div>
		
		  <ul >
		 <li><em>寄送至:</em><span id="J_AddrConfirm" style="word-break: break-all;"></span></li>
		 <li><em>收货人:</em><span id="J_AddrNameConfirm"></span></li>
		 </ul>
		     </div>
		 </div>
		         <a href="${pageContext.request.contextPath }/cart/tocart" style="width:76px;" class="back J_MakePoint" target="_top">返回购物车</a>
		       <a id="J_Go" class=" btn-go"  data-point-url=""  tabindex="0" title="点击此按钮，提交订单。">提交订单<b class="dpl-button"></b></a>
		  </div>
		 </div>
		 <div class="msg" style="clear: both;">
		 	<p class="tips naked" style="float:right;padding-right: 0">若价格变动，请在提交订单后联系卖家改价，并查看已买到的宝贝</p>
		 </div>
		 </div>
		 </td>
		 </tr>
		 </tfoot>
		 </table>
		</div>
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
<script src="${pageContext.request.contextPath }/layer/layer.js"></script>
<script>
$(function(){
	dd();
	address();
	aa();
	$.ajax({
		url:"http://restapi.amap.com/v3/ip?key=6830bd278765a3715bda9c4de2b972d3",
		success:function(e){
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
	function address(){
		var uid=$("#uid").val();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/address/getaddress",
			data:{"uid":uid,"data":new Date()},
			async:false,
			success:function(result){
        		var tableContent="";
				$.each(result,function(i, c){  
					tableContent += '<div class="address-info">';
					if(i==0){
						tableContent +='<input class="address2" type="radio" checked="checked" value="'+c.id+'" name="a">'+'<span class="address">'+c.address+'</span>&nbsp;'+'<span class="name">'+c.name+'</span>&nbsp;'+'<span class="tel">'+c.tel+'</span>';
					}else{
						tableContent +='<input class="address2" type="radio"  value="'+c.id+'" name="a">'+'<span class="address">'+c.address+'</span>&nbsp;'+'<span class="name">'+c.name+'</span>&nbsp;'+'<span class="tel">'+c.tel+'</span>';
					}
					tableContent += '</div>';
				});
       			$("#dizi").html(tableContent);
			}
		})
	}
	//刷新下面地址哪些
	function aa(){
		$(".address2").each(function(){
			var fal=this.checked;
			if(fal){
				var address=$(this).siblings(".address").text();
				var name=$(this).siblings(".name").text();
				var tel=$(this).siblings(".tel").text();
				$("#J_AddrConfirm").text(address);
				$("#J_AddrNameConfirm").text(name+"    "+tel);
			}
		})
	}
	$(".address2").click(function(){
		aa();
	})
	
	$("#J_Go").click(function(){
		var total=$("#J_ActualFee").text();
		var aid;
		$(".address2").each(function(){
			if(this.checked==true){
				aid=$(this).val();
			}
		})
		var loadingIndex = null;
		layer.confirm("确认要提交吗",  {icon: 3, title:'提示'}, function(cindex){
			layer.close(cindex);
			$.ajax({
				type:"post",
		    	url:"${pageContext.request.contextPath }/order/settleorder",
		    	data:{"aid":aid,"total":total},
		    	beforeSend : function(){
        			loadingIndex = layer.msg('提交中', {icon: 16});
        		},
		    	success:function (result){
		    		layer.close(loadingIndex);
		    		if(result.success){
		    			window.location.href = "${pageContext.request.contextPath }/store/index";
		    		}else{
		    			layer.msg("提交失败", {time:2000, icon:5, shift:6}, function(){
                        	
                        });
		    		}
		    		
		    	}
			})
		}, function(cindex){
			
		});
	})
})
</script>
</html>