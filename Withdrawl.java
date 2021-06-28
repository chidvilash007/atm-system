
package l17_atm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Withdrawl extends JFrame implements ActionListener{
    
    JTextField t1;
    JButton b1,b2,b3;
    JLabel l1,l3;
   String cardno;
   int d;
   Withdrawl(String card){
       cardno=card;
       setFont(new Font("System",Font.BOLD,22));
         setTitle("Withdrawal"); 
       
       
       l1 = new JLabel("WITHDRAW MONEY");
       l1.setFont(new Font("System", Font.BOLD, 40));
        
        l3 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l3.setFont(new Font("System", Font.BOLD, 35));
        
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        b1 = new JButton("WITHDRAW");
        b1.setFont(new Font("Raleway",Font.BOLD,18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("BACK");
        b2.setFont(new Font("Raleway",Font.BOLD,18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("Exit");
        b3.setFont(new Font("Raleway",Font.BOLD,18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        setLayout(null);
        
        
        l1.setBounds(190,100,800,60);
        add(l1);
        
        
        l3.setBounds(120,260,800,60);
        add(l3);
                
        t1.setBounds(210,340,360,50);
        add(t1);
        
        b1.setBounds(220,400,160,50);
        add(b1);
        
        b2.setBounds(400,400,160,50);
        add(b2);
        
        b3.setBounds(300, 550, 200, 50);
        add(b3);
              
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        getContentPane().setBackground(Color.cyan);
        
        setSize(800,800);
        setLocation(500,90);
        setVisible(true);
    }
   	public void actionPerformed(ActionEvent ae){
        try{        
            String a = t1.getText();
           
             DateFormat dform = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            java.util.Date obj = new java.util.Date();
            String date = dform.format(obj);
            
            if(ae.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                }else{
                    Conn c1 = new Conn();
                    
                    ResultSet rs = c1.s.executeQuery("select * from bank where cardno = '"+cardno+"' ");
                    double balance = 0;
                    if(rs.next()) {
                        String cardno = rs.getString("cardno");
                    	String pin = rs.getString("pin");
                    	balance = rs.getDouble("balance");
                    	
                    	d = Integer.parseInt(a);
                        if(d<=10000){                    
                        if(balance>d){
                            if(d<0){JOptionPane.showMessageDialog(null, "AMOUNT CANNOT BE NEGATIVE");d=0;}
                    	balance -= d;
                      	String q1 = "Update bank SET balance ='"+balance+"' where pin = '"+pin+"'";
                        String q2 = "insert into transactions values('"+cardno+"', '"+pin+"','"+date+"','"+d+"','"+balance+"','debit')";
                        c1.s.executeUpdate(q1);
                        c1.s.executeUpdate(q2);
                        if(d>0){JOptionPane.showMessageDialog(null, "Rs. "+a+" Debited Successfully");}
                        new Transactions(cardno).setVisible(true);
                        setVisible(false);
               
              
                    }else{
                            JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        }
                        }else{
                            JOptionPane.showMessageDialog(null, "WITHDRAWAL LIMIT IS 10000 ONLY");
                        }}
                   
                     }
            }else if(ae.getSource()==b2){
                new Transactions(cardno).setVisible(true);
                setVisible(false);
            }else if(ae.getSource()==b3) {
            	System.exit(0);
            }
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error:"+e);
        }
            
    }
    
    public static void main(String[] args){
        String card="";
        new Withdrawl(card).setVisible(true);
    }
}
