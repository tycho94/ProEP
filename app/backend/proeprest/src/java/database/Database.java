/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Version;
import model.*;

/**
 *
 * @author Amir
 */
public class Database {

    private Connection c;

    private PreparedStatement getAllUsers,
            getUserByName,
            getPassword,
            createUser,
            updateUser,
            getAddressByID,
            getAddressIDByAddress,
            createAddress,
            getItemByID,
            getItemByRestaurantID,
            getRestaurantByID,
            getRestaurantByName,
            getRestaurantsByCity,
            getOrderByID;

    public Database() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            c = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.15.56/dbi271837?",
                    "dbi271837",
                    "O3JUJwTWhi");

            //prepared statements
            //users
            getAllUsers = c.prepareStatement(
                    "select * from users");
            //used join to get both address & user in one statement
            getUserByName = c.prepareStatement(
                    "select * from users natural join address where username = ?");
            getPassword = c.prepareStatement(
                    "select username, password from users where username = ?");
            createUser = c.prepareStatement(
                    "insert into users (username, password, addressID, email) values (?,?,?,?)");
            updateUser = c.prepareStatement(
                    "update users set password=?, email=? where username = ?");
            //address
            createAddress = c.prepareStatement(
                    "insert into address (city, street, housenumber, addition) values (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            getAddressByID = c.prepareStatement(
                    "Select * from address where AddressID = ?");
            getAddressIDByAddress = c.prepareStatement(
                    "Select AddressID from address where city = ? and street = ? and housenumber = ? and addition = ?");
            //items
            getItemByID = c.prepareStatement(
                    "Select * from products natural join restaurant where product_id = ?");
            getItemByRestaurantID = c.prepareStatement(
                    "Select * from products where restaurant_id = ?");

            //restaurants
            getRestaurantByID = c.prepareStatement(
                    "Select * from restaurant where restaurant_id = ?");
            getRestaurantByName = c.prepareStatement(
                    "Select * from restaurant where restaurant_name = ?");
            getRestaurantsByCity = c.prepareStatement(
                    "Select * from restaurant where ResCity = ?");
            //Orders
            getOrderByID = c.prepareStatement(
                    "SELECT * FROM `order` WHERE `OrderID` = ?");

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Order getOrderByID(int id) {
        Order o = null;
        String[] items = null;
        try {
            getOrderByID.setInt(1, id);
            o = new Order();
            try (ResultSet rs = getOrderByID.executeQuery()) {
                while (rs.next()) {
                    o.setID(id);
                    o.setUsername(rs.getString("UserName"));
                    items = rs.getString("list").split("-");
                }
                getOrderByID.close();
            }

            for (String i : items) {
                getItemByID.setInt(1, Integer.parseInt(i));
                try (ResultSet rs = getItemByID.executeQuery()) {
                    while (rs.next()) {
                        o.addItem(
                                new Item(rs.getInt("product_id"),
                                        rs.getString("Name"),
                                        rs.getInt("price")));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            o = null;
        } finally {
            return o;
        }
    }

    public Restaurant getRestaurantByID(int id) {
        Restaurant r = null;
        try {
            getRestaurantByID.setInt(1, id);
            try (ResultSet rs = getRestaurantByID.executeQuery()) {
                while (rs.next()) {
                    r = new Restaurant(id,
                            rs.getString("Restaurant_Name"),
                            rs.getString("Pass"),
                            rs.getString("ResCity"));
                }
            }
            getRestaurantByID.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class
                    .getName()).log(Level.SEVERE, null, ex);
            r = null;
        } finally {
            return r;
        }
    }

    public Restaurant getRestaurantByName(String name) {
        Restaurant r = null;
        try {
            getRestaurantByName.setString(1, name);
            try (ResultSet rs = getRestaurantByName.executeQuery()) {
                while (rs.next()) {
                    r = new Restaurant(rs.getInt("Restaurant_ID"),
                            name,
                            rs.getString("Pass"),
                            rs.getString("ResCity"));
                }
            }
            getRestaurantByName.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class
                    .getName()).log(Level.SEVERE, null, ex);
            r = null;
        } finally {
            return r;
        }
    }

    public List<Restaurant> getRestaurantByCity(String city) {
        List<Restaurant> r = new ArrayList<>();
        try {
            getRestaurantsByCity.setString(1, city);
            try (ResultSet rs = getRestaurantsByCity.executeQuery()) {
                while (rs.next()) {
                    r.add(new Restaurant(rs.getInt("Restaurant_ID"),
                            rs.getString("Restaurant_Name"),
                            rs.getString("Pass"),
                            city));
                }
            }
            getRestaurantsByCity.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class
                    .getName()).log(Level.SEVERE, null, ex);
            r = null;
        } finally {
            return r;
        }
    }

    public Item GetItemByID(int id) {
        Item i = null;
        try {
            getItemByID.setInt(1, id);
            try (ResultSet rs = getItemByID.executeQuery()) {
                while (rs.next()) {
                    i = new Item(id, rs.getString("Name"),
                            rs.getInt("Price"),
                            new Restaurant(rs.getInt("Restaurant_ID"),
                                    rs.getString("Restaurant_Name"),
                                    rs.getString("Pass"),
                                    rs.getString("ResCity")));
                }
            }
            getItemByID.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            return i;
        }
    }

    public List<Item> GetItemsByRestaurantID(int id) {
        List<Item> i = new ArrayList<>();
        try {
            getItemByRestaurantID.setInt(1, id);
            try (ResultSet rs = getItemByRestaurantID.executeQuery()) {
                while (rs.next()) {
                    i.add(new Item(id, rs.getString("Name"),
                            rs.getInt("Price")));
                }
            }
            getItemByRestaurantID.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class
                    .getName()).log(Level.SEVERE, null, ex);
            i = null;
        } finally {
            return i;
        }
    }

    public User GetUserByName(String name) {
        User u = null;
        try {
            getUserByName.setString(1, name);
            try (ResultSet rs = getUserByName.executeQuery()) {
                while (rs.next()) {
                    u = new User(
                            rs.getString("UserName"),
                            rs.getString("Password"),
                            new Address(
                                    rs.getString("City"),
                                    rs.getString("Street"),
                                    rs.getInt("HouseNumber"),
                                    rs.getString("Addition")),
                            rs.getString("Email"));
                }
            }
            getUserByName.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            return u;
        }
    }

    public boolean UpdateUser(User u) {
        try {
            updateUser.setString(1, u.getPassword());
            updateUser.setString(2, u.getEmail());
            updateUser.setString(3, u.getUsername());
            updateUser.executeUpdate();
            updateUser.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            return true;
        }
    }

    public boolean CreateUser(User u) {
        int addressID = -1;
        try {
            try {
                //create address
                createAddress.setString(1, u.getAddress().getCity());
                createAddress.setString(2, u.getAddress().getStreet());
                createAddress.setInt(3, u.getAddress().getHousenumber());
                createAddress.setString(4, u.getAddress().getAddition());
                createAddress.executeUpdate();
                //get generated address id for user
                try (ResultSet rs = createAddress.getGeneratedKeys()) {
                    if (rs.next()) {
                        addressID = rs.getInt(1);
                    }
                }
                createAddress.close();
            } catch (SQLException ex) {
                getAddressIDByAddress.setString(1, u.getAddress().getCity());
                getAddressIDByAddress.setString(2, u.getAddress().getStreet());
                getAddressIDByAddress.setInt(3, u.getAddress().getHousenumber());
                getAddressIDByAddress.setString(4, u.getAddress().getAddition());
                ResultSet rs = getAddressIDByAddress.executeQuery();
                while (rs.next()) {
                    addressID = rs.getInt("AddressID");
                }
                getAddressIDByAddress.close();
            }
            //create user
            createUser.setString(1, u.getUsername());
            createUser.setString(2, u.getPassword());
            createUser.setInt(3, addressID);
            createUser.setString(4, u.getEmail());
            createUser.executeUpdate();
            createUser.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;

    }

    public int Login(String name, String pass) {
        String checkPass = null;
        try {
            getPassword.setString(1, name);
            try (ResultSet rs = getPassword.executeQuery()) {
                while (rs.next()) {
                    checkPass = rs.getString("Password");
                }
            }
            getPassword.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (checkPass != null) {
                if (checkPass.equals(pass)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return -1;
            }
        }
    }

    /* private Address GetAddressByID(int id) {
        Address a = null;
        try {
            getAddressByID.setInt(1, id);
            try (ResultSet rs = getAddressByID.executeQuery()) {
                while (rs.next()) {
                    a = new Address(
                            rs.getString("City"),
                            rs.getString("Street"),
                            rs.getInt("HouseNumber"),
                            rs.getString("Addition"));
                }
            }
            getAddressByID.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            return a;
        }
    }*/

 /* public List<User> GetAllUsers() {
        List<User> users = null;
        try {
            users = new ArrayList<>();
            ResultSet rs = getAllUsers.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getString("UserName"),
                        rs.getString("Password"),
                        GetAddressByID(rs.getInt("AddressID")),
                        rs.getString("Email")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                getAllUsers.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                return users;
            }
        }
    }*/
    //this is just a Temp Solution, i think, this function is slowing us down
    /////////////////////////////////////////////////////////////////////////
}
