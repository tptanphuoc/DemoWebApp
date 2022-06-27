<%-- 
    Document   : EditAccount
    Created on : Jun 17, 2022, 12:44:23 AM
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
<title>Edit Account Management</title>
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
                        <h2>Trang Chỉnh Sửa Tài Khoản</h2>
                    </div>
                </div>
            </div>
                       
        <div id="editAccountModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="edit-account" method="post"> 
			<div class="modal-header">						
				<h4 class="modal-title">Chỉnh sửa tài khoản</h4>
			</div>
			<div class="modal-body">	
                                <div class="form-group">
                                    <label>ID</label>
                                    <input value="${detail.accID}" name="id" readonly class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Tên tài khoản</label>
                                    <input value="${detail.accName}" name="name" type="text" class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Số điện thoại</label>
                                    <input value="${detail.phoneNum}" name="phone" type="text" class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Mật khẩu</label>
                                    <input value="${detail.accPassword}" name="pass" type="text" class="form-control" required>
				</div>
				<div class="form-group">
                                    <label>Quyền quản trị (1: Có, 0: Không)</label>
                                    <input value="${detail.isAdmin}" name="isadmin" type="text" class="form-control" required>
				</div>	
                            	<div class="form-group">
                                    <label>Quyền bán hàng (1: Có, 0: Không)</label>
                                    <input value="${detail.isSeller}" name="isseller" type="text" class="form-control" required>
				</div>                                
			</div>
                            <div class="modal-footer">
				<input type="submit" class="btn btn-info" value="Lưu">
                            </div>
                    </form>
		</div>
            </div>
	</div>
                <a href="account-manager" class="btn btn-secondary"><span>Quay lại</span></a>
    </div>
                                
    </div>
    
</div>     
</body>
</html>
