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
			    

			    /**
		            *修改保存数据
		            *
		            */
		            $("#update").click(function(){
						layer.confirm("确认要修改吗",  {icon: 3, title:'提示'}, function(cindex){
							layer.close(cindex);
							var category=$("#updatename").val();
							if(category ==""){
								layer.msg("分类名称不能为空，请输入", {time:2000, icon:5, shift:6}, function(){
					            });
					        	return;
							}
							var loadingIndex = null;
							var id= $("#id").val();
					        $.ajax({
					        	type : "POST",
					        	url  : path+"/category/ajaxcategoryedit",
					        	data : {
					        		"name" : category,"id":id        
					        	},
					        	beforeSend : function(){
					        		loadingIndex = layer.msg('处理中', {icon: 16});
					        	},
					        	success : function(result) {
					        		layer.close(loadingIndex);	  
					        		if (result=="exist") {
										layer.msg("分类已存在", {time:2000, icon:5, shift:6}, function(){
					                    	
					                    });
					        		} else if(result=="true") {
										layer.msg("修改成功", {time:2000, icon:1, shift:6}, function(){
					                    	
					                    });
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
            		url  : path+"/category/pageQuery",
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
            				var category = result.data;
            				var categorys = category.datas;	
            				$.each(categorys, function(i, c){
            	                tableContent += '<tr align="center">';
	          	                tableContent += '  <td>'+(i+1)+'</td>';
								tableContent += '  <td width="70%">'+c.name+'</td>';
								tableContent += '<td width="20%">';
								tableContent += '<button type="button" onclick="goUpdatePage('+c.id+')" data-toggle="modal" data-target="#updateModa" class="btn btn-primary"><i class=" glyphicon glyphicon-pencil"></i>修改</button>';
	          					tableContent += '<button type="button" onclick="goDeletePage('+c.id+')" class="btn btn-danger"><i class=" glyphicon glyphicon-remove"></i>删除</button>';
								tableContent += '</td>';
	          	                tableContent += '</tr>';
            				});
            				if ( pageno > 1 ) {
            					pageContent += '<li><a href="#" onclick="pageQuery('+(pageno-1)+')">上一页</a></li>';
            				}
            				
            				for ( var i = 1; i <= category.totalno; i++ ) {
            					if ( i == pageno ) {
            						pageContent += '<li class="active"><a  href="#">'+i+'</a></li>';
            					} else {
            						pageContent += '<li ><a href="#" onclick="pageQuery('+i+')">'+i+'</a></li>';
            					}
            				}
            				
            				if ( pageno < category.totalno ) {
            					pageContent += '<li><a href="#" onclick="pageQuery('+(pageno+1)+')">下一页</a></li>';
            				}
            				$("#categoryData").html(tableContent);
            				$(".pagination").html(pageContent);
            			} else {
                            layer.msg("分类分页查询失败", {time:2000, icon:5, shift:6}, function(){
                            	
                            });
            			}
            		}
            	});
            }
            
            //添加
            function add(){
            	layer.confirm("确认要添加吗",  {icon: 3, title:'提示'}, function(cindex){
            		layer.close(cindex);
					var category=$("#name").val();
					if(category ==""){
						layer.msg("分类名称不能为空，请输入", {time:2000, icon:5, shift:6}, function(){
			            });
			        	return;
					}
					var loadingIndex = null;
			        $.ajax({
			        	type : "POST",
			        	url  : path+"/category/ajaxcategory",
			        	data : {
			        		"name" : category,	        
			        	},
			        	beforeSend : function(){
			        		loadingIndex = layer.msg('处理中', {icon: 16});
			        	},
			        	success : function(result) {
			        		layer.close(loadingIndex);	  
			        		if (result=="exist") {
								layer.msg("分类已存在", {time:2000, icon:5, shift:6}, function(){
			                    	
			                    });
			        		} else if(result=="true") {
			        			$("#addclose").click();
			        			pageQuery(1);
			        		}else{
								layer.msg("抱歉，添加失败", {time:2000, icon:2, shift:6}, function(){
 	
			                    });
			        		}
			        	}
			        });
				}, function(cindex){
					
				});
				
		     }
           
            //删除
            function goDeletePage(id){
            	layer.confirm("确认要删除吗？",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    			    var loadingIndex = null;
    			    $.ajax({
    			    	type:"post",
    			    	url:path+"/category/ajaxcategorydelete",
    			    	data:{"id":id},
    			    	beforeSend : function(){
			        		loadingIndex = layer.msg('处理中', {icon: 16});
			        	},
    			    	success:function(result){
    			    		layer.close(loadingIndex);
    			    		if (result=="erro") {
								layer.msg("抱歉，分类存在商品，无法删除", {time:2000, icon:5, shift:6}, function(){
			                    	
			                    });
			        		} else if(result=="true") {
								layer.msg("删除成功", {time:2000, icon:1, shift:6}, function(){
			                    	
			                    });
								pageQuery(1);							
			        		}else{
								layer.msg("抱歉，删除失败", {time:2000, icon:2, shift:6}, function(){
			               			
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
             		url:path+"/category/ajaxget",
             		data:{"id":id},
             		success:function(result){
             			var category=result.data;
             			$("#updatename").val(category.name);
             			$("#id").val(category.id);
             		}         		
             	});
             }