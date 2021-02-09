package BLL;



import java.util.ArrayList;
import java.util.Iterator;

public class CompositeProduct extends MenuItem {
    private String product;
    private float price;
    private  ArrayList<MenuItem> list=new ArrayList<MenuItem>();

    public CompositeProduct(String product)
    {
        super(product, 0);
    }

    public float computePrice()
    {
        float t=0;
        Iterator<MenuItem> menuItemIterator = list.iterator();
        while(menuItemIterator.hasNext()) {
            MenuItem menuItem = menuItemIterator.next();
            t += menuItem.getPrice();
        }
        super.setPrice(t);
        return t;
    }
    public void setPrice(float price) {
        super.setPrice(price);
    }

    @Override
    public void add(MenuItem p) {
        list.add(p);
    }
}



