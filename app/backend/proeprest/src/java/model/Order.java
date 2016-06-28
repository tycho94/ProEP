/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amir
 */
public class Order {

    private int ID;
    private String username;
    private List<Item> orderlist;

    public Order() {
        this.orderlist = new ArrayList<>();
    }

    public Order(int ID, String username) {
        this.ID = ID;
        this.username = username;
        this.orderlist = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<Item> getOrderlist() {
        return orderlist;
    }

    public void addItem(Item i) {
        orderlist.add(i);
    }

    public void setOrderlist(List<Item> orderlist) {
        this.orderlist = orderlist;
    }
}
