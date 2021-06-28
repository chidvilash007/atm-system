package l17_atm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class MiniStatement extends JFrame implements ActionListener{
    String cardno;
    JLabel l1;
    JButton b1;
    MiniStatement(String card){
       cardno=card;
       setFont(new Font("System",Font.BOLD,22));
        setTitle("Mini Statement");
        b1 = new JButton("Back");
        b1.setFont(new Font("System", Font.BOLD, 18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        
        
        int i=0,j=0,l=0;
         try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from transactions where cardno = '"+cardno+"'");
            while(rs.next()){
               i++;
            }
         }catch(Exception e){}
         String amount[]=new String[i];
            String date[]=new String[i];
            String mode[]=new String[i];
            String balance[]=new String[i];
   
         try{
             Conn c1 = new Conn();
             ResultSet rs1 = c1.s.executeQuery("select * from transactions where cardno = '"+cardno+"'");
            while(rs1.next()){
                
               amount[j] = rs1.getString("amount");
               date[j] = rs1.getString("date");
               mode[j] = rs1.getString("mode");
               balance[j] = rs1.getString("balance");
               j++;
                }
          
        }catch(Exception e){}
       
        if(i>15){l=15;}else{l=i;}
         l1 = new JLabel(l +" TRANSACTIONS LISTED OF "+i +"TRANSACTIONS");
        l1.setFont(new Font("System", Font.BOLD, 24));        
        l1.setBounds(60, 40, 700, 35);
        add(l1);
     
        for(int k=1;k<=l;k++){
            l1 = new JLabel("Amount:  "+amount[i-k]);
            l1.setFont(new Font("System", Font.PLAIN, 18)); 
            l1.setBounds(100,((40*k)+80), 150, 35);
            j++;
            add(l1); }
        for(int k=1;k<=l;k++){
            l1 = new JLabel( "Mode:  "+mode[i-k]+"      Time:  "+date[i-k]+"      Balance:  "+balance[i-k]);
            l1.setFont(new Font("System", Font.PLAIN, 18)); 
            l1.setBounds(270,((40*k)+80), 800, 35);
            j++;
            add(l1); }
        
        l1 = new JLabel("Final Balance: "+balance[i-1]);
        l1.setFont(new Font("System", Font.BOLD, 18));        
        l1.setBounds(100, ((40*l)+125), 900, 35);
        add(l1);
        b1.setBounds(50, ((40*l)+180), 100, 50);
        add(b1);
        b1.addActionListener(this);

   
      getContentPane().setBackground(Color.white);
      setSize(900, 1000);
      
        setLayout(null);
       
    }
       public void actionPerformed(ActionEvent ae){
        try{    
            System.out.println("1111");
            if(ae.getSource()==b1){
                new Transactions(cardno).setVisible(true);
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error:"+e);
        }
       }
    public static void main(String[] args){
        String card="";
        new MiniStatement(card).setVisible(true);
    }
    
}
