	
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
            function detailed(oid){
            	window.location.href =path+"/adminorder/detailed?oid="+oid;
            }