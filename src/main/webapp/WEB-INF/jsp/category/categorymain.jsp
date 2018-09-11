<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>
  	<form class="form-inline" role="form" style="float:left;">
	  	<div class="form-group has-feedback">
	    	<div class="input-group">
		      	<div class="input-group-addon">查询条件</div>
		      		<input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
		    </div>
	  	</div>
  		<button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
  	</form>
	<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" id="add" data-toggle="modal" data-target="#Add"><i class=" glyphicon glyphicon-plus" ></i> 新增</button>
	<br>
 	<hr style="clear:both;">
    	<div class="table-responsive">
            <form id="categoryForm">
            <table class="table  table-bordered">
              <thead>
                <tr align="center">
                  <th width="30">#</th>
                  <th>名称</th>
                  <th>操作</th>
                </tr>
              </thead>             
              <tbody id="categoryData">             
              </tbody>             
			  <tfoot>
			     <tr >
				     <td colspan="3" align="center">
						<ul class="pagination">

						</ul>
					 </td>
				 </tr>
			  </tfoot>
            </table>
            </form>
  		</div>
  		
  		<!-- 添加静态框 -->
  		 <!-- 新增静态框 -->
  <div class="modal fade" id="Add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加商品</h4>
      </div>
      <div class="modal-body">
      	<form method="post"  enctype="multipart/form-data">
			<div class="form-group">
				<label for="exampleInputPassword1">名称</label>
				<input type="text" class="form-control"  id="name" placeholder="添加新的分类">
			</div>
		</form>
      </div>
      	<div class="modal-footer">
        	<button id="addclose" type="button" class="btn btn-default" data-dismiss="modal">返回</button>
        	<button type="button" onClick="add()" class="btn btn-primary">添加</button>
      	</div>
    	</div>
  		</div>
	</div>

  	<!-- 修改静态框 -->
  <div class="modal fade" id="updateModa" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">商品编辑</h4>
      </div>
      <div class="modal-body">
    	<form>
          <div class="form-group">
          	<input type="hidden" id="id"/>
          	  分类名称:<input type="text" name="name" class="form-control" id="updatename">
          </div>
        </form>
      </div>
      <input type="hidden" id="updateid">
      <div class="modal-footer">
        <button id="updateclose" type="button" class="btn btn-default" data-dismiss="modal">返回</button>
        <button type="button" class="btn btn-primary" id="update">修改</button>
      </div>
    </div>
  </div>
</div>
<input type="hidden" id="path" value="${pageContext.request.contextPath }" />
    <script src="${pageContext.request.contextPath }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/script/docs.min.js"></script>
	<script src="${pageContext.request.contextPath }/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath }/js/categorymain.js" ></script>
  </body>
</html>