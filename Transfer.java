package l17_atm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Transfer extends JFrame implements ActionListener{
    
    JTextField t2,t3;
    JButton b1,b2;                               
    JLabel l1,l2,l3,l4;
    String cardno,b="0",c="0";
    int st=0,st1=0,st2=1;
    double d=0;
    Transfer(String card){
        cardno=card;
 setTitle("Tranfer Amount");
    	setFont(new Font("System",Font.BOLD,22));
        
        l1 = new JLabel("Transfering Amount");
        l1.setFont(new Font("System", Font.BOLD, 35));
               
        l3 = new JLabel("Enter reciever Account number:");
        l3.setFont(new Font("System", Font.BOLD, 20));

        l4 = new JLabel("Enter amount:");
        l4.setFont(new Font("System", Font.BOLD, 22));
              
        
        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.BOLD, 22));
        
        t3 = new JTextField();
        t3.setFont(new Font("Raleway", Font.BOLD, 22));
        
        b1 = new JButton("Send");
        b1.setFont(new Font("System",Font.BOLD,18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("Cancel");
        b2.setFont(new Font("System",Font.BOLD,18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setLayout(null);
        
        l1.setBounds(250,130,800,60);
        add(l1);
        
        
        l3.setBounds(70,240,500,40);
        add(l3);
        

        l4.setBounds(70,360,500,40);
        add(l4);
        
        t2.setBounds(400,240,260,40);
        add(t2);
        
        t3.setBounds(400,360,260,40);
        add(t3);
        
        b1.setBounds(220,460,160,50);
        add(b1);
        
        b2.setBounds(400,460,160,50);
        add(b2);
        
        getContentPane().setBackground(Color.cyan);
        
        setSize(800,800);
        setLocation(500,90);
        setVisible(true);
    
    }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            Conn c1 = new Conn();
            
            DateFormat dform = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            java.util.Date obj = new java.util.Date();
            String date = dform.format(obj);
            
            
            if(ae.getSource()==b1){
                
                if (t2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Enter reciever account number");
                    st=1;
                }
                
                else if(t3.getText().equals("")) {
                	JOptionPane.showMessageDialog(null, "Enter Amount");
                        st=1;
                }
                else{
                    b = t2.getText();
                    c = t3.getText();
                    st=0;
            
                }
            ResultSet rs3 = c1.s.executeQuery("select * from bank where cardno = '"+b+"' ");
            if(rs3.next()){
                String vreciever = rs3.getString("cardno");
            
                
                
            ResultSet rs = c1.s.executeQuery("select * from bank where cardno = '"+cardno+"' ");   
                double balance = 0;
                    if(rs.next()) {
                        String cardno = rs.getString("cardno");
                        String pin = rs.getString("pin");
                    	balance = rs.getDouble("balance");
                    	st2=0;
                    	d = Double.parseDouble(c);
                        if(balance<d || d<0){
                            if(balance<d){JOptionPane.showMessageDialog(null, "INSUFFICIENT BALANCE");st2=1;}
                            if(d<0 &&st!=1){JOptionPane.showMessageDialog(null, "AMOUNT CANNOT BE NEGATIVE");st2=1;}
                            d=0;
                        }
                    	balance -= d;
                        if(st2==0){
                    	//String q1 = "insert into bank values('"+null+"','"+pin+"','"+d+"','"+balance+"')";
                    	String q1 = "Update bank SET balance ='"+balance+"' where cardno = '"+cardno+"'";
                       String q2 = "insert into transactions values('"+cardno+"','"+pin+"', '"+date+"','"+d+"','"+balance+"','debit')";
                        c1.s.executeUpdate(q1);
                       c1.s.executeUpdate(q2);}
              
                    }
            ResultSet rs1 = c1.s.executeQuery("select * from bank where cardno = '"+b+"' ");
                    double balance1 = 0;
                    if(rs1.next()) {
                        String cardno = rs1.getString("cardno");
                    	String pin = rs1.getString("pin");
                    	balance1 = rs1.getDouble("balance");
                    	
                    	balance1 += d;
                    	if(st2==0){
                    	String q1 = "Update bank SET balance ='"+balance1+"' where cardno = '"+b+"'";
                      String q2 = "insert into transactions values('"+cardno+"','"+pin+"','"+date+"','"+d+"','"+balance1+"','credit')";
                        c1.s.executeUpdate(q1);
                        c1.s.executeUpdate(q2);}
              
                    } 
                    if(d!=0){

                JOptionPane.showMessageDialog(null, "Amount Transfered successfully");}
            }else if(st==0){
                JOptionPane.showMessageDialog(null,"Account Details not found");
            }
            if(st==0 && st2==0){
                new Transactions(cardno).setVisible(true);
                setVisible(false);
            }
                } else if(ae.getSource()==b2) {
        	new Transactions(cardno).setVisible(true);
        	setVisible(false);
            	
            }
            }catch(Exception e){
            e.printStackTrace();
            System.out.println("error:" +e);
        } 
        
        }
      public static void main(String[] args){
          String card="";
        new Transfer(card).setVisible(true);
    }
       
        }