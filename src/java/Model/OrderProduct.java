/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class OrderProduct {
    public int id;
    public Product p;
    public int quanity;
    public float sellingPrice;

    public OrderProduct(int id, Product p, int quanity, float sellingPrice) {
        this.id = id;
        this.p = p;
        this.quanity = quanity;
        this.sellingPrice = sellingPrice;
    }

    public OrderProduct(Product p, int quanity, float sellingPrice) {
        this.p = p;
        this.quanity = quanity;
        this.sellingPrice = sellingPrice;
    }
    
    
    
    
    
    
    
}
