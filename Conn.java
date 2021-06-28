package l17_atm;

import java.sql.*;  

public class Conn{
    Connection c1;
    Statement s;
    public Conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            c1 =DriverManager.getConnection("jdbc:mysql://localhost:3307/atm?characterEncoding=utf8","root","Dramalife@000");    
            s =c1.createStatement(); 
           }catch(Exception e){ 
            System.out.println(e);
        }  
    }  
}  