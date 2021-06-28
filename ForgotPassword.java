package l17_atm;

/**
 *
 * @author KOVVURIDHARMAREDDY
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Random;


public class ForgotPassword extends JFrame implements ActionListener{
    
    JTextField t1;
    JPasswordField t2;
    JButton b1,b2;                               
    JLabel l1,l2,l3;
    int id=0,otp=0;
    ForgotPassword(){
    	 setFont(new Font("System",Font.BOLD,22));
         setTitle("FORGOT PASSWORD");
    	
        
        l1 = new JLabel("FORGOT PASSWORD");
        l1.setFont(new Font("System", Font.BOLD, 35));
        
        
        l2 = new JLabel("ENTER CARD NO:");
        l2.setFont(new Font("System", Font.BOLD, 22));
      
        
        l3 = new JLabel("ENTER OTP:");
        l3.setFont(new Font("System", Font.BOLD, 22));
        

        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 22));
        
        
        b1 = new JButton("GET OTP");
        b1.setFont(new Font("System",Font.BOLD,18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("SUBMIT OTP");
        b2.setFont(new Font("System",Font.BOLD,18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setLayout(null);
        
        l1.setBounds(220,130,800,60);
        add(l1);
        
        l2.setBounds(100,240,200,40);
        add(l2);
        
        l3.setBounds(100,450,150,40);
        add(l3);
     
        t1.setBounds(310,240,360,40);
        add(t1);
        
        t2.setBounds(310,450,360,40);
        add(t2);
        
        
        b1.setBounds(285,320,160,50);
        add(b1);
        
        b2.setBounds(285,530,160,50);
        add(b2);
        
        getContentPane().setBackground(Color.cyan);
        
        setSize(800,800);
        setLocation(500,90);
        setVisible(true);
    
    }
    
    public void actionPerformed(ActionEvent ae){
        String a,b,email;           
            a = t1.getText();
            b = t2.getText();
            if(ae.getSource()==b1){
                if (t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please Enter CARD NO");
                }
                else{
                 try{  
                    Conn c1 = new Conn();
                    ResultSet rs = c1.s.executeQuery("select * from signup3 where cardno = '"+a+"'");
                    while (rs.next()) {
                        id = rs.getInt("id");

                }
                            }catch(Exception e){
            e.printStackTrace();
            System.out.println("error:" +e);
        }
                 try{  
                    Conn c1 = new Conn();
                 
                    if(id==0){
                        JOptionPane.showMessageDialog(null, "Please Enter VALID CARD NO");                    
                    }
                    else{
                       ResultSet rs1 = c1.s.executeQuery("select * from signup where id = '"+id+"' ");
                    while (rs1.next()) {
                        email = rs1.getString("email");
                        Random r=new Random();
                        otp=99999+(r.nextInt(899999));
                        new Outlook(email,otp);
                } 
                    }
                 }catch(Exception e){
                     e.printStackTrace();
                     System.out.println("error:" +e);
        }
                }
        } else if(ae.getSource()==b2) {
            if(String.valueOf(otp).equals(b) ){
        	new Reset(a).setVisible(true);
        	setVisible(false);
            }
            else{
                  JOptionPane.showMessageDialog(null, "INVALID REQUEST");  
                    }
        }
            

    }
    public static void main(String[] args){
        new ForgotPassword();
    }
}
