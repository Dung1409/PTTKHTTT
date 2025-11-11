/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Member {
    public int id;
    public String username;
    public String password;
    public String name;
    public String address;
    public String dateOfBirth;
    public String email ;
    public String phoneNumber ;
    public String position;

    public Member(int id, String username, String password, String name, String address, String dateOfBith, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBith;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Member() {
        
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        
        if(obj == null || getClass() != obj.getClass()) return false;
        
        Member orther = (Member) obj;
        return this.id == orther.id;
    }

    @Override
    public int hashCode( ) {
      return Objects.hash(username);
    }
    
    
    
    
    
    
}
