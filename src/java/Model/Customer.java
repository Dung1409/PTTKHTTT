/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Customer extends Member{
    public Customer(int id, String username, String password, String name, String address, String dateOfBith, String email, String phoneNumber) {
        super(id, username, password, name, address, dateOfBith, email, phoneNumber);
    }

    public Customer(String username, String password) {
        super(username, password);
    }
    
    
    
}
