package l17_atm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;
  
    Login(){
        setTitle("DCL ATM SYSTEM");
        
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("l17_atm/icons/logo.jpg"));
        Image i12 = i11.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel l11 = new JLabel(i13);
        l11.setBounds(70, 10, 80, 80);
        add(l11);
        ImageIcon i21 = new ImageIcon(ClassLoader.getSystemResource("l17_atm/icons/img1.jpg"));
        Image i22 = i21.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        ImageIcon i23 = new ImageIcon(i22);
        JLabel l21 = new JLabel(i23);
        l21.setOpaque(true);
        l21.setBackground(Color.green);
        l21.setBounds(670, 80, 130, 130);
        add(l21);
        ImageIcon i31 = new ImageIcon(ClassLoader.getSystemResource("l17_atm/icons/img2.jpg"));
        Image i32 = i31.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i32);
        JLabel l31 = new JLabel(i33);
        l31.setOpaque(true);
        l31.setBackground(Color.cyan);
        l31.setBounds(670, 220, 130, 130);
        add(l31);
        ImageIcon i41 = new ImageIcon(ClassLoader.getSystemResource("l17_atm/icons/img3.jpg"));
        Image i42 = i41.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        ImageIcon i43 = new ImageIcon(i42);
        JLabel l41 = new JLabel(i43);
        l41.setOpaque(true);
        l41.setBackground(Color.yellow);
        l41.setBounds(670, 360, 130, 130);
        add(l41);
        
        
        l1 = new JLabel("DCL ATM SYSTEM");
        l1.setFont(new Font("Osward", Font.BOLD, 35));
        l1.setBounds(240,25,450,40);
        l1.setForeground(Color.white);
        add(l1);
        l1 = new JLabel("BANKING PARTNERS");
        l1.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
        l1.setBounds(660,25,445,40);
        l1.setForeground(Color.red);
        add(l1);
        
        l1 = new JLabel("LOGIN");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(330,80,450,40);
        l1.setForeground(Color.WHITE);
        add(l1);
        l1 = new JLabel("");
        l1.setFont(new Font("Osward", Font.BOLD, 24));
        l1.setBounds(650,20,170,480);
        l1.setOpaque(true);
        l1.setBackground(Color.WHITE);
        add(l1);
        
        l2 = new JLabel("Card No:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setOpaque(true);
        l2.setBackground(Color.white);
        l2.setBounds(125,150,120,30);
        add(l2);
       
        tf1 = new JTextField(15);
        tf1.setBounds(300,150,230,30);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        add(tf1);
        
        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setOpaque(true);
        l3.setBackground(Color.white);
        l3.setBounds(125,220,60,30);
        add(l3);
        
        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(300,220,230,30);
        add(pf2);
                
        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("FORGOT PIN");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("SIGN UP");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        setLayout(null);
        
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(300,300,100,30);
        add(b1);
        
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(430,300,140,30);
        add(b2);
        
        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBounds(300,350,230,30);
        add(b3);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        getContentPane().setBackground(Color.gray);
        
        setSize(900,550);
        setLocation(550,200);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        try{   
        	 Conn c1 = new Conn();
             String a  = tf1.getText();
             String b  = pf2.getText();
             String q  = "select * from login where cardno = '"+a+"' and pin = '"+b+"'";

             ResultSet rs = c1.s.executeQuery(q);
            if(ae.getSource()==b1){
                if(rs.next()){
                    new Transactions(a).setVisible(true);
                    setVisible(false);
                }else{
                    if(a.equals("")){JOptionPane.showMessageDialog(null, "ENTER CARD NUMBER");}
                    else if(b.equals("")){JOptionPane.showMessageDialog(null, "ENTER PIN");}
                    else{JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");}
                }
            }else if(ae.getSource()==b2){
                new ForgotPassword().setVisible(true);
                setVisible(false);
                 }else if(ae.getSource()==b3){
                new Signup().setVisible(true);
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error: "+e);
        }
    }
    public static void main(String[] args){
        new Login().setVisible(true);
    }   
}