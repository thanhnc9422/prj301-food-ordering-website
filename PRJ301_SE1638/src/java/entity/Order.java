/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author 84961
 */
public class Order {
         private int OrderID;
         private String UserID;
         private String LocationID;
         private String DateTime;
         private boolean Status;  
        public Order(){
        }

    public Order(int OrderID, String UserID, String LocationID, String DateTime, boolean Status) {
        this.OrderID = OrderID;
        this.UserID = UserID;
        this.LocationID = LocationID;
        this.DateTime = DateTime;
        this.Status = Status;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getLocationID() {
        return LocationID;
    }

    public void setLocationID(String LocationID) {
        this.LocationID = LocationID;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    
   
}
