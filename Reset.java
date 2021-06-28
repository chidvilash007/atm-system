package l17_atm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Reset extends JFrame implements ActionListener{
    
    JPasswordField t1,t3;
    JButton b1,b2;                               
    JLabel l1,l2,l4;
    String cardno,c;
    Reset(String card){
        cardno=card;
    	 setFont(new Font("System",Font.BOLD,22));
         setTitle("RESET PIN");
    	
        
        l1 = new JLabel("RESET YOUR PIN");
        l1.setFont(new Font("System", Font.BOLD, 35));
        
        
        l2 = new JLabel("ENTER PIN:");
        l2.setFont(new Font("System", Font.BOLD, 22));
     
        l4 = new JLabel("Re-Enter New PIN:");
        l4.setFont(new Font("System", Font.BOLD, 22));
              
        
        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        
        t3 = new JPasswordField();
        t3.setFont(new Font("Raleway", Font.BOLD, 22));
        
        b1 = new JButton("RESET");
        b1.setFont(new Font("System",Font.BOLD,18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("BACK TO LOGIN");
        b2.setFont(new Font("System",Font.BOLD,18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setLayout(null);
        
        l1.setBounds(220,130,800,60);
        add(l1);
        
        l2.setBounds(100,240,150,40);
        add(l2);
        

        l4.setBounds(100,360,200,40);
        add(l4);
        
        
        t1.setBounds(310,240,360,40);
        add(t1);
        
        
        t3.setBounds(310,360,360,40);
        add(t3);
        
        b1.setBounds(220,460,160,50);
        add(b1);
        
        b2.setBounds(400,460,210,50);
        add(b2);
        
        getContentPane().setBackground(Color.cyan);
        
        setSize(800,800);
        setLocation(500,90);
        setVisible(true);
    
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
            int st;
            String a="00",b="13";
            if(ae.getSource()==b1){
                st=0;
                if (t1.getText().equals("") || t3.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please Enter PIN");
                    st=1;
                }
                else if(st==0){
                    a = t1.getText();
                    b = t3.getText();
            
                }
                 if (a.equals(b)){
                    try{
                    Conn c1 = new Conn();
                    String q1 = "update bank set pin = '"+a+"' where cardno = '"+cardno+"' ";
                    String q2 = "update login set pin = '"+a+"' where cardno = '"+cardno+"' ";
                    String q3 = "update signup3 set pin = '"+a+"' where cardno = '"+cardno+"' ";

                    c1.s.executeUpdate(q1);
                    c1.s.executeUpdate(q2);
                    c1.s.executeUpdate(q3);

                    JOptionPane.showMessageDialog(null, "PIN changed successfully");
               
                    new Login().setVisible(true);
                    setVisible(false);
            
                    }
                    catch(Exception e){}
                
                }
                if((((a.equals(b))==false)) &&st==0){
                	JOptionPane.showMessageDialog(null, "PIN MISMATCH");
                }
                
        
        } else if(ae.getSource()==b2) {
        	new Login().setVisible(true);
        	setVisible(false);
            	
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error:" +e);
        }
    }
    

    public static void main(String[] args){
        String card="";
        new Reset(card).setVisible(true);
    }
}

