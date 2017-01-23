import java.util.ArrayList;
import java.util.List;

public class Order {
    
    //Index, how similar the order is to a previous order, i.e. the root Order
    private Integer simIndex;
    
    //The carton size of the order
    private int cartonSize;
    
    //The articles in the order
    private List<Article> articles = new ArrayList<Article>();
    
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Order(int test) {
        this.simIndex = test;
    }

    public Integer getSimIndex() {
        return simIndex;
    }

    public void setSimIndex(Integer simIndex) {
        this.simIndex = simIndex;
    }

    public int getCartonSize() {
        return cartonSize;
    }

    public void setCartonSize(int cartonSize) {
        this.cartonSize = cartonSize;
    }
    
    public void addArticle(Article article) {
        articles.add(article);
    }
}






