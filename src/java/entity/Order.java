/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author huynh
 */
public class Order extends Product{
    private int orderID;
    private int uID;
    private int pID;
    private int quantities;
    private String date;

    public Order() {
    }

    public Order(int orderID, int uID, int pID, int quantities, String date) {
        super();
        this.orderID = orderID;
        this.uID = uID;
        this.pID = pID;
        this.quantities = quantities;
        this.date = date;
    }

    public Order(int uID, int pID, int quantities, String date) {
        this.uID = uID;
        this.pID = pID;
        this.quantities = quantities;
        this.date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", uID=" + uID + ", pID=" + pID + ", quantities=" + quantities + ", date=" + date + '}';
    }


    
}
