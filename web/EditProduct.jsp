<%-- 
    Document   : EditProduct
    Created on : Jun 17, 2022, 12:43:34 AM
    Author     : huynh
--%>
<%@page import="entity.Account"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Account account = (Account) request.getSession().getAttribute("account");
    if(account == null || account.getIsSeller() != 1){
        request.setAttribute("alert", "Bạn không có quyền truy cập vào trang này!");
        request.getRequestDispatcher("home-controller").forward(request, response);
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Edit Product Management</title>
<LINK REL="SHORTCUT ICON"  HREF="Flashglasses.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
body {
    color: #566787;
    background: #f5f5f5;
    font-family: 'Varela Round', sans-serif;
    font-size: 13px;
}

.table-wrapper {
    min-width: 1000px;
    background: #fff;
    padding: 20px 25px;
    border-radius: 3px;
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.table-title {
    padding-bottom: 15px;
    background: #299be4;
    color: #fff;
    padding: 16px 30px;
    margin: -20px -25px 10px;
    border-radius: 3px 3px 0 0;
}
.table-title h2 {
    margin: 5px 0 0;
    font-size: 24px;
}

</style>

</head>
<body>
<div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-5">
                        <h2>Trang Chỉnh Sửa Sản Phẩm</h2>
                    </div>
                </div>
            </div>
                        
                    <div id="editProductModal">
            <div class="modal-dialog">
		<div class="modal-content">
                    <form action="edit-product" method="get">
			<div class="modal-header">						
				<h4 class="modal-title">Chỉnh sửa sản phẩm</h4>
			</div>
			<div class="modal-body">	
                                <div class="form-group">
                                    <label>ID</label>
                                    <input value="${detail.id}" name="id" readonly class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Tên sản phẩm</label>
                                    <input value="${detail.name}" name="name" type="text" class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Hình ảnh</label>
                                    <input value="${detail.image}" name="image" type="text" class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Giá</label>
                                    <input value="${(detail.price)}" name="price" type="text" class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Tiêu đề</label>
                                    <input value="${detail.title}" name="title" type="text" class="form-control" required>
				</div>	
                            	<div class="form-group">
                                    <label>Số lượng</label>
                                    <input value="${detail.quantity}" name="quantity" type="text" class="form-control" required>
				</div>
                                <div class="form-group">
                                    <label>Loại mặt hàng</label>
                                        <select name="category" class="form-select" aria-label="Default select example">
                                            <c:forEach items="${listCategory}" var="cate">
                                                <option ${detail.category == cate.cateID ? "selected" : ""} value="${cate.cateID}">${cate.cateName}</option>
                                            </c:forEach>
                                        </select>
                                                        
				</div>
                          
			</div>
                            <div class="modal-footer">
				<input type="submit" class="btn btn-info" value="Lưu">
                            </div>
                    </form>
		</div>
            </div>
	</div>
                <a href="product-manager" class="btn btn-secondary"><span>Quay lại</span></a>
        </div>
                                
    </div>
    
</div>     
</body>
</html>
