	
		var path=$("#path").val();
		var likeflg = false;
            $(function () {
			    pageQuery(1);
			    $.ajax({
					type:"post",
					url:path+"/product/ajaxcategory",
					date:{},
					success:function(result){
						var category = result.data;
						var a="";
						$.each(category, function(i, c){
							a+='<option value="'+c.id+'">'+c.name+'</option>';
						});
						$("#cid").html(a);
					}
				});
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
            		url  : path+"/product/pageQuery",
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
            				var product = result.data;
            				var products = product.datas;	
            				$.each(products, function(i, c){
            	                tableContent += '<tr>';
	          	                tableContent += '  <td>'+(i+1)+'</td>';
								tableContent += '  <td>'+c.name+'</td>';
								tableContent += '  <td>'+c.cname+'</td>';
								tableContent += '  <td>'+c.market_price+'</td>';
								tableContent += '  <td>'+c.shop_price+'</td>';
								tableContent += '  <td align="center"><img  width="50px" height="50px" src="'+c.image+'">'+'</td>';
								tableContent += '  <td>'+c.pdate+'</td>';
								tableContent += '  <td>'+(c.flag==1 ? "否":"是")+'</td>';
								tableContent += '  <td>'+c.pdesc+'</td>';	
								tableContent += '<td>';
								tableContent += '<button type="button" onclick="goUpdatePage('+c.id+')" data-toggle="modal" data-target="#updateModa" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i>修改</button>';
								if(c.flag==1){
									tableContent += '<button type="button" onclick="flagon('+c.id+','+c.flag+')" class="btn btn-warning btn-xs"><i class="glyphicon glyphicon-arrow-down"></i>下架</button>';
								}else{
									tableContent += '<button type="button" onclick="flagoff('+c.id+','+c.flag+')" class="btn btn-info btn-xs"><i class=" glyphicon glyphicon-arrow-up"></i>上架</button>';
								}				
	          					tableContent += '<button type="button" onclick="goDelete('+c.id+')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i>删除</button>';
								tableContent += '</td>';
								tableContent += '</tr>';
            				});
            				if ( pageno > 1 ) {
            					pageContent += '<li><a href="#" onclick="pageQuery('+(pageno-1)+')">上一页</a></li>';
            				}
            				
            				for ( var i = 1; i <= product.totalno; i++ ) {
            					if ( i == pageno ) {
            						pageContent += '<li class="active"><a  href="#">'+i+'</a></li>';
            					} else {
            						pageContent += '<li ><a href="#" onclick="pageQuery('+i+')">'+i+'</a></li>';
            					}
            				}
            				
            				if ( pageno < product.totalno ) {
            					pageContent += '<li><a href="#" onclick="pageQuery('+(pageno+1)+')">下一页</a></li>';
            				}
            				$("#productData").html(tableContent);
            				$(".pagination").html(pageContent);
            			} else {
                            layer.msg("查询失败", {time:2000, icon:5, shift:6}, function(){
                            	
                            });
            			}
            		}
            	});
            }
            function add(){
            	layer.confirm("确认要添加吗",  {icon: 3, title:'提示'}, function(cindex){
					layer.close(cindex);
					$("#addForm").submit();
				}, function(cindex){
					
				});
            	
            }
            
            /**
             * 下架方法
             * @param id
             * @returns
             */
            function flagon(id,flag){
            	var loadingIndex = null;
            	layer.confirm("确认要下架该商品吗",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    			    $.ajax({
    			    	type:"post",
    			    	url:path+"/product/flagonproduct",
    			    	data:{"id":id,"flag":flag},
    			    	beforeSend : function(){
                			loadingIndex = layer.msg('下架中', {icon: 16});
                		},
    			    	success:function (result){
    			    		layer.close(loadingIndex);
    			    		if(result.success){
    			    			pageQuery(1);
    			    		}else{
    			    			layer.msg("下架失败", {time:2000, icon:5, shift:6}, function(){
                                	
                                });
    			    		}
    			    		
    			    	}
    			    })
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
            /**
             * 上架方法
             */
             function flagoff(id,flag){
             	var loadingIndex = null;
             	layer.confirm("确认要上架该商品吗",  {icon: 3, title:'提示'}, function(cindex){
     			    layer.close(cindex);
     			    $.ajax({
     			    	type:"post",
     			    	url:path+"/product/flagoffproduct",
     			    	data:{"id":id,"flag":flag},
     			    	beforeSend : function(){
                 			loadingIndex = layer.msg('下架中', {icon: 16});
                 		},
     			    	success:function (result){
     			    		layer.close(loadingIndex);
     			    		if(result.success){
     			    			pageQuery(1);
     			    		}else{
     			    			layer.msg("下架失败", {time:2000, icon:5, shift:6}, function(){
                                 	
                                 });
     			    		}
     			    		
     			    	}
     			    })
     			}, function(cindex){
     			    layer.close(cindex);
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
		    
		    //删除方法
		    function goDelete(id){
            	layer.confirm("确认要删除吗？",  {icon: 3, title:'提示'}, function(cindex){
					layer.close(cindex);
					var loadingIndex = null;
			        $.ajax({
			        	type : "POST",
			        	url  : path+"/product/productdelete",
			        	data : {
			        		"id":id        
			        	},
			        	beforeSend : function(){
			        		loadingIndex = layer.msg('处理中', {icon: 16});
			        	},
			        	success : function(result) {
			        		layer.close(loadingIndex);	  
			        		if (result.success) {
								pageQuery(1);			
			        		}else{
								layer.msg("抱歉，删除失败", {time:2000, icon:2, shift:6}, function(){
			               
			                    });
			        		}
			        	}
			        });	
						
				}, function(cindex){
								
				});
            	
            }
            