
package l17_atm;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener{
    
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l3;
    String cardno;
    Deposit(String card){
        cardno=card;
    	 setFont(new Font("System",Font.BOLD,22));
         setTitle("DEPOSIT");
          
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        l1.setFont(new Font("System", Font.BOLD, 24));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
            
        b1 = new JButton("DEPOSIT");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("BACK");
        b2.setFont(new Font("System", Font.BOLD, 18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("Exit");
        b3.setFont(new Font("System", Font.BOLD, 18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        setLayout(null);
        
        
        l1.setBounds(150,150,800,60);
        add(l1);
        
        t1.setBounds(250,300,300,50);
        add(t1);
        
        b1.setBounds(260,380,125,50);
        add(b1);
        
        b2.setBounds(415,380,125,50);
        add(b2);
        
        b3.setBounds(300,550,200,50);
        add(b3);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        getContentPane().setBackground(Color.cyan);
        
        setSize(800,700);
        setLocation(500,90);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String a = t1.getText();           
            DateFormat dform = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date obj = new Date();
            String date = dform.format(obj);
            if(ae.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
                }else{
                    Conn c1 = new Conn();
                    ResultSet rs = c1.s.executeQuery("select * from bank where cardno = '"+cardno+"' ");
                    double balance = 0,d=0;
                    if(rs.next()) {
                        String cardno = rs.getString("cardno");
                    	String pin = rs.getString("pin");
                    	balance = rs.getDouble("balance");
                    	
                    	d = Double.parseDouble(a);
                        if(d<0){
                   	balance += d;
                    	
                    	String q1 = "Update bank SET balance ='"+balance+"' where pin = '"+pin+"'";
                        String q2 = "insert into transactions values('"+cardno+"', '"+pin+"','"+date+"','"+d+"','"+balance+"','credit')";
                        c1.s.executeUpdate(q1);
                        c1.s.executeUpdate(q2);
                        }
              
                    }
                   if(d>0){
                    JOptionPane.showMessageDialog(null, "Rs. "+a+" Deposited Successfully");
                    new Transactions(cardno).setVisible(true);
                    setVisible(false);}
                   else{JOptionPane.showMessageDialog(null, "AMOUNT CANNOT BE NEGATIVE");}
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
        new Deposit(card).setVisible(true);
    }
}