package BLL;



public abstract class MenuItem{
    private String name;
    private float price;
    public MenuItem(){}
    public MenuItem(String name,float price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice(){
        return this.price;
    }
    public abstract float computePrice();
    public abstract void add(MenuItem p);

    public String toString(){
        return getName()+ " " + getPrice()+ "\n";
    }



}

