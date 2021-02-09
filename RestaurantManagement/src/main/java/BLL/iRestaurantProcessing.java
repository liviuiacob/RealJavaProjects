package BLL;


import java.util.Date;

public interface iRestaurantProcessing {
    //for waiter
    public void createNewOrder(int a, Date b, int c);
    public float computePrice(Order o);
    public void generateBill(int a);

    //for admin
    public void addInMenu(MenuItem m);
    public void deleteMenuItem(String str);
    public void editMenuItem(String a, float f);
}
