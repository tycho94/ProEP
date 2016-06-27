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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Version;
import model.*;

/**
 *
 * @author Amir
 */
public class Database {

    //public static void main(String[] args) {    }
    private Connection c;

    private PreparedStatement getAllUsers,
            getUserByName,
            getPassword,
            createUser,
            updateUser,
            getAddressByID,
            getAddressIDByAddress,
            createAddress;

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

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList GetAllRestaurant(String myQuery) throws SQLException {

        Statement st = null;
        Statement st2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ArrayList<Restaurant> query = new ArrayList<>();
        try {
            st = (Statement) c.createStatement();
            st2 = (Statement) c.createStatement();
            rs = st.executeQuery(myQuery);
            while (rs.next()) {

                int restaurant_ID = Integer.parseInt(rs.getString(1));
                String restaurant_Name = rs.getString(2);
                String password = rs.getString(3);
                int addressID = Integer.parseInt(rs.getString(4));
                //inner querry
                rs2 = st2.executeQuery("SELECT * FROM address WHERE Address_ID = '" + addressID + "' ");
                rs2.next();
                String city = rs2.getString(2);
                String street = rs2.getString(3);
                int houseNumber = Integer.parseInt(rs2.getString(4));
                String addition = rs2.getString(5);
                Address address = new Address(city, street, houseNumber, addition);
                //end of inner query
                String email = rs.getString(5);
                String phoneNumber = rs.getString(6);
                System.out.print(restaurant_Name);
                query.add(new Restaurant(restaurant_ID, restaurant_Name, password, email, phoneNumber, address));

            }

        } finally {
            try {

                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (c != null) {
                    c.close();
                }
                if (rs2 != null) {
                    rs2.close();

                }
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class
                        .getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }

        return query;
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
    public ArrayList GetAllItems(String myQuery) throws SQLException {

        Statement st = null;
        ResultSet rs = null;

        ArrayList<Item> itemList = new ArrayList<>();

        try {
            st = (Statement) c.createStatement();
            rs = st.executeQuery(myQuery);
            while (rs.next()) {

                int product_ID = Integer.parseInt(rs.getString(1));
                int restaurant_ID = Integer.parseInt(rs.getString(2));
                String name = rs.getString(3);
                int price = Integer.parseInt(rs.getString(4));
                int bid = Integer.parseInt(rs.getString(5));

                System.out.print(name);
                itemList.add(new Item(product_ID, restaurant_ID,
                        name, price, bid));
            }

        } finally {

            try {

                if (rs != null) {

                    rs.close();
                }
                if (st != null) {

                    st.close();
                }
                if (c != null) {

                    c.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }

        return itemList;
    }
}
