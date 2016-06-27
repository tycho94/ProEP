/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import database.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author mikaeil
 */
public class RestaurantService {

    ArrayList<Restaurant> restaurants = new ArrayList<>();
    Database d;

    public RestaurantService() throws SQLException {
        this.d = new Database();
   }

    public Restaurant getRestaurantByID(int id) {
        for (Restaurant R : restaurants) {
            if (id == R.getRestaurantID()) {
                return R;
            }
        }
        return null;
    }

    public Restaurant getRestaurantByname(String res_name) {
        for (Restaurant R : restaurants) {
            if (res_name.equals(R.getName())) {
                return R;
            }
        }
        return null;
    }

    public List<Item> getRestaurantMenu(String res_name) {
        for (Restaurant R : restaurants) {
            if (res_name.equals(R.getName())) {
              //  return R.getMenu();
            }
        }
        return null;
    }


    public void createRestaurant(Restaurant R) {
        restaurants.add(R);
    }

}
