import java.util.Comparator;

public class OrderComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if(o1.getSimIndex() <  o2.getSimIndex()) return -1;
        if(o1.getSimIndex() == o2.getSimIndex()) return 0;
        return 1;
    }

}
