	
		var path=$("#path").val();
		var likeflg = false;
            $(function () {
			    pageQuery(1);
			    $("#queryBtn").click(function(){
			    	var queryText = $("#queryText").val();
			    	if ( queryText == "" ) {
			    		likeflg = false;
			    	} else {
			    		likeflg = true;
			    	}
			    	
			    	pageQuery(1);
			    });		    
            });
            // 分页查询
            function pageQuery(pageno) {
            	var loadingIndex = null;
            	var jsonData = {"pageno" : pageno, "pagesize" : 5};
            	if ( likeflg == true ) {
            		jsonData.queryText = $("#queryText").val();
            	}	
            	$.ajax({
            		type : "POST",
            		url  : path+"/adminorder/pageQuery",
            		data : jsonData,
            		beforeSend : function(){
            			loadingIndex = layer.msg('处理中', {icon: 16});
            		},
            		success : function(result) {
            			layer.close(loadingIndex);
            			if ( result.success ) {
            				// 局部刷新页面数据
            				var tableContent = "";
            				var pageContent = "";
            				var order = result.data;
            				var orders = order.datas;	
            				$.each(orders, function(i, c){
            	                tableContent += '<tr>';
	          	                tableContent += '  <td>'+(i+1)+'</td>';
								tableContent += '  <td>'+c.coding+'</td>';
								tableContent += '  <td>'+c.address.name+'</td>';
								tableContent += '  <td>'+c.address.tel+'</td>';
								tableContent += '  <td>'+c.address.address+'</td>';
								tableContent += '  <td >'+c.orderTime+'</td>';
								tableContent += '  <td>'+c.total+'</td>';	
								tableContent += '<td>';
								tableContent += '<button type="button"  data-toggle="modal" data-target="#updateModa" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i>编辑</button>';		
	          					tableContent += '<button type="button" onclick="detailed('+c.id+')"  class="btn btn-info btn-xs"><i class=" glyphicon glyphicon-list-alt"></i>详情</button>';
								tableContent += '</td>';
								tableContent += '</tr>';
            				});
            				if ( pageno > 1 ) {
            					pageContent += '<li><a href="#" onclick="pageQuery('+(pageno-1)+')">上一页</a></li>';
            				}
            				
            				for ( var i = 1; i <= order.totalno; i++ ) {
            					if ( i == pageno ) {
            						pageContent += '<li class="active"><a  href="#">'+i+'</a></li>';
            					} else {
            						pageContent += '<li ><a href="#" onclick="pageQuery('+i+')">'+i+'</a></li>';
            					}
            				}
            				if ( pageno < order.totalno ) {
            					pageContent += '<li><a href="#" onclick="pageQuery('+(pageno+1)+')">下一页</a></li>';
            				}
            				$("#ordersData").html(tableContent);
            				$(".pagination").html(pageContent);
            			} else {
                            layer.msg("用户信息分页查询失败", {time:2000, icon:5, shift:6}, function(){
                            	
                            });
            			}
            		}
            	});
            }
            /**
             * 为修改的静态框用ajax赋值
             * @param id
             * @returns
             */
            function goUpdatePage(id) {
            	$.ajax({
            		type:"post",
            		url:path+"/product/ajaxget",
            		data:{"id":id},
            		success:function(result){
            			var product=result.data;
            			$("#umarket_price").val(product.market_price);
            			$("#ushop_price").val(product.shop_price);
            			$("#update").val(product.pdate);
            			$("#updesc").val(product.pdesc);updateid
            			$("#updateid").val(product.id);
            		}         		
            	});
            }
            $("#updateproduct").click(function(){
				layer.confirm("确认要修改吗",  {icon: 3, title:'提示'}, function(cindex){
					layer.close(cindex);
					var loadingIndex = null;
					var id= $("#updateid").val();
					var market_price=$("#umarket_price").val();
					var shop_price=$("#ushop_price").val();
					var pdate=$("#update").val();
					var pdesc=$("#updesc").val();
			        $.ajax({
			        	type : "POST",
			        	url  : path+"/product/ajaxproductedit",
			        	data : {
			        		"market_price" : market_price,
			        		"shop_price" : shop_price,
			        		"pdate" : pdate,
			        		"pdesc" : pdesc,
			        		"id":id        
			        	},
			        	beforeSend : function(){
			        		loadingIndex = layer.msg('处理中', {icon: 16});
			        	},
			        	success : function(result) {
			        		layer.close(loadingIndex);	  
			        		if (result.success) {
			        			$("#updateclose").click();
								pageQuery(1);			
			        		}else{
								layer.msg("抱歉，修改失败", {time:2000, icon:2, shift:6}, function(){
			               
			                    });
			        		}
			        	}
			        });	
						
				}, function(cindex){
								
				});
		    })
            function detailed(oid){
            	window.location.href =path+"/adminorder/detailed?oid="+oid;
            }