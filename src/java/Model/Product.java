/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Product {
    public int id;
    public String name;
    public String supplierName;
    public String description;
    public float purchasePrice;
    public float sellingPrice;

    public Product(int id, String name, String supplierName, String description, float purchasePrice, float sellingPrice) {
        this.id = id;
        this.name = name;
        this.supplierName = supplierName;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
    }
    
    
    
}
