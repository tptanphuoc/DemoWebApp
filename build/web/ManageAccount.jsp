<%-- 
    Document   : ManageProduct
    Created on : Jun 14, 2022, 6:42:47 PM
    Author     : huynh
--%>

<%@page import="entity.Account"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Account account = (Account) request.getSession().getAttribute("account");
    if(account == null || account.getIsAdmin() != 1){
        request.setAttribute("alert", "Bạn không có quyền truy cập vào trang này!");
        request.getRequestDispatcher("home-controller").forward(request, response);
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Accounts Management Table</title>
<LINK REL="SHORTCUT ICON"  HREF="Flashglasses.png">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

        
        <% String message = (String)request.getAttribute("alert");%>
        
        <% if(message != null) { %>
        <script type="text/javascript">
        var msg = "<%=message%>";
        alert(msg);
        </script>
        <% } %>

<style>
    
body {
    color: #566787;
    background: #f5f5f5;
    font-family: 'Varela Round', sans-serif;
    font-size: 13px;
}
.table-responsive {
    margin: 30px 0;
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
.table-title .btn {
    color: #566787;
    float: right;
    font-size: 13px;
    background: #fff;
    border: none;
    min-width: 50px;
    border-radius: 2px;
    border: none;
    outline: none !important;
    margin-left: 10px;
}
.table-title .btn:hover, .table-title .btn:focus {
    color: #566787;
    background: #f2f2f2;
}
.table-title .btn i {
    float: left;
    font-size: 21px;
    margin-right: 5px;
}
.table-title .btn span {
    float: left;
    margin-top: 2px;
}
table.table tr th, table.table tr td {
    border-color: #e9e9e9;
    padding: 12px 15px;
    vertical-align: middle;
}
table.table tr th:first-child {
    width: 60px;
}
table.table tr th:last-child {
    width: 100px;
}
table.table-striped tbody tr:nth-of-type(odd) {
    background-color: #fcfcfc;
}
table.table-striped.table-hover tbody tr:hover {
    background: #f5f5f5;
}
table.table th i {
    font-size: 13px;
    margin: 0 5px;
    cursor: pointer;
}	
table.table td:last-child i {
    opacity: 0.9;
    font-size: 22px;
    margin: 0 5px;
}
table.table td a {
    font-weight: bold;
    color: #566787;
    display: inline-block;
    text-decoration: none;
}
table.table td a:hover {
    color: #2196F3;
}
table.table td a.settings {
    color: #2196F3;
}
table.table td a.delete {
    color: #F44336;
}
table.table td i {
    font-size: 19px;
}
table.table .avatar {
    border-radius: 50%;
    vertical-align: middle;
    margin-right: 10px;
}
.status {
    font-size: 30px;
    margin: 2px 2px 0 0;
    display: inline-block;
    vertical-align: middle;
    line-height: 10px;
}
.text-success {
    color: #10c469;
}
.text-info {
    color: #62c9e8;
}
.text-warning {
    color: #FFC107;
}
.text-danger {
    color: #ff5b5b;
}
.pagination {
    float: right;
    margin: 0 0 5px;
}
.pagination li a {
    border: none;
    font-size: 13px;
    min-width: 30px;
    min-height: 30px;
    color: #999;
    margin: 0 2px;
    line-height: 30px;
    border-radius: 2px !important;
    text-align: center;
    padding: 0 6px;
}
.pagination li a:hover {
    color: #666;
}	
.pagination li.active a, .pagination li.active a.page-link {
    background: #03A9F4;
}
.pagination li.active a:hover {        
    background: #0397d6;
}
.pagination li.disabled i {
    color: #ccc;
}
.pagination li i {
    font-size: 16px;
    padding-top: 6px
}
.hint-text {
    float: left;
    margin-top: 10px;
    font-size: 13px;
}
.avatar{
    height: 60px; 
    width: 60px;
}
</style>
<script>

</script>
</head>
<body>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-5">
                        <h2>Trang Quản Lý Tài Khoản</h2>
                    </div>
                    <div class="col-sm-7">
                        <a data-target="#addAccountModal" class="btn btn-secondary" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Thêm tài khoản mới</span></a>
                        
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên tài khoản</th>						
                        <th>Số điện thoại</th>
                        <th>Mật khẩu</th>
                        <th>Quyền quản trị</th>
                        <th>Quyền bán hàng</th>
                        <th>Thiết lập</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listAccounts}" var="a">
                    <tr>
                        <td>${a.accID}</td>
                        <td>${a.accName}</td>
                        <td>${a.phoneNum}</td>                        
                        <td>${a.accPassword}</td>
                        <td>${a.isAdmin}</td>
                        <td>${a.isSeller}</td>
                        <td>
                            <a href="account-info?aid=${a.accID}" class="settings" title="Sửa tài khoản" data-toggle="tooltip"><i class="material-icons">&#xE8B8;</i></a>
                            <a href="deleteaccount?aid=${a.accID}" class="delete" title="Xóa tài khoản" data-toggle="tooltip" onclick="return confirm('Bạn có chắc muốn xóa tài khoản: ${a.accName}')"><i class="material-icons">&#xE5C9;</i></a>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="home-controller" class="btn btn-secondary"><span>Quay lại trang chủ</span></a>
        </div>
        
    </div>
    
</div>
        <!-- Thêm account mới -->
    	<div id="addAccountModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="add-account" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Thêm tài khoản</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Tên tài khoản</label>
							<input name="username" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Số điện thoại</label>
							<input name="phone" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Mật khẩu</label>
							<input name="pass" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Quyền quản trị (0:Không/1:Có)
							<input name="admin" type="text" class="form-control" required>
                                                        Quyền bán hàng (0:Không/1:Có)
							<input name="seller" type="text" class="form-control" required>
                                                        </label>
						</div>					
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Hủy">
						<input type="submit" class="btn btn-success" value="Thêm">
					</div>
				</form>
			</div>
		</div>
	</div>        
        
</body>
</html>