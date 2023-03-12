/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author 84961
 */
public class Manager {
    
    private String datetime;
    private String fullname;
    private String phonenumber;
    private String location;
    private int orderid;
    private String status;
    private int priority;
    private String userid;
    public Manager() {
    }

    public Manager(String datetime, String fullname, String phonenumber, String location, int orderid, String status, int priority, String userid) {
        this.datetime = datetime;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.location = location;
        this.orderid = orderid;
        this.status = status;
        this.priority = priority;
        this.userid = userid;
    }

   

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    
}
