public class Item {
    private String m_productId;
    private int m_weight;

    Item(String productId, int weight){

        m_productId = productId;

        if(weight < 0){
            m_weight = 0;
        }
        else {
            m_weight = weight;
        }
    }

    public boolean equals(Item itm){
        return itm.m_productId.equals(this.m_productId);
    }
}
