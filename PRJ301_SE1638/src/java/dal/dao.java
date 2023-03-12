/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import sun.security.util.Password;

/**
 *
 * @author 84961
 */
public class dao {

    private Connection con;
    private ArrayList<Product> product;
    private ArrayList<ProductSubcategory> category;
    private ArrayList<Cart> cart;
    private ArrayList<Location> location;
    private ArrayList<Order> order;
    private ArrayList<History> history;
    private ArrayList<Manager> manager;
    private ArrayList<ManagerDetail> managerdetail;
    private AccountUser user;
    public String status = "";

    public dao() {
        try {
            con = new DBContext().getConnection();
            status = "Ket noi thanh cong";
        } catch (Exception e) {
            status = "Ket noi that bai" + e.getMessage();
        }
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }

    public ArrayList<ProductSubcategory> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<ProductSubcategory> category) {
        this.category = category;
    }

    public ArrayList<Cart> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Cart> cart) {
        this.cart = cart;
    }

    public ArrayList<Location> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Location> location) {
        this.location = location;
    }

    public AccountUser getUser() {
        return user;
    }

    public void setUser(AccountUser user) {
        this.user = user;
    }

    public ArrayList<Order> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Order> order) {
        this.order = order;
    }

    public ArrayList<History> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<History> history) {
        this.history = history;
    }

    public ArrayList<Manager> getManager() {
        return manager;
    }

    public void setManager(ArrayList<Manager> manager) {
        this.manager = manager;
    }

    public ArrayList<ManagerDetail> getManagerdetail() {
        return managerdetail;
    }

    public void setManagerdetail(ArrayList<ManagerDetail> managerdetail) {
        this.managerdetail = managerdetail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void LoadProductById(String n) {
        product = new ArrayList<Product>();
        String sql = "select * from Product_HE160277 where SubcategoryID = " + n;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt(1);
                String Name = rs.getString(2);
                int Price = rs.getInt(3);
                int SubcategoryID = rs.getInt(4);

                String images = rs.getString(5);
                product.add(new Product(ProductID, Name, Price, SubcategoryID, images));
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
    }

    public void LoadProductEdit(int fid) {
        product = new ArrayList<Product>();
        String sql = "select * from Product_HE160277 where ProductID = " + fid;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt(1);
                String Name = rs.getString(2);
                int Price = rs.getInt(3);
                int SubcategoryID = rs.getInt(4);
                String images = rs.getString(5);
                product.add(new Product(ProductID, Name, Price, SubcategoryID, images));
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
    }

    public void LoadLocation(String userid) {
        location = new ArrayList<Location>();
        String sql = "select * from Location_HE160277 where UserID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String locationid = rs.getString(1);
                String userID = rs.getString(2);
                String address = rs.getString(3);
                location.add(new Location(locationid, userID, address));
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
    }

    public String LoadAddress(String locationid) {
        location = new ArrayList<Location>();
        String sql = "select * from Location_HE160277 where LocationID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, locationid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String address = rs.getString(3);
                return address;
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
        return null;
    }

    public String LoadHistory(String userid) {
        history = new ArrayList<History>();
//        String sql = "select name, Amount, Status from \n"
//                + "Cart_HE160277 c left join Product_HE160277 p on p.ProductID=c.ProductID where UserID = ?";
        String sql
                = "select Name, Amount, tt_order from \n"
                + "(select c.UserID,ProductID,Amount, c.Status tt_cart, o.Status tt_order \n"
                + "from Cart_HE160277 c left join Order_HE160277 o on o.OrderID=c.OrderID)tb left join Product_HE160277 p on p.ProductID=tb.ProductID\n"
                + "where tt_cart = '1' and "
                + "UserID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                int amount = rs.getInt(2);
                int tt = rs.getInt(3);
                history.add(new History(name, amount, tt));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void LoadLocation() {
        location = new ArrayList<Location>();
        String sql = "select * from Location_HE160277";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String locationid = rs.getString(1);
                String userID = rs.getString(2);
                String address = rs.getString(3);
                location.add(new Location(locationid, userID, address));
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
    }

    public void ChangeCart(String userId, int a) {
        if (a == 1) {
            String sql = "update Cart_HE160277 set Status = '1' where UserID = ?";;
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                }
            } catch (Exception e) {
                status = "loi ket noi o Load Student " + e.getMessage();
            }
        } else {
            LoadOder();
            String sql = "update Cart_HE160277 set OrderID = ? where UserID = ? and Status = 'false'";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, order.size());
                ps.setString(2, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                }
            } catch (Exception e) {
                status = "loi ket noi o Load Student " + e.getMessage();
            }
        }
    }

    public void LoadCart(String userId, boolean status) {
        cart = new ArrayList<Cart>();
        String sql = "select UserID,c.ProductID,[Name], Price, Amount\n"
                + "from Cart_HE160277 c left join Product_HE160277 p on p.ProductID=c.ProductID where UserID = ? and Status = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setBoolean(2, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userid = rs.getString(1);
                int productid = rs.getInt(2);
                String name = rs.getString(3);
                int price = rs.getInt(4);
                int amount = rs.getInt(5);
                cart.add(new Cart(userid, productid, name, price, amount));
            }
        } catch (Exception e) {

        }
    }

    public void LoadDetail(int orderid) {
        managerdetail = new ArrayList<ManagerDetail>();
        String sql
                = "select Name, Price, Amount from \n"
                + "Cart_HE160277 c left join Product_HE160277 p on p.ProductID=c.ProductID\n"
                + "where Status = '1' and OrderID = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                int price = rs.getInt(2);
                int amount = rs.getInt(3);

                managerdetail.add(new ManagerDetail(name, price, amount));
            }
        } catch (Exception e) {

        }
    }

    public int LoadTotalCart() {
        int count = 0;
        String sql = "select * from Cart_HE160277";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
            return count;
        } catch (Exception e) {

        }
        return 0;
    }

    public void AddCart(String userId, int productId) {
        cart = new ArrayList<Cart>();
        int amount = 0;
        if (SearchCart(userId, productId)) {
            String sql1 = "select * from Cart_HE160277 where UserID = ? and ProductID = ? and Status ='false'";
            String sql = "update Cart_HE160277 set Amount =? where UserID = ? and ProductID = ? and Status = 'false'";
            try {
                PreparedStatement ps1 = con.prepareStatement(sql1);
                ps1.setString(1, userId);
                ps1.setInt(2, productId);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    amount = rs1.getInt(3);
//                    System.out.println(amount);
                }
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, amount + 1);
                ps.setString(2, userId);
                ps.setInt(3, productId);
                ps.execute();
            } catch (Exception e) {
                status = "Co loi khi Update" + e.getMessage();
            }
        } else {
            String sql = "insert into Cart_HE160277 values(?,?,?,?,?,?)";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, userId);
                ps.setInt(2, productId);
                ps.setInt(3, 1);
                ps.setBoolean(4, false);
                ps.setInt(5, 0);
                ps.setInt(6, LoadTotalCart() + 1);
                ps.execute();
            } catch (Exception e) {
                status = "Co loi khi Update" + e.getMessage();
            }
        }
    }

    public boolean SearchCart(String userId, int productId) {
        String a = "";
        int b = 0;
        String sql = "select * from Cart_HE160277 where UserID = ? and ProductID = ? and Status = 'false'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                a = rs.getString(1);
                b = rs.getInt(2);
            }
            if (a.equals("") && b == 0) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
        return false;
    }

    public void LoadProduct() {
        product = new ArrayList<Product>();
        String sql = "select * from Product_HE160277";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt(1);
                String Name = rs.getString(2);
                int Price = rs.getInt(3);
                int SubcategoryID = rs.getInt(4);
                String images = rs.getString(5);
                product.add(new Product(ProductID, Name, Price, SubcategoryID, images));
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
    }

    public void LoadProductTop() {
        product = new ArrayList<Product>();
        String sql = "select tb.ProductID, Name, Price,SubcategoryID,images from\n"
                + "(select top 3 * from\n"
                + "(select ProductID, sum(Amount) Amount\n"
                + "from Cart_HE160277\n"
                + "group by ProductID\n"
                + ")tb order by Amount DESC)tb left join Product_HE160277 p on p.ProductID=tb.ProductID\n"
                + "\n"
                + "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt(1);
                String Name = rs.getString(2);
                int Price = rs.getInt(3);
                int SubcategoryID = rs.getInt(4);
                String images = rs.getString(5);
                product.add(new Product(ProductID, Name, Price, SubcategoryID, images));
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
    }

    public void LoadCategory() {
        category = new ArrayList<ProductSubcategory>();
        String sql = "select * from ProductSubcategory_HE160277";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int SubcategoryID = rs.getInt(1);
                String Name = rs.getString(2);

                category.add(new ProductSubcategory(SubcategoryID, Name));
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
    }

    public void LoadManager() {
        manager = new ArrayList<Manager>();
        String sql
                = "select Date, FullName, PhoneNumber, Location,OrderID, Status,Priority, tb.UserID from\n"
                + "(select o.UserID,LastName+' '+FirstName FullName, PhoneNumber, LocationID, OrderID, Status, Date,Priority from \n"
                + "Order_HE160277 o left join AccountUser_HE160277 a on a.UserID=o.UserID\n"
                + "where orderID != 0) tb left join Location_HE160277 l on l.LocationID=tb.LocationID";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String datetime = rs.getString(1);
                String fullname = rs.getString(2);
                String phonenumber = rs.getString(3);
                String locationm = rs.getString(4);
                int orderid = rs.getInt(5);
                String statusm = rs.getString(6);
                int priority = rs.getInt(7);
                String userid = rs.getString(8);
                manager.add(new Manager(datetime, fullname, phonenumber, locationm, orderid, statusm, priority, userid));
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
    }

    public void Insert(String userid, String username, String password, String lastname, String firstname, String phonenumber, String email) {
        String sql = "insert into AccountUser_HE160277 values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userid);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, lastname);
            ps.setString(5, firstname);
            ps.setInt(6, 0);
            ps.setInt(7, 0);
            ps.setString(8, phonenumber);
            ps.setString(9, email);
            ps.execute();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public void InsertLocation(String userid, String address) {
        LoadLocation();
        String sql = "insert into Location_HE160277 values(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, location.size() + "");
            ps.setString(2, userid);
            ps.setString(3, address);
            ps.execute();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public void InsertProduct(String name, int price, int cid, String images) {
        LoadProduct();
        String sql = "insert into Product_HE160277 values(?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
          
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setInt(3, cid);
            ps.setString(4, images);
            ps.execute();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public void InsertOrder(String locationid, String userid, String datetime) {
        LoadOder();
        String sql = "insert into Order_HE160277 values(?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, order.size() + 1);
            ps.setString(2, userid);
            ps.setString(3, locationid);
            ps.setString(4, datetime);
            ps.setBoolean(5, false);
            ps.execute();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public void LoadOder() {
        order = new ArrayList<Order>();
        String sql = "select * from Order_HE160277";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderid = rs.getInt(1);
                String userid = rs.getString(2);
                String locationid = rs.getString(3);
                String datetime = rs.getString(4);
                boolean status = rs.getBoolean(5);
                order.add(new Order(orderid, userid, locationid, datetime, status));
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }

    }

    public AccountUser LoadUser(String user1, String password1) {

        String sql = "select * from AccountUser_HE160277 where UserName = ? and Password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user1);
            ps.setString(2, password1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userid = rs.getString(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String lastname = rs.getString(4);
                String firstname = rs.getString(5);
                int manager = rs.getInt(6);
                int priority = rs.getInt(7);
                String phonenumber = rs.getString(8);
                String mail = rs.getString(9);
                user = new AccountUser(userid, username, password, lastname, firstname, manager, priority, phonenumber, mail);
                return user;
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
        return null;
    }

    public AccountUser LoadUser(String usernamec) {

        String sql = "select * from AccountUser_HE160277 where UserName = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usernamec);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userid = rs.getString(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String lastname = rs.getString(4);
                String firstname = rs.getString(5);
                int manager = rs.getInt(6);
                int priority = rs.getInt(7);
                String phonenumber = rs.getString(8);
                String email = rs.getString(9);
                user = new AccountUser(userid, username, password, lastname, firstname, manager, priority, phonenumber, email);
                return user;
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
        return null;
    }

    public int LoadPriority(String userid) {

        String sql = "select * from AccountUser_HE160277 where Userid = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(7);
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
        return 0;
    }

    public String CheckCA(String userca) {

        String sql = "select * from AccountUser_HE160277 where UserName = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userca);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString(2);
                return a;
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
        return null;
    }

    public String getEmail(String userca) {

        String sql = "select * from AccountUser_HE160277 where UserName = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userca);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString(9);
                return a;
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
        return null;
    }

    public String CheckEmail(String email) {

        String sql = "select * from AccountUser_HE160277 where Email = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString(9);
                return a;
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
        return null;
    }

    public int totalAccount() {
        int count = 1;
        String sql = "select * from AccountUser_HE160277";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
            return count;
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
        return 0;
    }

    public int TotalMoney(String userid) {
        String sql = "select sum(total)\n"
                + "from\n"
                + "(select Price*Amount total\n"
                + "from Cart_HE160277 c left join Product_HE160277 p \n"
                + "on p.ProductID=c.ProductID where UserID = ? and Status = 'false') tb";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int totalmoney = rs.getInt(1);
                return totalmoney;
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
        return 0;
    }

    public int FinalTotalMoney(int ordeid) {
        String sql
                = "select sum(Price*Amount)\n"
                + "from\n"
                + "(select Name, Price, Amount from \n"
                + "Cart_HE160277 c left join Product_HE160277 p on p.ProductID=c.ProductID\n"
                + "where Status = '1' and OrderID = ?)tb";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ordeid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int totalmoney = rs.getInt(1);
                return totalmoney;
            }
        } catch (Exception e) {
            status = "loi ket noi o Load Student " + e.getMessage();
        }
        return 0;
    }

    public void ChangeAmount(String btn, String userId, int productId) {
        int amount = 0;

        String sql = "update Cart_HE160277 set Amount =? where UserID = ? and ProductID = ?";
        String sql1 = "select * from Cart_HE160277 where UserID = ? and ProductID = ?";
        String sql2 = "delete from Cart_HE160277 where UserID = ? and ProductID = ?";
        try {
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setString(1, userId);
            ps1.setInt(2, productId);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                amount = rs1.getInt(3);
                System.out.println(amount);
            }
            PreparedStatement ps = con.prepareStatement(sql);
            if (btn.equals("tangsl")) {
                ps.setInt(1, amount + 1);
            }
            if (btn.equals("giamsl")) {
                ps.setInt(1, amount - 1);
            }
            if (amount == 1 && btn.equals("giamsl")) {
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ps2.setString(1, userId);
                ps2.setInt(2, productId);
                ps2.execute();
            }
            ps.setString(2, userId);
            ps.setInt(3, productId);
            ps.execute();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public void ChangeInfo(String position, String change, String userid) {
        String sql = "update AccountUser_HE160277 set " + position + " =? where UserID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, change);
            ps.setString(2, userid);
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public void ChangeInfoItem(String position, String change, int fid) {
        String sql = "update Product_HE160277 set " + position + " =? where ProductID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (position.equals("Price") || position.equals("SubcategoryID")) {
                ps.setInt(1, Integer.parseInt(change));
            } else {
                ps.setString(1, change);
            }
            ps.setInt(2, fid);
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public void ChangeStatus(int orderid, int sts) {
        String sql = "update Order_HE160277 set Status = ? where OrderID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, sts);
            ps.setInt(2, orderid);
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }
//    public int PinCode(){
//    int max = 9999;
//    int min = 1000;
//    return (int)(Math.random()*(max - min + 1) + min);
//    }

    public void ChangePriority(String userid, String th) {
        String sql = "update AccountUser_HE160277 set Priority = ? where UserID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (th.equals("1")) {          
                    ps.setInt(1, LoadPriority(userid) - 1);              
            }else{
                if(LoadPriority(userid)<-10){
                    ps.setInt(1, 0);
                }else{
                ps.setInt(1, LoadPriority(userid) + 1);
                }
            }
            ps.setString(2, userid);
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public void DeleteItem(int fid) {
        String sql = "delete from Product_HE160277 where ProductID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, fid);
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public void DeleteOrder(int oid) {
        String sql = "delete from Order_HE160277 where OrderID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, oid);
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public void DeleteCart(int oid) {
        String sql = "delete from Cart_HE160277 where OrderID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, oid);
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public void DeleteLocation(String lid) {
        String sql = "delete from Location_HE160277 where LocationID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, lid);
            ResultSet rs = ps.executeQuery();
        } catch (Exception e) {
            status = "Co loi khi Update" + e.getMessage();
        }
    }

    public static String getRandom() {
        Random rd = new Random();
        int number = rd.nextInt(999999);
        return String.format("%06d", number);
    }

    public void SendMail(String to, String sub, String msg, String user, String pass) {
        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        javax.mail.Session session = javax.mail.Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void SendEmail(String pin) {
//        String subject = "dfdd";
//        String message = "<!DOCTYPE html>\n"
//                + "<html>\n"
//                + "    <head>\n"
//                + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
//                + "        <title>JSP Page</title>\n"
//                + "    </head>\n"
//                + "    <body>\n"
//                + pin
//                + "    </body>\n"
//                + "</html>\n"
//                + "";
//        SendMail("thanhtanh180@gmail.com", subject, message, "thanhnche160277@fpt.edu.vn", "we123456");
//
//    }
    public String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

class Using {

    public static void main(String[] args) {
        dao d = new dao();
//        String pass = d.getMd5("1");
//        System.out.println(d.LoadUser("thanh",d.getMd5("123")));
        
        d.ChangeStatus(2, -1);
        System.out.println();

//        }
    }
}
