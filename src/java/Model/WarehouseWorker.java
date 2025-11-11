/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class WarehouseWorker extends Staff{
    
    public WarehouseWorker(int id, String username, String password, String name, String address, String dateOfBith, String email, String phoneNumber) {
        super(id, username, password, name, address, dateOfBith, email, phoneNumber);
    }

    public WarehouseWorker(String username, String password) {
        super(username, password);
    }   
}
