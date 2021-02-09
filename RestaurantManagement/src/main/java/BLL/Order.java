package BLL;

import java.util.*;

import static java.lang.Integer.parseInt;

public class Order {
    private int OrderID;
    private Date date;
    private int table;


    public int getOrderID() {
        return OrderID;
    }


    public int getTable() {
        return table;
    }


    public Order(int id, Date date, int number)
    {
        this.OrderID=id;
        this.date=date;
        this.table=number;
    }
    public int hashCode() {
        return (int)date.hashCode()+OrderID+parseInt(String.valueOf(table));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return OrderID == order.OrderID &&
                table == order.table &&
                date.equals(order.date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderID=" + OrderID +
                ", date=" + date +
                ", table=" + table +
                '}'+"\n";
    }
}