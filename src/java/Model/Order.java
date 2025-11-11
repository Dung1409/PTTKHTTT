/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Order {
    public int id;
    public Member member;
    public String createDate;
    public OrderProduct[] orders;
    public Receiver receiver;

    public Order() {
    }

    
    
    public Order(Member member, String createDate, OrderProduct[] orders, Receiver receiver) {
        this.member = member;
        this.createDate = createDate;
        this.orders = orders;
        this.receiver = receiver;
    }
       
}
