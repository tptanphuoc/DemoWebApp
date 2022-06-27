package view;

import connect.DBConnect;
import entity.Account;
import entity.Cart;
import entity.Category;
import entity.Order;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    
    public Connection con = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    public DAO() {
    }
    
    public DAO(Connection con){
        this.con = con;
    }


//đăng nhập    
    public Account login(String userName, String password) {
        Account acc= new Account();
        String query = "select * from account where userName = ? and password = ?";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("userPhone"),
                        rs.getString("password"),
                        rs.getInt("isAdmin"),
                        rs.getInt("isSeller"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
//đăng ký tài khoản
    public void signup(String userName, String phoneNumber, String password) {
        Account acc= new Account();
        String query = "insert into account (userName, userPhone, password, isAdmin, isSeller)\n"
                + "VALUES (?, ?, ?, 0, 0);";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, phoneNumber);
            ps.setString(3, password);
            ps.executeUpdate();
            
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    
//check xem tài khoản tồn tại trong sql chưa
    public Account checkAccount(String userName){
        Account acc= new Account();
        String query = "select * from account where userName = ?";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("userPhone"),
                        rs.getString("password"),
                        rs.getInt("isAdmin"),
                        rs.getInt("isSeller"));
            } 
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
     
//lấy 1 account theo id   
    public Account getAccountByID(int aID) {
        String query = "select * from account where userID = ?";   
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, aID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("userPhone"),
                        rs.getString("password"),
                        rs.getInt("isAdmin"),
                        rs.getInt("isSeller"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }    
    
//lấy tất cả account trong SQL
    public List<Account> getAllAccount() {
        List<Account> listAcc = new ArrayList<>();
        String query = "select * from account";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listAcc.add(new Account(rs.getInt("userID"),
                        rs.getString("userName"),
                        rs.getString("userPhone"),
                        rs.getString("password"),
                        rs.getInt("isAdmin"),
                        rs.getInt("isSeller")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listAcc;
    }
    
//lấy tất cả sản phẩm
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

//lấy tất cả loại mặt hàng
    public List<Category> getAllCategory() {
        List<Category> listc = new ArrayList<>();
        String query = "select * from category";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listc.add(new Category(rs.getInt("cateID"),
                        rs.getString("cateName")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listc;
    }

//lấy sản phẩm theo loại mặt hàng    
    public List<Product> getByCateID(int cid) {
        List<Product> listcid = new ArrayList<>();
        String query = "select * from product where cateID = ?";
                
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                listcid.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listcid;
    }
    
//lấy sản phẩm theo id của người bán
    public List<Product> getBySellerID(int sid) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product where sellerID = ?";                
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, sid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));
                    }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

//sắp xếp sp theo giá tăng dần
    public List<Product> getByAscPrice() {
        List<Product> listAsc = new ArrayList<>();
        String query = "select * from product\n order by price asc";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listAsc.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listAsc;
    }

//sắp xếp sp theo giá giảm dần
    public List<Product> getByDescPrice() {
        List<Product> listDesc = new ArrayList<>();
        String query = "select * from product\n order by price desc";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listDesc.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listDesc;
    }
    
//lấy 1 sp theo id   
    public Product getProductByID(int pID) {
        String query = "select * from product where id = ?";   
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, pID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
//lấy 1 sp theo id (trả về list)
    public List<Product> getListProductByID(int pID) {
        List<Product> listPro = new ArrayList<>();
        String query = "select * from product where id = ?";   
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, pID);
            rs = ps.executeQuery();
            while (rs.next()) {
                listPro.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listPro;
    }
    
//tìm sp theo tên
    public List<Product> getProductByName(String name) {
        List<Product> listPro = new ArrayList<>();
        String query = "select * from product where [name] like ?";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, "%"+name+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                listPro.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listPro;
    }
    
//xóa sản phẩm theo id
    public void deleteProductByID(int pID) {
        String query = "delete from product where id= ?";                
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, pID);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
//thêm sản phẩm
    public void insertProduct(String name, String image, String price, String title, int quantity, int cateID, int sellerID){
        String query = "INSERT INTO product([name], [image], [price], [title], [quantity], [cateID], [sellerID])\n"
                    + "VALUES (?,?,?,?,?,?,?)";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setInt(5, quantity);
            ps.setInt(6, cateID);
            ps.setInt(7, sellerID);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
                
    }
    
//sửa sản phẩm
    public void editProduct(int id, String name, String image, String price, String title, int quantity, int cateID){
        String query = "UPDATE product\n" +
            "set [name] = ?,\n" +
            "[image] = ?,\n" +
            "[price] = ?,\n" +
            "[title] = ?,\n" +
            "[quantity] = ?,\n" +
            "[cateID] = ?\n" +
            "where id = ?";               
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setInt(5, quantity);
            ps.setInt(6, cateID);
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
                
    }
    
//update lại số lượng sau khi mua
    public void updateQuantity(int id,int quantity){
        String query = "UPDATE product\n" +
            "set [quantity] = ?\n" +
            "where id = ?";               
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
                
    }    
    
    
//thêm account
    public void insertAccount(String name, String phone, String password, int isAd, int isSel){
        String query = "INSERT INTO account([userName], [userPhone], [password], [isAdmin], [isSeller])\n"
                    + "VALUES (?,?,?,?,?)";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, password);
            ps.setInt(4, isAd);
            ps.setInt(5, isSel);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
                
    }
    
//sửa account
    public void editAccount(int id, String name, String phone, String pass, int isAdmin, int isSeller){
        String query = "UPDATE account\n" +
            "set [userName] = ?,\n" +
            "[userPhone] = ?,\n" +
            "[password] = ?,\n" +
            "[isAdmin] = ?,\n" +
            "[isSeller] = ?\n" +
            "where userID = ?";              
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, pass);
            ps.setInt(4, isAdmin);
            ps.setInt(5, isSeller);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
                
    }
    
//xóa account theo id
    public void deleteAccByID(int aID) {
        String query = "delete from account where userID= ?";                
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, aID);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
//lấy giỏ hàng
    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> products = new ArrayList<>();
           
        try {
            if(cartList.size()>0){
                for(Cart item:cartList){
                    con = new DBConnect().getConnection();
                    String query = "select * from product where id = ?";
                    ps = con.prepareStatement(query);
                    ps.setInt(1, item.getId());
                    rs = ps.executeQuery();
                
               while (rs.next()) {
                    Cart row = new Cart();
                        row.setId(rs.getInt("id"));
                        row.setName(rs.getString("name"));
                        row.setImage(rs.getString("image"));
                        row.setPrice(rs.getInt("price"));
                        row.setTitle(rs.getString("title"));
                        row.setAmount(item.getAmount());
                        row.setCategory(rs.getInt("cateID"));
                        products.add(row);
            }
            }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }
    
//tính tổng tiền giỏ hàng
    public int totalCartPrice(ArrayList<Cart> cartList){
        int sum = 0;
        try {
            if(cartList.size() > 0){
                for(Cart item : cartList){
                    String query = "select price from product where id=? ";
                    ps = con.prepareStatement(query);
                    ps.setInt(1, item.getId());
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        sum += rs.getInt("price")*item.getAmount();
                        
                    }
                }
            }
        } catch (Exception e) {
        }
        return sum;
    }
    
//lấy số lượng sp trong SQL
    public int getQuantity(int id){
        int quantity = 0;
        String query = "select quantity from product where id=? ";
        try {            
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                quantity = rs.getInt("quantity");
                            }
                         
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    } 
    
//thêm vào đơn hàng
    public void insertOrder(Order order){
        String query = "INSERT INTO [order](uID, pID, oQuantity, oDate) VALUES(?,?,?,?) ";
        try {
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, order.getuID());
            ps.setInt(2, order.getpID());
            ps.setInt(3, order.getQuantities());
            ps.setString(4, order.getDate());
            rs = ps.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
//lấy danh sách đơn hàng đã đặt theo id của account    
        public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        String query = "select * from [order] where uID=? order by oID desc";
        try {    
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order orders = new Order();                 
                DAO dao = new DAO();            
                int pId = rs.getInt("pID");
                Product pro = dao.getProductByID(pId);
                
                orders.setOrderID(rs.getInt("oID"));
                orders.setId(pId);
                orders.setName(pro.getName());
                orders.setImage(pro.getImage());
                orders.setTitle(pro.getTitle());
                orders.setPrice(pro.getPrice()*rs.getInt("oQuantity"));
                orders.setQuantities(rs.getInt("oQuantity"));
                orders.setDate(rs.getString("oDate"));
                list.add(orders);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }
    
//test thử kếu quả
    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Order> list = new ArrayList<>();
        list=dao.userOrders(11);
        for(Order order: list){
            System.out.println(order);
        }
    }
}

