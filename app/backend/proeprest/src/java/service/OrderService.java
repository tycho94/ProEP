/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.Item;
import model.Order;

/**
 * @author Amir
 **/
public class OrderService {

    List<Order> orderList = new ArrayList<>();
    
    public OrderService() {
        orderList.add(new Order(0, "Tycho"));
        orderList.add(new Order(1, "Tycho"));
        Order ord = new Order(2, "Tom");
        orderList.add(ord);
    }

    public boolean updateOrder(Order or) {
        for (Order order : orderList) {
            if (order.getID() == or.getID()) {
                order = or;
                return true;
            }
        }
        return false;
    }

    public boolean AddOrder(Order ord) {
        for (Order order : orderList) {
            if (order.getID() == ord.getID()) {
                return false;
            }
        }
        orderList.add(ord);
        return true;
    }
}
