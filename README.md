# DemoWebApp

• Đây là demo đơn giản về web shop bán quần áo bằng Java. Gồm 3 role: 
- Admin: Có chức năng thêm, xóa, sửa tài khoản của trang web
- Seller: Bán hàng, có thể thêm, xóa, sửa sản phẩm
- User: Người mua hàng, có thể thêm sản phẩm vào giỏ và thanh toán

• File SQLDatabse.sql là của SQL Server2019, chứa script để tạo bảng. Trước khi chạy script thì phải tạo 1 database tên WebApp.
• Chú ý: portNumber trong class DBConnect.java là 1500, khác với cổng mặc định là 1433. Có thể sẽ phải thay đổi số cổng trong class này hoặc đổi trong phần config của SQL Server.
• Project được code và build trên Netbean IDE 8.2, phiên bản Java 1.8.0_45.
• Các library đã được import trong project : sqljdbc4.2.0 (dùng để kết nối Java với SQL Server) và jst1.2 (dùng để load dữ liệu từ SQL lên các trang JSP).


Created by Phước
