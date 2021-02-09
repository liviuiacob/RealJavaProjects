package PresentationLayer;


import BLL.BaseProduct;
import BLL.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class WaiterGraphicalUserInterface extends JPanel{
    private JButton jcomp2;

    public WaiterGraphicalUserInterface(final Restaurant restaurant) {
         final JTextField orderTF;

        final JTable tabel;
         JButton viewAllButton;
         JLabel jcomp5;
         JLabel jcomp6;
         JLabel jcomp7;
         final JTextField tabeleNrTF;
         JButton addOrderBtton;
         final JTextField generateBillOrderIDTF;
         JLabel jcomp11;
         JButton generateBillButton;
         JLabel jcomp8;
         final JTextField orderID_billTF;
         JLabel jcomp9;
         final JTextField porductOrderTF;
          JButton addProductButton;
        //construct preComponents

        //construct components
        orderTF = new JTextField (5);
        jcomp2 = new JButton ("Back");
        tabel = new JTable ();
        viewAllButton = new JButton ("View All");
        jcomp5 = new JLabel ("Orders");
        jcomp6 = new JLabel ("Order");
        jcomp7 = new JLabel ("Table");
        tabeleNrTF = new JTextField (5);
        addOrderBtton = new JButton ("Add");
        generateBillOrderIDTF = new JTextField (5);
        jcomp11 = new JLabel ("OrderID");
        generateBillButton = new JButton ("Generate Bill");


        jcomp8 = new JLabel ("OrderId");
        orderID_billTF = new JTextField (5);
        jcomp9 = new JLabel ("Product");
        porductOrderTF = new JTextField (5);
        addProductButton = new JButton ("Add");

        //adjust size and set layout
        setPreferredSize (new Dimension (667, 366));
        setLayout (null);

        //add components
        add (orderTF);
        add (jcomp2);
        add (tabel);
        add (viewAllButton);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add (tabeleNrTF);
        add (addOrderBtton);
        add (generateBillOrderIDTF);
        add (jcomp11);
        add (generateBillButton);

        add (jcomp8);
        add (orderID_billTF);
        add (jcomp9);
        add (porductOrderTF);
        add (addProductButton);

        //set component bounds (only needed by Absolute Positioning)
        orderTF.setBounds (5, 25, 100, 25);
        jcomp2.setBounds (5, 335, 100, 25);
        tabel.setBounds (520, 45, 100, 230);
        viewAllButton.setBounds (520, 285, 100, 25);
        jcomp5.setBounds (520, 20, 100, 25);
        jcomp6.setBounds (5, 5, 100, 25);
        jcomp7.setBounds (5, 70, 100, 25);
        tabeleNrTF.setBounds (5, 90, 100, 25);
        addOrderBtton.setBounds (5, 120, 100, 25);
        generateBillOrderIDTF.setBounds (170, 225, 110, 25);
        jcomp11.setBounds (170, 205, 100, 25);
        generateBillButton.setBounds (170, 260, 110, 25);

        jcomp8.setBounds (170, 5, 100, 25);
        orderID_billTF.setBounds (170, 30, 100, 25);
        jcomp9.setBounds (170, 55, 100, 25);
        porductOrderTF.setBounds (170, 80, 100, 25);
        addProductButton.setBounds (170, 115, 100, 25);



        addOrderBtton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (restaurant.findByID(Integer.parseInt(orderTF.getText()))==null) {
                    restaurant.createNewOrder(Integer.parseInt(orderTF.getText()), new Date(), Integer.parseInt(tabeleNrTF.getText()));
                    System.out.println(restaurant.Orders());
                }
                else
                    System.out.println("Exista deja o comanda cu acelasi ID\n");
            }


        });
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restaurant.addProductToOrder(porductOrderTF.getText(), Integer.parseInt(orderID_billTF.getText()));
                System.out.println(restaurant.Orders());
            }


        });
        generateBillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restaurant.generateBill(Integer.parseInt(generateBillOrderIDTF.getText()));
                System.out.println(restaurant.Orders());
            }


        });

        viewAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restaurant.showOrderTable(tabel);
            }
        });


    }
    public JButton getBackButton(){
        return this.jcomp2;
    }

/*
    public static void main (String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new MyPanel());
        frame.pack();
        frame.setVisible (true);
    }

 */
}

