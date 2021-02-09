import BLL.BaseProduct;
import BLL.CompositeProduct;
import BLL.MenuItem;
import BLL.Restaurant;
import PresentationLayer.AdministratorGraphicalUserInterface;
import PresentationLayer.WaiterGraphicalUserInterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





public class MainClass extends JPanel{
        private JButton jcomp1;
        private JButton jcomp2;

        public MainClass() {
            //construct components
            jcomp1 = new JButton ("Admin");
            jcomp2 = new JButton ("Waiter");

            //adjust size and set layout
            setPreferredSize (new Dimension (667, 366));
            setLayout (null);

            //add components
            add (jcomp1);
            add (jcomp2);

            //set component bounds (only needed by Absolute Positioning)
            jcomp1.setBounds (90, 150, 155, 60);
            jcomp2.setBounds (400, 150, 155, 60);
        }


        public static void main (String[] args) {
            final Restaurant restaurant= new Restaurant();
            final JFrame frame = new JFrame ("Restaurant");
            MainClass main=new MainClass();
            frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add (main);
            frame.pack();
            frame.setVisible (true);


            main.jcomp1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.setVisible(false);
                    final JFrame admin = new JFrame ("Admin GUI");
                    AdministratorGraphicalUserInterface admin1 = new AdministratorGraphicalUserInterface(restaurant);
                    admin.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                    admin.getContentPane().add(admin1);
                    admin.pack();
                    admin.setVisible (true);

                    admin1.getBackButton().addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            admin.setVisible(false);
                            frame.setVisible(true);
                        }
                    });
                }
            });

            main.jcomp2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    frame.setVisible(false);
                    final JFrame waiter = new JFrame ("Waiter GUI");
                    WaiterGraphicalUserInterface admin1 = new WaiterGraphicalUserInterface(restaurant);
                    waiter.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                    waiter.getContentPane().add(admin1);
                    waiter.pack();
                    waiter.setVisible (true);

                    admin1.getBackButton().addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            waiter.setVisible(false);
                            frame.setVisible(true);
                        }
                    });
                }
            });

        }
    }



