/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Amir
 */
public class Item {

    private int itemID;
    private String name;
    private int price;
    private Restaurant restaurant;

    public Item() {
    }

    public Item(int itemID, String name, int price, Restaurant restaurant) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }    
    
    public Item(int itemID, String name, int price) {
        this.itemID = itemID;
        this.name = name;
        this.price = price;
        this.restaurant = null;
    }

    public int getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    
    
    

}
