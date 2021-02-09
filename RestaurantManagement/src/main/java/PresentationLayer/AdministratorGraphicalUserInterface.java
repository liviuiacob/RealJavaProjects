package PresentationLayer;


import BLL.BaseProduct;
import BLL.CompositeProduct;
import BLL.MenuItem;
import BLL.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdministratorGraphicalUserInterface extends JPanel{
        private JButton backButton;

    public AdministratorGraphicalUserInterface(final Restaurant restaurant) {
             JButton addBasicButton;
             JButton deleteBasicButton;
             final JTextField basicNameTF;
             JLabel jcomp4;
             JLabel jcomp5;
             final JTextField basicPriceTF;
             final JTextField deleteBasicTF;
             final JLabel jcomp9;
             final JTable lista;
             JButton viewAll;
             JLabel jcomp12;
             final JTextField editNameTF;
             final JLabel jcomp14;
             final JTextField editPriceTF;
             final JLabel jcomp16;
             JButton editButton;
             final JTextField compositNameTF;
             final JLabel jcomp17;
             final JTextField compositBaseNameTF;
             final JLabel jcomp18;
             JButton addCompositButton;
             JButton addComponentButton;


            //construct components
            addBasicButton = new JButton ("Add");
            deleteBasicButton = new JButton ("Delete");
            basicNameTF = new JTextField (5);
            jcomp4 = new JLabel ("Basic Product");
            jcomp5 = new JLabel ("Price");
            basicPriceTF = new JTextField (5);
            backButton = new JButton ("Back");
            deleteBasicTF = new JTextField(5);
            jcomp9 = new JLabel ("Product");
            lista = new JTable ();
            viewAll = new JButton ("View All");
            jcomp12 = new JLabel ("Products");
            editNameTF = new JTextField (5);
            jcomp14 = new JLabel ("Product Name");
            editPriceTF = new JTextField (5);
            jcomp16 = new JLabel ("Product Price");
            editButton = new JButton ("Edit");


            jcomp17 = new JLabel ("Composite Product");
            compositNameTF = new JTextField (5);
            addCompositButton = new JButton("Composite");
            jcomp18 = new JLabel ("Base Product");
            compositBaseNameTF = new JTextField (5);
            addComponentButton = new JButton("component");

            //adjust size and set layout
            setPreferredSize (new Dimension (667, 366));
            setLayout (null);

            //add components
            add (addBasicButton);
            add (deleteBasicButton);
            add (basicNameTF);
            add (jcomp4);
            add (jcomp5);
            add (basicPriceTF);
            add (backButton);
            add (deleteBasicTF);
            add (jcomp9);
            add (lista);
            add (viewAll);
            add (jcomp12);
            add (editNameTF);
            add (jcomp14);
            add (editPriceTF);
            add (jcomp16);
            add (editButton);
            add (compositNameTF);
            add (jcomp17);
            add (compositBaseNameTF);
            add (jcomp18);
            add (addCompositButton);
            add (addComponentButton);

            //set component bounds (only needed by Absolute Positioning)
            addBasicButton.setBounds (80, 85, 100, 30);
            deleteBasicButton.setBounds (80, 240, 100, 30);
            basicNameTF.setBounds (20, 45, 100, 25);
            jcomp4.setBounds (20, 10, 100, 25);
            jcomp5.setBounds (140, 10, 100, 25);
            basicPriceTF.setBounds (140, 45, 100, 25);
            backButton.setBounds (5, 335, 100, 25);
            deleteBasicTF.setBounds (80, 210, 100, 25);
            jcomp9.setBounds (80, 180, 100, 25);
            lista.setBounds (520, 45, 100, 230);
            viewAll.setBounds (520, 285, 100, 25);
            jcomp12.setBounds (520, 20, 100, 25);
            editNameTF.setBounds (330, 235, 100, 25);
            jcomp14.setBounds (330, 210, 100, 25);
            editPriceTF.setBounds (330, 290, 100, 25);
            jcomp16.setBounds (330, 265, 100, 25);
            editButton.setBounds (330, 325, 100, 25);


            compositNameTF.setBounds (330, 35, 100, 25);
            jcomp17.setBounds (330, 10, 100, 25);
            addCompositButton.setBounds (330, 60, 100, 25);
            compositBaseNameTF.setBounds (330, 110, 100, 25);
            jcomp18.setBounds (330, 85, 100, 25);
            addComponentButton.setBounds (330, 145, 100, 25);




            addBasicButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            if(restaurant.isInMenu(basicNameTF.getText()) == false) { //create
                                    restaurant.addInMenu(new BaseProduct(basicNameTF.getText(), Float.parseFloat(basicPriceTF.getText())));
                                    System.out.println(restaurant.toString());
                            }
                            else System.out.println("Produsul este deja in meniu");
                    }
            });
            deleteBasicButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            if(restaurant.isInMenu(deleteBasicTF.getText()) == true) { //create
                                    restaurant.deleteMenuItem(deleteBasicTF.getText());
                                    System.out.println(restaurant.toString());
                            }
                            else System.out.println("Produsul NU este in meniu");
                    }
            });
            addCompositButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            if(restaurant.isInMenu(compositNameTF.getText()) == false) { //create
                                    restaurant.addInMenu(new CompositeProduct(compositNameTF.getText()));
                                    System.out.println(restaurant.toString());
                            }
                            else System.out.println("Produsul este deja in meniu");
                    }
            });
            addComponentButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            if(restaurant.isInMenu(compositBaseNameTF.getText()) == true) { //create
                                    MenuItem m= restaurant.searchByName(compositNameTF.getText());
                                    MenuItem n= restaurant.searchByName(compositBaseNameTF.getText());
                                    restaurant.addInComposit(m, n);
                                    System.out.println(restaurant.toString());
                            }
                            else System.out.println("Produsul NU este in meniu");
                    }
            });
            editButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            if(restaurant.isInMenu(editNameTF.getText()) == true) { //create
                                    restaurant.editMenuItem(editNameTF.getText(), Float.parseFloat(editPriceTF.getText()));
                                    System.out.println(restaurant.toString());
                            }
                            else System.out.println("Produsul NU este in meniu");
                    }
            });
            viewAll.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                            restaurant.showTable(lista);
                    }
            });

        }
        public JButton getBackButton(){
                return this.backButton;
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

