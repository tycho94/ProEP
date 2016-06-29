/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author mikaeil
 */
public class Restaurant {

    int restaurantID;
    String name;
    String password;
    String city;

    public Restaurant() {
    }

    public Restaurant(int resID, String resName, String password, String city) {
        this.restaurantID = resID;
        this.name = resName;
        this.password = password;
        this.city = city;
    }

    public void setRestaurantID(int resID) {
        this.restaurantID = resID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
