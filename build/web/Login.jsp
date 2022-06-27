<%-- 
    Document   : h
    Created on : Jun 12, 2022, 1:36:28 AM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="//cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <style>
            body{
                background-color: #000;
            }

            .card{
                width: 400px;
                border:none;
            }

            .btr{
                border-top-right-radius: 5px !important;
            }

            .btl{
            border-top-left-radius: 5px !important;
            }

            .btn-dark {
            color: #fff;
            background-color: #0d6efd;
            border-color: #0d6efd;
            }

            .btn-dark:hover {
            color: #fff;
            background-color: #0d6efd;
            border-color: #0d6efd;
            }

            .nav-pills{
            display:table !important;
            width:100%;
            }

            .nav-pills .nav-link {
            border-radius: 0px;
            border-bottom: 1px solid #0d6efd40;
            }

            .nav-item{
            display: table-cell;
            background: #0d6efd2e;
            }
            
            .form{
            padding: 10px;
            height: 300px;
            }

            .form input{
            margin-bottom: 12px;
            border-radius: 3px;
            }

            .form input:focus{
            box-shadow: none;
            }
            
            .form button{
            margin-top: 20px;
            }   
            
            .text-danger{
                text-align: center;
            }
            
            .text-success{
                color: green;
                text-align: center;
            }
            
         </style>

        <!--hiện popup báo thêm vào giỏ thành công-->
        <% String message = (String)request.getAttribute("alert");%>
        <!--nếu có nội dung trong alert thì mới hiện-->
        <% if(message != null) { %>
        <script type="text/javascript">
        var msg = "<%=message%>";
        alert(msg);
        </script>
        <% } %>
        
    </head>
    
    <body>
        <div class="d-flex justify-content-center align-items-center mt-5">

        <div class="card">

            <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                <li class="nav-item text-center">
                  <a class="nav-link active btl" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Login</a>
                </li>
                <li class="nav-item text-center">
                  <a class="nav-link btr" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Signup</a>
                </li>
            </ul>
                   
              <div class="tab-content" id="pills-tabContent">
                
                <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                <form class="form-login" action="login" method="post"> 
                  <div class="form px-4 pt-5">
                      
                    <input type="text" name="user" class="form-control" placeholder="Username">
                    
                    <input type="password" name="pass" class="form-control" placeholder="Password">
                    
                    <button class="btn btn-success btn-block" type="submit">Login</button>
                                <h6 class="text-danger">${error1}</h6> <!--hiển thị lỗi khi nhập sai account-->
                  </div>
                </form>
                </div>
                
                <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
                  <form class="form-signin" action="signup" method="post"> 
                  <div class="form px-4">

                    <input type="text" name="user" class="form-control" placeholder="Username">

                    <input type="text" name="phone" class="form-control" placeholder="Phone Number">

                    <input type="password" name="pass" class="form-control" placeholder="Password">
                    
                    <input type="password" name="repass" class="form-control" placeholder="Repeat Password">

                    <button class="btn btn-dark btn-block">Signup</button>
                                <h6 class="text-danger">${error2}</h6> <!--chỉ hiển thị khi đăng ký KHÔNG thành công-->
                                <h6 class="text-success">${message}</h6> <!--chỉ hiển thị khi đăng ký thành công-->
                  </div>


                  </form>
                </div>
                
               </div>
            
          
          

        </div>
        

      </div>
    </body>
</html>
