import java.util.List;

public class Trolley {
    private List<Order> orders;
    
    public Trolley(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


}
