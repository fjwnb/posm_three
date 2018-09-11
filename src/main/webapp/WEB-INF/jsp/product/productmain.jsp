<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
            <form id="userForm">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th width="120px">商品名称</th>
                  <th width="80px">商品类别</th>
                  <th width="65px">市场价</th>
                  <th width="65px">售价</th>
                  <th width="75px">图片</th>
                  <th width="100px">日期</th>
                  <th width="50px">下架</th>
                  <th width="280px">描述</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody id="productData">             
              </tbody>             
			  <tfoot>
			     <tr >
				     <td colspan="10" align="center">
						<ul class="pagination">

						</ul>
					 </td>
				 </tr>
			  </tfoot>
            </table>
            </form>
  </div>
  <!-- 新增静态框 -->
  <div class="modal fade" id="Add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加商品</h4>
      </div>
      <div class="modal-body">
      	<form id="addForm" action="${pageContext.request.contextPath }/product/upload" method="post"  enctype="multipart/form-data">
				  <div class="form-group">
					<label for="exampleInputPassword1">选择分类</label>
					<select id="cid" name="cid" class="form-control">	
						
					</select>
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">商品名称</label>
					<input type="text" class="form-control"  name="name" placeholder="请输入商品名称">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">市场价</label>
					<input type="text" class="form-control"  name="market_price" placeholder="请输入市场价">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">售价</label>
					<input type="text" class="form-control"  name="shop_price" placeholder="请输入售价">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">图片</label>
					<input type="file" class="form-control"  name="file" placeholder="请选择图片">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">日期</label>
					<input type="date" class="form-control" name="pdate"  >
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">描述</label>
					<input type="text" class="form-control"  name="pdesc" placeholder="描述一下吧">
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
					<label for="exampleInputPassword1">市场价</label>
					<input type="text" class="form-control"  id="umarket_price" placeholder="请输入市场价">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">售价</label>
					<input type="text" class="form-control"  id="ushop_price" placeholder="请输入售价">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">日期</label>
					<input type="date" class="form-control" id="update"  >
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">描述</label>
					<input type="text" class="form-control"  id="updesc" placeholder="描述一下吧">
				  </div>
		</form>
      </div>
      <input type="hidden" id="updateid">
      <div class="modal-footer">
        <button id="updateclose" type="button" class="btn btn-default" data-dismiss="modal">返回</button>
        <button type="button" class="btn btn-primary" id="updateproduct">修改</button>
      </div>
    </div>
  </div>
</div>
<input type="hidden" id="path" value="${pageContext.request.contextPath }"/>
    <script src="${pageContext.request.contextPath }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/script/docs.min.js"></script>
	<script src="${pageContext.request.contextPath }/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/productmain.js"></script>
	
  </body>
</html>