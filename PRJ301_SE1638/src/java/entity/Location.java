/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author 84961
 */
public class Location {
    private String LocationID;
    private String UserID;
    private String Location;

    public Location() {
    }

    public Location(String LocationID, String UserID, String Location) {
        this.LocationID = LocationID;
        this.UserID = UserID;
        this.Location = Location;
    }

    public String getLocationID() {
        return LocationID;
    }

    public void setLocationID(String LocationID) {
        this.LocationID = LocationID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }
    
}
