# DemoWebApp
Đây là demo đơn giản về web bán quần áo. Gồm 3 role: 
- Admin: Có chức năng thêm, xóa, sửa tài khoản của trang web
- Seller: Bán hàng, có thể thêm, xóa, sửa sản phẩm
- User: Người mua hàng, có thể thêm sản phẩm vào giỏ và thanh toán

File SQLDatabse.sql là của SQL Server2019, chứa script để tạo bảng. Trước khi chạy script thì phải tạo 1 database tên WebApp. 
Chú ý: portNumber trong class DBConnect.java là 1500, khác với cổng mặc định là 1433. Có thể sẽ phải thay đổi số cổng trong class này hoặc đổi trong phần config của SQL Server
