package BLL;



import PresentationLayer.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.util.*;
import java.util.List;

public class Restaurant implements Serializable,iRestaurantProcessing,Observable {
    private List<Observer> observers;
    private HashMap<Order, ArrayList<MenuItem>> hmap;
    private ArrayList<MenuItem> menu= new ArrayList<MenuItem>();

    public Restaurant(){
        observers=new ArrayList<Observer>();
        hmap=new HashMap<Order,ArrayList<MenuItem>>();
    }

    /**
     * Acesta metoda nu a fost folosita
     * @param observer
     */
    public void registerObserver(Observer observer) {
        if(observer!=null){
            this.observers.add(observer);
        }
    }

    /**
     * Aceasta metoda nu a fost folosita
     */
    public void notifyObservers() {
        Iterator<Observer> it=observers.iterator();
        while(it.hasNext()){
            Observer observer=it.next();
            observer.update(this);
        }
    }

    /**
     * Aceasta metoda nu a fost folosita
     * @param observer
     */
    public void removeObserver(Observer observer) {
        if(observer!=null)
            this.observers.remove(observer);
    }

    /**
     * Aceasta metoda cauta dupa nume daca un produs se afla deja in meniu si returneaza true daca da si false daca nu
     * @param nume
     * @return
     */
    public boolean isInMenu(String nume){
        for (MenuItem m : menu) {
            if (nume.equals(m.getName()))
                return true;
        }
        return false;

    }

    /**
     * Aceasta metoda cauta dupa dume un produs si il returneaza daca il gaseste, si daca nu returneaza null
     * @param nume
     * @return
     */
    public MenuItem searchByName(String nume){
        for (MenuItem m : menu) {
            if (nume.equals(m.getName()))
                return m;
        }
        return null;

    }

    /**
     * Aceasta metoda cauta dupa nume un produs si il sterge din meniu daca il gaseste
     * @param str
     */
    public void deleteMenuItem(String str) {
        MenuItem q = null;
        for (MenuItem m : menu)
            if (str.equals(m.getName()))
                q = m;
        menu.remove(q);
    }

    /**
     * Aceasta metoda cauta dupa nume un produs si ii seteaza pretul ca fiind cel primit ca parametru
     * @param nume
     * @param pret
     */
    public void editMenuItem(String nume, float pret) {
        MenuItem m=searchByName(nume);
        m.setPrice(pret);
    }

    /**
     * Adauga in meniu un produs
     * @param m
     */
    public void addInMenu(MenuItem m) {
        menu.add(m);
    }

    /**
     * Adauga intr-un produs complez un produs de baza
     * @param m
     * @param n
     */
    public void addInComposit(MenuItem m, MenuItem n){
        m.add(n);
        m.computePrice();
    }

    /**
     * Creaza o noua comanda cu datele primite ca parametri
     * @param id
     * @param date
     * @param number
     */
    public void createNewOrder(int id, Date date, int number) {
        Order o = new Order(id, date, number);
        ArrayList<MenuItem> lista= new ArrayList<MenuItem>();
        hmap.put(o, lista);

    }

    /**
     * Cauta si returneaza o comanda dupa id
     * @param id
     * @return
     */
    public Order findByID(int id)
    {
        for (HashMap.Entry<Order, ArrayList<MenuItem>> entry: hmap.entrySet()){
            if (entry.getKey().getOrderID()==id)
                return entry.getKey();
        }
        return null;

    }

    /**
     * Adauga un produs la o comanda deja existenta
     * @param name
     * @param id
     */
    public void addProductToOrder(String name, int id){
        if (searchByName(name)==null)
            System.out.println("Nu exista produsul");
        else{
            Order o = findByID(id);
            MenuItem m= searchByName(name);
            for (HashMap.Entry<Order, ArrayList<MenuItem>> entry: hmap.entrySet()){
                if (entry.getKey().equals(o))
                    entry.getValue().add(m);
            }

        }
    }

    /**
     * Calculeaza pretul pentru o comanda existenta
     * @param o
     * @return
     */
    public float computePrice(Order o) {
        float price=0;
        for (HashMap.Entry<Order, ArrayList<MenuItem>> entry: hmap.entrySet()){
            Order k= entry.getKey();
            ArrayList<MenuItem> value= entry.getValue();
            if (k.equals(o)){
                for (MenuItem m: value)
                    price+=m.computePrice();
            }

        }
        return price;
    }

    /**
     * Afiseaza in consola comanda finalizata si pretul ei, dupa care o scoate din lista de comenzi active
     * @param id
     */
    public void generateBill(int id) {
        Order o = findByID(id);
        System.out.println("Order with ID: " + id + " has ben paid with: "+ computePrice(o)+" and has been removed\n");
        hmap.remove(o);
    }
    public String toString(){
        String t="";
        Iterator<MenuItem> menuItemIterator = menu.iterator();
        while(menuItemIterator.hasNext()) {
            MenuItem menuItem = menuItemIterator.next();
            t += menuItem.toString();
        }
        return t;
    }

    /**
     * Echivalent cu toString(), doar ca pentru comnezi
     * @return
     */
    public String Orders(){
        String t="";
        for (HashMap.Entry<Order, ArrayList<MenuItem>> entry: hmap.entrySet()){
            t+=entry.toString();
        }
        return t;
    }

    /**
     * Creaza lista afisata in tabel cu obiectele noi formate
     * @param table
     */
    public void showTable(JTable table) {
        List<Object[]> list = new ArrayList<Object[]>();
        for(MenuItem m : this.menu) {
            list.add(new Object[] {m.getName(), String.valueOf(m.getPrice())});
        }
        table.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), new String[] {"Name", "Price"}));
    }
    /**
     * Creaza lista afisata in tabel cu obiectele noi formate
     * @param table
     */
    public void showOrderTable(JTable table) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (HashMap.Entry<Order, ArrayList<MenuItem>> entry: hmap.entrySet()){
            list.add(new Object[] {entry.getKey().getOrderID(), entry.getKey().getTable(), computePrice(entry.getKey())});
        }
        table.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), new String[] {"ID", "Table", "price"}));
    }

}
