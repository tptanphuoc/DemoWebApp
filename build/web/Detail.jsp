<%@page import="entity.Account"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% DecimalFormat dcf = new DecimalFormat("###,###,###");
   request.setAttribute("dcf", dcf);
%>
<%
    Account account = (Account) request.getSession().getAttribute("account");
    if(account == null){
        request.setAttribute("alert", "Bạn chưa login!");
        request.getRequestDispatcher("Login.jsp").forward(request, response);
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

        <!-- Include the above in your HEAD tag -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>

        <!--Style CSS-->
        <style>
            .num{
                color: red;
            }

            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }

            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }

            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }

            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }           
        </style>
    </head>

    <body>
        <!--begin of menu-->
        <nav class="navbar navbar-expand-md bg-dark">
            <div class="container">
                <a class="navbar-brand" href="home-controller"> E-Shopper </a>
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

                            </a>
                        </c:if>
                    </form>
                </div>
            </div>
        </nav>
        <!--end of the menu-->

        <!--Định dạng tiêu đề shop-->
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading"> Ở đây có bán đồ đẹp hơn người yêu cũ của bạn </h1>
                <p class="lead text-muted mb-0"> Tôi không tiếp cận với thời trang, thời trang tiếp cận tôi </p>
            </div>
        </section>
        <!--Định dạng tiêu đề shop-->

        <!--Định dạng body ở dưới-->
        <div class="container">
            <div class="row">
                <!--Định dạng mục sản phẩm bên trái-->
                <div class="col-sm-3">                    
                    <div class="card bg-light mb-3">                        
                        <div class="card-header bg-info text-center text-uppercase"><i class="fa fa-star"></i> Các sản phẩm </div>
                        <ul class="list-group category_block">
                            <c:forEach items="${listCategory}" var="ca">
                                <li class="list-group-item text-uppercase ${ticked == ca.cateID ? "active" : ""}"><a href="categories?category-id=${ca.cateID}">${ca.cateName}</a></li>
                                </c:forEach>
                        </ul>           
                    </div>
                </div>  
                <!--Định dạng mục sản phẩm bên trái-->

                <!--Định dạng sản phẩm bên phải-->
                <div class="col-sm-9">
                    <div class="container">
                        <div class="card">
                            <div class="row">
                                <aside class="col-sm-5 border-right">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <div> <a href="#"><img src="${detail.image}"></a></div>
                                        </div>
                                        <div class="img-small-wrap">
                                        </div>
                                    </article>
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${detail.name}</h3>
                                        <hr>
                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="num">${dcf.format(detail.price)}đ</span>
                                            </span>                                            
                                        </p>
                                        <dl class="item-property">
                                            <dt> Loại sản phẩm: <dd>${detail.title}</dd></dt>
                                                                                            
                                        </dl>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt> Số lượng: <a>${detail.quantity}</a></dt>
                                                    
                                                </dl>
                                            </div>
                                        </div>
                                        <hr>
                                            <c:if test="${sessionScope.account.isAdmin == 0 and sessionScope.account.isSeller ==0 and sessionScope.account != null}">
                                                <a href="cart-manager?id=${detail.id}" class="btn btn-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng </a>
                                            </c:if>
                                    </article>
                                </aside>
                            </div>
                        </div>                    
                    </div>
                </div>
            </div> 
        </div>
        <!--Định dạng sản phẩm bên phải-->

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

