/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author 84961
 */
public class AccountUser {
    private String UserID;
    private String UserName;
    private String Password;
    private String LastName;
    private String FirstName;
    private int Manager;
    private int priority;
    private String PhoneNumber;
    private String Email;

    public AccountUser(){
    }
    
    public AccountUser(String UserName, String Password) {
        this.UserName = UserName;
        this.Password = Password;
    }

    public AccountUser(String UserID, String UserName, String Password, String LastName, String FirstName, int Manager, int priority, String PhoneNumber, String Email) {
        this.UserID = UserID;
        this.UserName = UserName;
        this.Password = Password;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.Manager = Manager;
        this.priority = priority;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
    }

    
    
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public int getManager() {
        return Manager;
    }

    public void setManager(int Manager) {
        this.Manager = Manager;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
 
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

}
