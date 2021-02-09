package BLL;


public class BaseProduct extends MenuItem {

    private String product;
    private float price;

    public BaseProduct(String product,float price)
    {
        super(product, price);
    }

    public void setPrice(float price) {
        super.setPrice(price);
    }

    @Override
    public float computePrice() {
        return getPrice();
    }

    @Override
    public void add(MenuItem p) {

    }


}

