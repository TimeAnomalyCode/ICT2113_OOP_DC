public class Item {
    private String m_productId;
    private int m_weight;

    Item(String productId, int weight){
        m_productId = productId;
        m_weight = weight;
    }

    public void setProductId(String productId){m_productId = productId;}
    public void setWeight(int weight){m_weight = weight;}
    public String getProductId(){return m_productId;}
    public int getWeight(){return m_weight;}
}
