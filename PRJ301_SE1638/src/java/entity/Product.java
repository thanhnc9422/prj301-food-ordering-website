/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author 84961
 */
public class Product {
    private int ProductID;
    private String Name;
    private int Price;
    private int SubcategoryID;
    private String images;

    public Product(){
    }

    public Product(int ProductID, String Name, int Price, int SubcategoryID,String images) {
        this.ProductID = ProductID;
        this.Name = Name;
        this.Price = Price;
        this.SubcategoryID = SubcategoryID;
        this.images = images;
    }


    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getSubcategoryID() {
        return SubcategoryID;
    }

    public void setSubcategoryID(int SubcategoryID) {
        this.SubcategoryID = SubcategoryID;
    }


        public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
    
    
}
