<%@page import="java.util.*"%>
<%@page import="view.DAO"%>
<%@page import="entity.Cart"%>
<%@page import="entity.Account"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% DecimalFormat dcf = new DecimalFormat("###,###,###");
    request.setAttribute("dcf", dcf);
%>
<%
    Account account = (Account) request.getSession().getAttribute("account");
    if (account == null) {
        request.setAttribute("alert", "Bạn chưa login!");
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if (cart_list != null) {
        DAO dao = new DAO();
        cartProduct = dao.getCartProducts(cart_list);
        request.setAttribute("cart_list", cart_list);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <LINK REL="SHORTCUT ICON"  HREF="Flashglasses.png">
        <title>E-Shopper</title>

        <!--Style + Bootstrap-->        
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <!--hiện popup báo thêm vào giỏ thành công-->
        <% String message = (String) request.getAttribute("alert");%>
        <!--nếu có nội dung trong alert thì mới hiện-->
        <% if (message != null) {%>
        <script type="text/javascript">
            var msg = "<%=message%>";
            alert(msg);
        </script>
        <% }%>

        <!--begin of menu-->
        <nav class="navbar navbar-expand-md bg-dark">
            <div class="container">
                <a class="navbar-brand" href="home-controller">E-Shopper</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <ul class="navbar-nav m-auto">

                        <c:if test="${sessionScope.account.isAdmin == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="account-manager">Quản lý tài khoản</a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.account.isSeller == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="product-manager">Quản lý sản phẩm</a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.account != null}">
                            <li class="nav-item">
                                <a class="nav-link" href="logout">Đăng xuất</a>
                            </li>
                            <li class="nav-item">
                                <a class="bg-info">Chào ${sessionScope.account.accName}</a> <!--Nếu đăng nhập thành công thì hiện chuỗi "Chào + userName"-->
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.account == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="Login.jsp">Đăng nhập</a>
                            </li>
                        </c:if>                       

                    </ul>

                    <form action="search" method="get" class="form-inline my-2 my-lg-0">
                        <div class="input-group input-group-sm">
                            <input name="name" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Tìm kiếm sản phẩm...">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                        <c:if test="${sessionScope.account.isAdmin == 0 and sessionScope.account.isSeller ==0}">
                            <a class="btn btn-white btn-sm ml-3" href="ShoppingCart.jsp">
                                <i class="fa fa-shopping-cart"></i> Giỏ hàng
                                <span class="badge badge-light">${cart_list.size()}</span>
                            </a>
                        </c:if>

                        <c:if test="${sessionScope.account.isAdmin == 0 and sessionScope.account.isSeller ==0}">
                            <a class="btn btn-white btn-sm ml-3" href="Order.jsp">
                                <i class="fa fa-list"></i> Đơn hàng

                            </a>
                        </c:if>
                    </form>
                </div>
            </div>
        </nav>        
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Ở đây có bán đồ đẹp hơn người yêu cũ của bạn</h1>
                <p class="lead text-muted mb-0">Tôi không tiếp cận với thời trang, thời trang tiếp cận tôi</p>
            </div>
        </section>
        <!--end of the menu-->

        <!--Định dạng body ở dưới-->
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <!--Định dạng toàn bộ sản phẩm-->
                    <div class="card bg-light mb-3">                        
                        <div class="card-header bg-info text-center text-uppercase"><i class="fa fa-star"></i>Các sản phẩm</div>
                        <ul class="list-group category_block">
                            <c:forEach items="${listCategory}" var="ca">
                                <li class="list-group-item text-uppercase ${ticked == ca.cateID ? "active" : ""}"><a href="categories?category-id=${ca.cateID}">${ca.cateName}</a></li>
                                </c:forEach>
                        </ul> 
                        <!--Định dạng kiểu sắp xếp sp-->
                        <div class="card-header bg-info text-center text-uppercase"><i class="fa fa-list"></i>Sắp xếp theo</div>
                        <ul class="list-group category_block">
                            <li class="list-group-item "><a href="ascending-order">Giá tăng dần</a></li>                                                                 
                            <li class="list-group-item "><a href="descending-order">Giá giảm dần</a></li>
                        </ul>
                        <!--Định dạng kiểu sắp xếp sp-->
                    </div>
                </div>                
                <div class="col-sm-9">
                    <h4 class="text-error">${error}</h4>
                    <div class="row">

                        <c:forEach items="${listProduct}" var="pd">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="${pd.image}" alt="Card image cap">
                                    <div class="card-body">
                                        <h5 class="card-title show_txt"><a href="detail?product-id=${pd.id}" title="Xem chi tiết">${pd.name}</a></h5>
                                        <p class="card-text show_txt">Số lượng: ${pd.quantity}</p>  
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">${dcf.format(pd.price)}đ</p>
                                            </div>
                                            <div class="col">
                                                <c:if test="${sessionScope.account.isAdmin == 0 and sessionScope.account.isSeller ==0 and sessionScope.account != null}">
                                                    <a href="cart-manager?id=${pd.id}" class="btn btn-success btn-block">Thêm vào giỏ hàng</a>
                                                </c:if>                     
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>                
            </div>
        </div>
        <!--Định dạng toàn bộ sản phẩm-->

        <!--Body ở dưới-->

        <!--Chân trang-->
        <footer class="text-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-lg-3 col-xl-3">
                        <h5>Thông tin liên hệ</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <i class="fab fa-facebook"></i>
                            <a href="https://www.facebook.com/hvtp2002">Huỳnh Phước</a>
                            <li><i class="fa fa-envelope mr-2"></i>phuochvtse160419@fpt.edu.vn</li>                            
                        </ul>
                    </div>
                    <div class="col-12 copyright mt-3">
                        <p class="float-left">
                            <a href="#">Lên đầu trang</a>
                        </p>
                    </div>
                </div>
            </div>
        </footer>
        <!--Chân trang-->

    </body>
</html>

