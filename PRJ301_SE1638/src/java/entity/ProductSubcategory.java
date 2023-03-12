/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author 84961
 */
public class ProductSubcategory {

    private int SubcategoryID;
    private String Name;

    public ProductSubcategory() {
    }

    public ProductSubcategory(int SubcategoryID, String Name) {
        this.SubcategoryID = SubcategoryID;
        this.Name = Name;
    }

    public int getSubcategoryID() {
        return SubcategoryID;
    }

    public void setSubcategoryID(int SubcategoryID) {
        this.SubcategoryID = SubcategoryID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

}
