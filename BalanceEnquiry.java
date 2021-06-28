package l17_atm;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

class BalanceEnquiry {

    String cardno,balance;

    BalanceEnquiry(String card){
        cardno=card;
         try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from bank where cardno = '"+cardno+"'");
            while (rs.next()) {
                 balance = rs.getString("balance");
                }
            }
        catch(Exception e){}
        JOptionPane.showMessageDialog(null,"Your current balance Rs."+balance,"Balance Enquiry", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        String card="";
        new BalanceEnquiry(card);
    }
}
