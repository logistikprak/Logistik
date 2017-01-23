import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Rossmann practice logistics.
 * 
 * @author Torben Windler
 * @version 0.1
 */
public class Logistics {

    public static void main(String[] args) {

        // List of the 500 Orders we want to sort
        List<Order> orderlist = new ArrayList<Order>();

        // Test filling for the orders list
        for (int i = 0; i < 500; i++) {
            orderlist.add(new Order((int) ((Math.random() * 10000) + 1)));
            for (int j = 0; j < (int) ((Math.random() * 100) + 1); j++) {
                orderlist.get(i)
                    .addArticle(new Article((int) ((Math.random() * 10) + 1)));
            }
        }

        // Get best Order
        Order rootOrder = getBestOrder(orderlist);

        // Compute SimIndexes for every order
        computeSimIndexes(orderlist, rootOrder);

        // Sort orders and split them fit onto the trolleys
        List<Trolley> trolleys = sortAndSplit(orderlist);

        // Test printout
        for (int i = 0; i < trolleys.size(); i++) {
            System.out.println(i + ": ");
            for (int j = 0; j < trolleys.get(i).getOrders().size(); j++) {
                System.out
                    .println(trolleys.get(i).getOrders().get(j).getSimIndex());
            }
        }
    }

    public static void computeSimIndexes(List<Order> orderlist,
        Order rootOrder) {
        for (int i = 1; i < orderlist.size(); i++) {
            orderlist.get(i).setSimIndex(editDistDP(rootOrder.getArticles(),
                orderlist.get(i).getArticles(), rootOrder.getArticles().size(),
                orderlist.get(i).getArticles().size()));
        }

    }

    public static Order getBestOrder(List<Order> orderlist) {
        Order bestOrder = orderlist.get(0);
        for (int i = 1; i < orderlist.size() - 1; i++) {
            if (orderlist.get(i).getSimIndex() < bestOrder.getSimIndex()) {
                bestOrder = orderlist.get(i);
            }
        }
        return bestOrder;
    }

    public static int min(int x, int y, int z) {
        if (x < y && x < z)
            return x;
        if (y < x && y < z)
            return y;
        else
            return z;
    }

    public static int editDistDP(List<Article> a1, List<Article> a2, int m,
        int n) {
        // Create a table to store results of subproblems
        int dp[][] = new int[m + 1][n + 1];

        // Fill d[][] in bottom up manner
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // If first string is empty, only option is to
                // isnert all characters of second string
                if (i == 0)
                    dp[i][j] = j; // Min. operations = j

                // If second string is empty, only option is to
                // remove all characters of second string
                else if (j == 0)
                    dp[i][j] = i; // Min. operations = i

                // If last characters are same, ignore last char
                // and recur for remaining string
                else if (a1.get(i - 1).getArticleNumber() == a2.get(j - 1)
                    .getArticleNumber())
                    dp[i][j] = dp[i - 1][j - 1];

                // If last character are different, consider all
                // possibilities and find minimum
                else
                    dp[i][j] = 1 + min(dp[i][j - 1], // Insert
                        dp[i - 1][j], // Remove
                        dp[i - 1][j - 1]); // Replace
            }
        }

        return dp[m][n];
    }

    /**
     * Sorts a given list of orders and splits them fit onto the trolleys.
     * 
     * @param orderlist
     *            the order list which shall be sorted and splitted.
     * 
     * @return a list of trolleys with the splitted orders.
     */
    public static List<Trolley> sortAndSplit(List<Order> orderlist) {
        List<Trolley> trolleys = new ArrayList<Trolley>();
        for (int i = 0; 0 < orderlist.size(); i++) {
            Collections.sort(orderlist, new OrderComparator());
            trolleys.add(i, splitForCartonSize(orderlist));
        }
        return trolleys;
    }

    /**
     * Splits a given orderlist depending on carton size.
     * 
     * @param orderlist
     *            the order list which shall be splitted.
     * 
     * @return a trolley with a sublist of the orderlist.
     */
    public static Trolley splitForCartonSize(List<Order> orderlist) {
        List<Order> split = new ArrayList<Order>();
        // Split the List after "carton size = 6" Orders
        while (split.size() < 6 && orderlist.size() > 0) {
            split.add(orderlist.remove(0));
        }
        return new Trolley(split);
    }
}