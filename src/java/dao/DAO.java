/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Account;
import entity.Cart;
import entity.Category;
import entity.Product;
import entity.Purchase;
import entity.productCart;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trinh
 */
public class DAO implements DBContext {

    public static Connection getConnect(){
        try{ 
            Class.forName(DRIVERNAME); 
	} catch(ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
	}
        try{            
            Connection con = DriverManager.getConnection(DBURL,USERDB,PASSDB);
            return con;
        }
        catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select * from product order by pid";
        
        try (Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "select * from account";
        
        try (Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)));
            }
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where cid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, cid);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Integer> getProductByProductCartId(String cid) {
        List<Integer> list = new ArrayList<>();
        String query = "select pid from productCart\n"
                + "where cartid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, cid);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
               int pid = rs.getInt("pid");
               
               list.add(pid);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Purchase> getPurchaseByID(int id) {
        List<Purchase> list = new ArrayList<>();
        String query = "select * from Purchase\n"
                + "where cartid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                list.add(new Purchase(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Purchase getPurchaseLastestByID(int id) {
       Purchase p = null;
        String query = "SELECT TOP 1 * FROM Purchase ORDER BY oid DESC";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                p = new Purchase(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public List<Purchase> getAllPurchase() {
        List<Purchase> list = new ArrayList<>();
        String query = "select * from Purchase";
        try (Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                list.add(new Purchase(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where pname like ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Product getProductByID(String id) {
        String query = "select * from product\n"
                + "where pid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Account getAccountByID(int id) {
        String query = "select * from account\n"
                + "where ID = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Category getCategoryByID(String id) {
        String query = "select * from category\n"
                + "where cid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                return new Category(rs.getInt(1),
                        rs.getString(2));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Cart geCartByID(int id) {
        String query = "select * from Cart\n"
                + "where id = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                return new Cart(rs.getInt(1),rs.getInt(2));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Cart geCartByCartID(int id) {
        String query = "select * from Cart\n"
                + "where cartid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                return new Cart(rs.getInt(1),rs.getInt(2));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<productCart> geProductCartByID(int id) {
        List<productCart> list = new ArrayList<>();
        String query = "select * from productCart\n"
                + "where cartid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                list.add(new productCart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4)));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public productCart geProductCartByPID(String id, String cartid) {
        String query = "select * from productCart\n"
                + "where pid = ? and cartid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, cartid);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                return new productCart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try (Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Product getLast() {
        String query = "select top 1 * from product\n"
                + "order by pid desc";
       try (Connection con=getConnect()) {
            PreparedStatement stmt=con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account login(String user, String pass) {
        String query = "select * from account\n"
                + "where username = ?\n"
                + "and password = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account checkAccountExist(String user) {
        String query = "select * from account\n"
                + "where [username] = ?\n";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteProduct(String pid) {
        String query = "delete from product\n"
                + "where pid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void deleteAccount(String id) {
//        String query = "delete from account\n"
//                + "where id = ?";
//        try (Connection con=getConnect()) {
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setString(1, id);
//            ps.executeUpdate();
//        } catch (Exception ex) {
//            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    

     public void deleteAccount(int pid) {
        String query = "delete from account\n"
                + "where id = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, pid);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteCategory(String pid) {
        String query = "delete from category\n"
                + "where cid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteItem(String pid, String cartId) {
        String query = "delete from productCart\n"
                + "where pid = ? and cartid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, pid);
            ps.setString(2, cartId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteProductCartByCartId(int cartId) {
        String query = "delete from productCart\n"
                + "where cartid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cartId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteCartByCartId(String cartId) {
        String query = "delete from Cart\n"
                + "where cartid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, cartId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteCartById(int id) {
        String query = "delete from Cart\n"
                + "where id = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deletePurchaseById(int id) {
        String query = "delete from Purchase\n"
                + "where cartid = ?";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void insertProduct(String name, String image, String price, String title, String description, String category) {
        String query = "insert into Product values (?, ?, ?, ?, ?, ?)";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setString(3, image);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertCart(int id) {
        String query = "insert into Cart values (?)";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertProductCart(int cartid, String pid) {
        String query = "insert into productCart values (?, ?, 1, 0)";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cartid);
            ps.setString(2, pid);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertOrder(int cartid, String address, String phone, double bill) {
        String query = "insert into Purchase values (?,?, 0, ?, ?)";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cartid);
            ps.setDouble(2, bill);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertAccount(String name, String username, String password, String role) {
        String query = "insert into Account values (?, ?, ?, ?)";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertAccountCustomer(String name, String username, String password) {
        String query = "insert into Account values (?, ?, ?, 1)";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertCategory(String name) {
        String query = "INSERT [dbo].[Category] \n"
                + "([cname])\n"
                + "VALUES(?)";
        try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editProduct(String name, String image, String price, String title,  String description, String category, String pid) {
        String query = "update product\n"
                + "set pname = ?,\n"
                + "[img] = ?,\n"
                + "price = ?,\n"
                + "title = ?,\n"
                + "[details] = ?,\n"
                + "cid = ?\n"
                + "where pid = ?";
       try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setString(7, pid);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editCategory(String name, String pid) {
        String query = "update category set [cname] = ? where [cid] = ?";
       try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, pid);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editStatus(String id) {
        String query = "update Purchase set [Status] = 1  where [oid] = ?";
       try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editStatusReady(String id) {
        String query = "update Purchase set [Status] = 2  where [oid] = ?";
       try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editStatusComplete(String id) {
        String query = "update Purchase set [Status] = 3  where [oid] = ?";
       try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editProductCart(int id) {
        String query = "update productCart set [Status] = 1  where [cartid] = ?";
       try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editAddAmount(String cartid, String pid) {
        String query = "update productCart set amount = amount + 1 where cartid = ? and pid = ?";
       try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, cartid);
            ps.setString(2, pid);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editMinusAmount(String cartid, String pid) {
        String query = "update productCart set amount = amount - 1 where cartid = ? and pid = ?";
       try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, cartid);
            ps.setString(2, pid);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editAccount(String name, String username, String password, String uid) {
        String query = "update account set [name] = ?, username = ? , password = ?  where id = ?";
       try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, uid);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editProfile(String name, String username, String uid) {
        String query = "update account set [name] = ?, username = ?   where id = ?";
       try (Connection con=getConnect()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, uid);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();

        for (Category o : listC) {
            System.out.println(o);
        }
    }
}
